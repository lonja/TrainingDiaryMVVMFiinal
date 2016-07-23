package su.dreamteam.lonja.data.source;

import android.support.annotation.NonNull;

import io.realm.RealmResults;
import rx.Observable;
import su.dreamteam.lonja.data.model.Measurement;

public interface MeasurementsDataSource {

    Observable<RealmResults<Measurement>> getMeasurements();

    Observable<Measurement> getMeasurement(@NonNull String measurementId);

    Observable saveMeasurement(@NonNull Measurement measurement);

    Observable deleteMeasurement(@NonNull String measurementId);
}
