package su.dreamteam.lonja.data.repository;

import android.support.annotation.NonNull;

import io.realm.RealmResults;
import rx.Observable;
import su.dreamteam.lonja.data.model.Measurement;
import su.dreamteam.lonja.data.source.MeasurementsDataSource;

public class MeasurementsRepository implements MeasurementsDataSource {

    private MeasurementsDataSource mLocalDataSource;

    private static MeasurementsRepository INSTANCE;

    private MeasurementsRepository(MeasurementsDataSource measurementsLocalDataSource) {
        mLocalDataSource = measurementsLocalDataSource;
    }

    public static MeasurementsRepository getInstance(MeasurementsDataSource measurementsLocalDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new MeasurementsRepository(measurementsLocalDataSource);
        }
        return INSTANCE;
    }

    public void refreshMeasurements() {

    }

    @Override
    public Observable<RealmResults<Measurement>> getMeasurements() {
        return mLocalDataSource.getMeasurements();
    }

    @Override
    public Observable<Measurement> getMeasurement(@NonNull String measurementId) {
        return mLocalDataSource.getMeasurement(measurementId);
    }

    @Override
    public Observable saveMeasurement(@NonNull Measurement measurement) {
        return mLocalDataSource.saveMeasurement(measurement);
    }

    @Override
    public Observable deleteMeasurement(@NonNull String measurementId) {
        return mLocalDataSource.deleteMeasurement(measurementId);
    }
}
