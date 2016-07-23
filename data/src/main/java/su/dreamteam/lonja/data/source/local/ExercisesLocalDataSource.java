package su.dreamteam.lonja.data.source.local;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import io.realm.RealmResults;
import io.realm.exceptions.RealmException;
import rx.Observable;
import su.dreamteam.lonja.data.model.Exercise;
import su.dreamteam.lonja.data.source.ExercisesDataSource;

public final class ExercisesLocalDataSource extends LocalDataSource implements ExercisesDataSource {

    private static ExercisesLocalDataSource INSTANCE;

    private ExercisesLocalDataSource() {
        super();
    }

    public static ExercisesLocalDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ExercisesLocalDataSource();
        }
        return INSTANCE;
    }

    @Override
    public Observable<RealmResults<Exercise>> getExercises() {
        return mRealm.where(Exercise.class)
                .findAllAsync()
                .asObservable();
    }

    @Override
    public Observable<Exercise> getExercise(String exerciseId) {
        Exercise exercise = mRealm.where(Exercise.class)
                .equalTo("id", exerciseId)
                .findFirst();
        if (exercise != null) {
            return exercise.asObservable();
        }
        return Observable.error(new RealmException("Exercise not found"));
    }

    @Nullable
    @Override
    public Observable<Exercise> getExercise(int exercise) {
        switch (exercise) {
            case Exercise.BENCH_PRESS: {
                return getExerciseByTitle("");
            }
            case Exercise.BENT_OVER_ROW: {
                return getExerciseByTitle("");
            }
            case Exercise.DEADLIFT: {
                return getExerciseByTitle("");
            }
            case Exercise.PULL_UPS: {
                return getExerciseByTitle("");
            }
            case Exercise.SQUATS: {
                return getExerciseByTitle("");
            }
        }
        return null;
    }

    private Observable<Exercise> getExerciseByTitle(String exerciseName) {
        return mRealm.where(Exercise.class).equalTo("title", exerciseName).findFirstAsync().asObservable();
    }

    @Override
    public Observable saveExercise(@NonNull Exercise exercise) {
        return executeTransactionAsync(realm -> realm.copyToRealmOrUpdate(exercise));
    }

    @Override
    public Observable deleteExercise(@NonNull String exerciseId) {
        return executeTransactionAsync(realm -> realm.where(Exercise.class)
                .equalTo("id", exerciseId)
                .findFirst()
                .deleteFromRealm());
    }
}