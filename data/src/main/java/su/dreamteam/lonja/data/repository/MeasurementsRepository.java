package su.dreamteam.lonja.data.repository;

import android.support.annotation.NonNull;

import io.realm.RealmResults;
import rx.Observable;
import su.dreamteam.lonja.data.model.Measurement;
import su.dreamteam.lonja.data.source.MeasurementsDataSource;

public class MeasurementsRepository implements MeasurementsDataSource {

    public void refreshMeasurements() {

    }

    @Override
    public Observable<RealmResults<Measurement>> getMeasurements() {
        return null;
    }

    @Override
    public Observable<Measurement> getMeasurement(@NonNull String measurementId) {
        return null;
    }

    @Override
    public Observable saveMeasurement(@NonNull Measurement measurement) {
        return null;
    }

    @Override
    public Observable deleteMeasurement(@NonNull String measurementId) {
        return null;
    }
}
