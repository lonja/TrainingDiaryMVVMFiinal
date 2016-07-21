package su.dreamteam.lonja.trainingdiaryfinal.common;

import rx.Observable;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;

public abstract class UseCase<Q extends UseCase.RequestValues, P extends UseCase.ResponseValue> {

    private final Scheduler mBackgroundScheduler;

    protected UseCase(Scheduler backgroundScheduler) {
        mBackgroundScheduler = backgroundScheduler;
    }

    public Observable<P> run(Q requestValues) {
        return executeUseCase(requestValues)
                .subscribeOn(mBackgroundScheduler)
                .observeOn(AndroidSchedulers.mainThread());
    }

    protected abstract Observable<P> executeUseCase(Q requestValues);

    public interface RequestValues {
    }

    public interface ResponseValue {
    }
}
