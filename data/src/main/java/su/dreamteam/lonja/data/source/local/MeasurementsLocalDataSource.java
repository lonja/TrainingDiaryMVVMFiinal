package su.dreamteam.lonja.data.source.local;

import android.support.annotation.NonNull;
import android.system.ErrnoException;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;
import rx.Observable;
import su.dreamteam.lonja.data.model.Measurement;
import su.dreamteam.lonja.data.source.MeasurementsDataSource;

public final class MeasurementsLocalDataSource implements MeasurementsDataSource {

    private Realm mRealm;

    private static MeasurementsLocalDataSource INSTANCE;

    private MeasurementsLocalDataSource() {
        mRealm = Realm.getDefaultInstance();
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
        return Observable.empty();
    }

    @Override
    public void saveMeasurement(@NonNull Measurement measurement) {
        try {
            mRealm.beginTransaction();
            Measurement realmMeasurement = mRealm.createObject(Measurement.class);
            realmMeasurement.setId(measurement.getId());
            realmMeasurement.setDate(measurement.getDate());
            realmMeasurement.setComment(measurement.getComment());
            realmMeasurement.setChest(measurement.getChest());
            realmMeasurement.setLeftBiceps(measurement.getLeftBiceps());
            realmMeasurement.setLeftForearm(measurement.getLeftForearm());
            realmMeasurement.setLeftHip(measurement.getLeftHip());
            realmMeasurement.setLeftShin(measurement.getLeftShin());
            realmMeasurement.setNeck(measurement.getNeck());
            realmMeasurement.setRightBiceps(measurement.getRightBiceps());
            realmMeasurement.setRightShin(measurement.getRightShin());
            realmMeasurement.setRightForearm(measurement.getRightForearm());
            realmMeasurement.setRightHip(measurement.getRightHip());
            realmMeasurement.setWaist(measurement.getWaist());
            realmMeasurement.setWeight(measurement.getWeight());
            mRealm.commitTransaction();
        } catch (Exception e) {
            mRealm.cancelTransaction();
        }
    }

    @Override
    public void deleteMeasurement(@NonNull String measurementId) {
        try {
            mRealm.beginTransaction();
            mRealm.where(Measurement.class)
                    .equalTo("id", measurementId)
                    .findFirst()
                    .deleteFromRealm();
            mRealm.commitTransaction();
        } catch (Exception | Error e) {
            mRealm.cancelTransaction();
        }
    }
}