package su.dreamteam.lonja.data.repository;

import android.support.annotation.NonNull;

import io.realm.RealmResults;
import rx.Observable;
import su.dreamteam.lonja.data.model.Exercise;
import su.dreamteam.lonja.data.source.contract.ExercisesDataSourceContract.*;

public class ExercisesRepository implements ExercisesDataSource {

    private ExercisesRealmDataSource mLocalDataSource;

    private static ExercisesRepository INSTANCE;

    private ExercisesRepository(ExercisesRealmDataSource localDataSource) {
        mLocalDataSource = localDataSource;
    }

    public static ExercisesRepository getInstance(ExercisesRealmDataSource localDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new ExercisesRepository(localDataSource);
        }
        return INSTANCE;
    }

    @Override
    public Observable<RealmResults<Exercise>> getExercises() {
        return mLocalDataSource.getExercises();
    }

    @Override
    public Observable<Exercise> getExercise(String exerciseId) {
        return mLocalDataSource.getExercise(exerciseId);
    }

    @Override
    public Observable<Exercise> getExercise(int exercise) {
        return mLocalDataSource.getExercise(exercise);
    }

    @Override
    public Observable saveExercise(@NonNull Exercise exercise) {
        return mLocalDataSource.saveExercise(exercise);
    }

    @Override
    public Observable deleteExercise(@NonNull String exerciseId) {
        return mLocalDataSource.deleteExercise(exerciseId);
    }
}