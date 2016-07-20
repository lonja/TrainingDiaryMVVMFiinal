package su.dreamteam.lonja.trainingdiaryfinal.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.annotation.NonNull;

import io.realm.RealmResults;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;
import su.dreamteam.lonja.data.repository.TrainingsRepository;
import su.dreamteam.lonja.trainingdiaryfinal.BR;
import su.dreamteam.lonja.trainingdiaryfinal.adapter.WorkoutsAdapter;
import su.dreamteam.lonja.trainingdiaryfinal.ui.activity.AddEditWorkoutActivity;
import su.dreamteam.lonja.trainingdiaryfinal.ui.activity.ExercisesChoiceActivity;

import static com.google.common.base.Preconditions.checkNotNull;

public class WorkoutsViewModel extends BaseObservable implements ViewModel {

    private TrainingsRepository mRepo;

    private int mWorkoutsSize = 0;

    private CompositeSubscription mSubscriptions;

    private WorkoutsAdapter mAdapter;

    private Context mContext;

    public WorkoutsViewModel(@NonNull TrainingsRepository repo,
                             @NonNull WorkoutsAdapter adapter,
                             @NonNull Context context) {
        mRepo = checkNotNull(repo);
        mAdapter = checkNotNull(adapter);
        mContext = checkNotNull(context);
        mSubscriptions = new CompositeSubscription();
    }

    public void onAddClick() {
        Intent intent = new Intent(mContext, ExercisesChoiceActivity.class);
        mContext.startActivity(intent);
    }

    @Override
    public void subscribe() {
        Subscription subscription = mRepo.getTrainings()
                .filter(RealmResults::isLoaded)
                .first()
                .doOnNext(workouts -> {
                    mAdapter.replaceData(workouts);
                    workouts.addChangeListener(changedData -> {
                        setWorkoutsListSize(changedData.size());
                        mAdapter.notifyDataSetChanged();
                    });
                    setWorkoutsListSize(workouts.size());
                })
                .subscribe();
        mSubscriptions.add(subscription);
    }

    @Override
    public void unsubscribe() {
        mSubscriptions.clear();
    }

    @Bindable
    public boolean isEmpty() {
        return mWorkoutsSize == 0;
    }

    private void setWorkoutsListSize(int workoutsListSize) {
        mWorkoutsSize = workoutsListSize;
        notifyPropertyChanged(BR.empty);
    }
}
