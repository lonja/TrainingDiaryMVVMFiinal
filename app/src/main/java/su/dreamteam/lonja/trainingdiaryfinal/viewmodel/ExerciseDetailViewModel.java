package su.dreamteam.lonja.trainingdiaryfinal.viewmodel;


import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;
import su.dreamteam.lonja.data.model.Exercise;
import su.dreamteam.lonja.data.repository.ExercisesRepository;

import static com.google.common.base.Preconditions.checkNotNull;

public class ExerciseDetailViewModel extends BaseObservable implements ViewModel {

    public final ObservableField<Exercise> exercise = new ObservableField<>();

    private ExercisesRepository mRepo;

    private CompositeSubscription mSubscriptions;

    private Context mContext;

    private String mExerciseId;

    public ExerciseDetailViewModel(@NonNull ExercisesRepository repo,
                                   @NonNull Context context,
                                   @NonNull String exerciseId) {
        mRepo = checkNotNull(repo);
        mContext = checkNotNull(context);
        mExerciseId = exerciseId;
        mSubscriptions = new CompositeSubscription();
    }

    @Override
    public void subscribe() {
        Subscription subscription = mRepo.getExercise(mExerciseId)
                .filter(exercise -> exercise.isLoaded())
                .doOnNext(this::setExercise)
                .doOnError(this::showError)
                .subscribe();
        mSubscriptions.add(subscription);
    }

    private void showError(Throwable throwable) {

    }

    private void setExercise(Exercise exercise) {
        this.exercise.set(exercise);
    }

    @Override
    public void unsubscribe() {
        mSubscriptions.clear();
    }
}