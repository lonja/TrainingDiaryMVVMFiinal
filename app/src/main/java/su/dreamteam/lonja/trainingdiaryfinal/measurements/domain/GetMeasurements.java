package su.dreamteam.lonja.trainingdiaryfinal.measurements.domain;

import android.support.annotation.NonNull;

import io.realm.RealmResults;
import rx.Observable;
import rx.Scheduler;
import su.dreamteam.lonja.data.model.Measurement;
import su.dreamteam.lonja.trainingdiaryfinal.common.UseCase;
import su.dreamteam.lonja.trainingdiaryfinal.measurements.domain.filter.FilterFactory;
import su.dreamteam.lonja.trainingdiaryfinal.measurements.domain.filter.MeasurementsFilterType;

public class GetMeasurements extends UseCase<GetMeasurements.RequestValues, GetMeasurements.ResponseValue> {

    private final MeasurementsRepository mMeasurementsRepository;

    private final FilterFactory mFilterFactoy;

    protected GetMeasurements(Scheduler backgroundScheduler) {
        super(backgroundScheduler);
    }

    @Override
    protected Observable<ResponseValue> executeUseCase(RequestValues requestValues) {
        return null;
    }

    public static final class RequestValues implements UseCase.RequestValues {

        private final MeasurementsFilterType mCurrentFiltering;

        private final boolean mForceUpdate;

        public RequestValues(boolean forceUpdate, @NonNull MeasurementsFilterType currentFiltering) {
            mForceUpdate = forceUpdate;
            mCurrentFiltering = currentFiltering;
        }

        public boolean isForceUpdate() {
            return mForceUpdate;
        }

        public MeasurementsFilterType getCurrentFiltering() {
            return mCurrentFiltering;
        }
    }

    public static final class ResponseValue implements UseCase.ResponseValue {


        private final RealmResults<Measurement> mMeasurements;

        public ResponseValue(RealmResults<Measurement> measurements) {
            mMeasurements = measurements;
        }

        public RealmResults<Measurement> getMeasurements() {
            return mMeasurements;
        }
    }
}
