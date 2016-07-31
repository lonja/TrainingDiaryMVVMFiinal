package su.dreamteam.lonja.data.repository;

import android.support.annotation.NonNull;

import io.realm.RealmResults;
import rx.Observable;
import su.dreamteam.lonja.data.model.Workout;
import su.dreamteam.lonja.data.source.contract.WorkoutsDataSourceContract.*;

public class WorkoutsRepository implements WorkoutsDataSource {

    private WorkoutsRealmDataSource mLocalDataSource;

    private static WorkoutsRepository INSTANCE;

    private WorkoutsRepository(WorkoutsRealmDataSource localDataSource) {
        mLocalDataSource = localDataSource;
    }

    public static WorkoutsRepository getInstance(WorkoutsRealmDataSource localDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new WorkoutsRepository(localDataSource);
        }
        return INSTANCE;
    }

    @Override
    public Observable<RealmResults<Workout>> getWorkouts() {
        return mLocalDataSource.getWorkouts();
    }

    @Override
    public Observable<Workout> getWorkout(String workoutId) {
        return mLocalDataSource.getWorkout(workoutId);
    }

    @Override
    public Observable saveWorkout(@NonNull Workout workout) {
        return mLocalDataSource.saveWorkout(workout);
    }

    @Override
    public Observable deleteWorkout(@NonNull String workoutId) {
        return mLocalDataSource.deleteWorkout(workoutId);
    }
}
