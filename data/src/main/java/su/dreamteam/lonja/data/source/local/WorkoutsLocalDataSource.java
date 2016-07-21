package su.dreamteam.lonja.data.source.local;

import android.support.annotation.NonNull;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.exceptions.RealmException;
import rx.Observable;
import su.dreamteam.lonja.data.model.Workout;
import su.dreamteam.lonja.data.source.WorkoutsDataSource;


public final class WorkoutsLocalDataSource implements WorkoutsDataSource {

    private Realm mRealm;

    private WorkoutsLocalDataSource() {
        mRealm = Realm.getDefaultInstance();
    }

    private static WorkoutsLocalDataSource INSTANCE;

    public static WorkoutsLocalDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new WorkoutsLocalDataSource();
        }
        return INSTANCE;
    }

    @Override
    public Observable<RealmResults<Workout>> getWorkouts() {
        return mRealm.where(Workout.class)
                .findAllAsync()
                .asObservable();
    }

    @Override
    public Observable<Workout> getWorkout(String trainingId) {
        Workout workout = mRealm.where(Workout.class)
                .equalTo("id", trainingId)
                .findFirst();
        if (workout != null) {
            return workout.asObservable();
        }
        return Observable.error(new RealmException("Exercise not found"));
    }

    @Override
    public Observable saveWorkout(@NonNull Workout workout) {
        try {
            mRealm.beginTransaction();
            Workout realmWorkout = mRealm.createObject(Workout.class);
            realmWorkout.setDate(workout.getDate());
            realmWorkout.setDuration(workout.getDuration());
            realmWorkout.setExercises(workout.getExercises());
            mRealm.commitTransaction();
            return Observable.empty();
        } catch (RealmException exception) {
            mRealm.cancelTransaction();
            return Observable.error(exception);
        }
    }

    @Override
    public Observable deleteWorkout(@NonNull String trainingId) {
        try {
            mRealm.beginTransaction();
            mRealm.where(Workout.class)
                    .equalTo("id", trainingId)
                    .findFirst()
                    .deleteFromRealm();
            mRealm.commitTransaction();
            return Observable.empty();
        } catch (RealmException exception) {
            mRealm.cancelTransaction();
            return Observable.error(exception);
        }
    }
}