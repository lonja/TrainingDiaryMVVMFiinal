package su.dreamteam.lonja.data.source.local;

import android.support.annotation.NonNull;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;
import io.realm.exceptions.RealmException;
import rx.Observable;
import su.dreamteam.lonja.data.model.Measurement;
import su.dreamteam.lonja.data.source.contract.MeasurementsDataSourceContract;

public final class MeasurementsLocalDataSource extends BaseRealmDataSource implements MeasurementsDataSourceContract.MeasurementsRealmDataSource {

    private static MeasurementsLocalDataSource INSTANCE;
    private Realm mRealm;

    private MeasurementsLocalDataSource() {

    }

    public static MeasurementsLocalDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new MeasurementsLocalDataSource();
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

    @Override
    public void openConnection() {
        if (mRealm != null) {
            return;
        }
        mRealm = Realm.getDefaultInstance();
    }

    @Override
    public void closeConnection() {
        if (mRealm == null) {
            return;
        }
        closeConnection(mRealm);
    }
}