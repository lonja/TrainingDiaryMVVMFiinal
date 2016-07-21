package su.dreamteam.lonja.trainingdiaryfinal.measurements.domain.filter;

import io.realm.RealmResults;
import su.dreamteam.lonja.data.model.Measurement;

public class AllMeasurementsFilter implements MeasurementFilter {

    @Override
    public RealmResults<Measurement> filter(RealmResults<Measurement> measurements) {
        return measurements;
    }
}
