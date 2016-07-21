package su.dreamteam.lonja.data.repository;

import android.support.annotation.NonNull;

import io.realm.RealmResults;
import rx.Observable;
import su.dreamteam.lonja.data.model.Workout;
import su.dreamteam.lonja.data.source.WorkoutsDataSource;

public class WorkoutsRepository implements WorkoutsDataSource {

    private WorkoutsDataSource mLocalDataSource;

    private static WorkoutsRepository INSTANCE;

    private WorkoutsRepository(WorkoutsDataSource localDataSource) {
        mLocalDataSource = localDataSource;
    }

    public static WorkoutsRepository getInstance(WorkoutsDataSource localDataSource) {
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
    public Observable<Workout> getWorkout(String trainingId) {
        return mLocalDataSource.getWorkout(trainingId);
    }

    @Override
    public Observable saveWorkout(@NonNull Workout workout) {
        return mLocalDataSource.saveWorkout(workout);
    }

    @Override
    public Observable deleteWorkout(@NonNull String trainingId) {
        return mLocalDataSource.deleteWorkout(trainingId);
    }
}
