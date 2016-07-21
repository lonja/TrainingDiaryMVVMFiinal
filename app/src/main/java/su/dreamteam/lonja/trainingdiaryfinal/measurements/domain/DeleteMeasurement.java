package su.dreamteam.lonja.trainingdiaryfinal.measurements.domain;

import rx.Observable;
import rx.Scheduler;
import su.dreamteam.lonja.trainingdiaryfinal.common.UseCase;

public class DeleteMeasurement extends UseCase<DeleteMeasurement.RequestValues, DeleteMeasurement.ResponseValue> {


    protected DeleteMeasurement(Scheduler backgroundScheduler) {
        super(backgroundScheduler);
    }

    @Override
    protected Observable<ResponseValue> executeUseCase(RequestValues requestValues) {
        return null;
    }

    public static final class RequestValues implements UseCase.RequestValues {

    }

    public static final class ResponseValue implements UseCase.ResponseValue {

    }
}
