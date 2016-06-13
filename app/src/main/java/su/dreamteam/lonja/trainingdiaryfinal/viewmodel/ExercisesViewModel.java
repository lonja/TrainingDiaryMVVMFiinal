package su.dreamteam.lonja.trainingdiaryfinal.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.annotation.NonNull;

import io.realm.RealmResults;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;
import su.dreamteam.lonja.data.repository.ExercisesRepository;
import su.dreamteam.lonja.trainingdiaryfinal.BR;
import su.dreamteam.lonja.trainingdiaryfinal.adapter.ExercisesAdapter;

public class ExercisesViewModel extends BaseObservable implements ViewModel {

    private ExercisesRepository mRepo;

    private int mExercisesSize = 0;

    private CompositeSubscription mSubscriptions;

    private ExercisesAdapter mAdapter;

    private Context mContext;

    public ExercisesViewModel(@NonNull ExercisesRepository repo,
                              @NonNull ExercisesAdapter adapter,
                              @NonNull Context context) {
        mRepo = repo;
        mAdapter = adapter;
        mContext = context;
        mSubscriptions = new CompositeSubscription();
    }

    @Override
    public void subscribe() {
        Subscription subscription = mRepo.getExercises()
                .filter(RealmResults::isLoaded)
                .first()
                .doOnNext(exercises -> {
                    mAdapter.replaceData(exercises);
                    exercises.addChangeListener(changedData -> {
                        setExercisesListSize(changedData.size());
                        mAdapter.notifyDataSetChanged();
                    });
                    setExercisesListSize(exercises.size());
                })
                .subscribe();
        mSubscriptions.add(subscription);
    }

    @Bindable
    public boolean isEmpty() {
        return mExercisesSize == 0;
    }

    private void setExercisesListSize(int size) {
        mExercisesSize = size;
        notifyPropertyChanged(BR.empty);
    }

    @Override
    public void unsubscribe() {
        mSubscriptions.clear();
    }
}
