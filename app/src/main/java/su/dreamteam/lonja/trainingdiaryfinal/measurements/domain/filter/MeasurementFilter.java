package su.dreamteam.lonja.trainingdiaryfinal.measurements.domain.filter;

import io.realm.RealmResults;
import su.dreamteam.lonja.data.model.Measurement;

public interface MeasurementFilter {

    RealmResults<Measurement> filter(RealmResults<Measurement> measurements);
}
