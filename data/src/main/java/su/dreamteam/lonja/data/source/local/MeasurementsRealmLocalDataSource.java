package su.dreamteam.lonja.data.source.local;

import android.support.annotation.NonNull;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;
import io.realm.exceptions.RealmException;
import rx.Observable;
import su.dreamteam.lonja.data.model.Measurement;
import su.dreamteam.lonja.data.source.MeasurementsDataSource;

public final class MeasurementsRealmLocalDataSource extends RealmLocalDataSource implements MeasurementsDataSource {

    private static MeasurementsRealmLocalDataSource INSTANCE;
    private Realm mRealm;

    private MeasurementsRealmLocalDataSource() {
        mRealm = Realm.getDefaultInstance();
    }

    public static MeasurementsRealmLocalDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new MeasurementsRealmLocalDataSource();
        }
        return INSTANCE;
    }

    @Override
    public Observable<RealmResults<Measurement>> getMeasurements() {
        return mRealm.where(Measurement.class)
                .findAllAsync()
                .sort("date", Sort.DESCENDING)
                .asObservable();
    }

    @Override
    public Observable<Measurement> getMeasurement(@NonNull String measurementId) {
        Measurement measurement = mRealm.where(Measurement.class)
                .equalTo("id", measurementId)
                .findFirst();
        if (measurement != null) {
            return measurement.asObservable();
        }
        return Observable.error(new RealmException("Measurement not found"));
    }

    @Override
    public Observable saveMeasurement(@NonNull Measurement measurement) {
        return executeTransactionAsync(mRealm, realm -> realm.copyToRealmOrUpdate(measurement));
    }

    @Override
    public Observable deleteMeasurement(@NonNull String measurementId) {
        return executeTransactionAsync(mRealm, realm -> realm.where(Measurement.class)
                .equalTo("id", measurementId)
                .findFirst()
                .deleteFromRealm());
    }
}