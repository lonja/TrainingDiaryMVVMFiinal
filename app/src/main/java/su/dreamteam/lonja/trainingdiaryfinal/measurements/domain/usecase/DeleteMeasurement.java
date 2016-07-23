package su.dreamteam.lonja.trainingdiaryfinal.measurements.domain.usecase;

import android.support.annotation.NonNull;

import rx.Observable;
import rx.Scheduler;
import rx.schedulers.Schedulers;
import su.dreamteam.lonja.data.repository.MeasurementsRepository;
import su.dreamteam.lonja.trainingdiaryfinal.common.UseCase;

public class DeleteMeasurement extends UseCase<DeleteMeasurement.RequestValues, DeleteMeasurement.ResponseValue> {

    private final MeasurementsRepository mMeasurementsRepository;

    public DeleteMeasurement(@NonNull MeasurementsRepository measurementsRepository) {
        super(Schedulers.io());
        mMeasurementsRepository = measurementsRepository;
    }

    @Override
    protected Observable<ResponseValue> executeUseCase(RequestValues requestValues) {
        return mMeasurementsRepository.deleteMeasurement(requestValues.getMeasurementId());
    }

    public static final class RequestValues implements UseCase.RequestValues {

        private final String mMeasurementId;

        public RequestValues(@NonNull String measurementId) {
            mMeasurementId = measurementId;
        }

        public String getMeasurementId() {
            return mMeasurementId;
        }
    }

    public static final class ResponseValue implements UseCase.ResponseValue {

    }
}
