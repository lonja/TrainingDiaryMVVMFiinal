package su.dreamteam.lonja.data.source.local;

import android.support.annotation.NonNull;

import io.realm.RealmResults;
import rx.Observable;
import su.dreamteam.lonja.data.model.Exercise;
import su.dreamteam.lonja.data.source.ExercisesDataSource;

public final class ExercisesLocalDataSource implements ExercisesDataSource {

    @Override
    public Observable<RealmResults<Exercise>> getExercises() {
        return null;
    }

    @Override
    public Observable<Exercise> getExercise(String exerciseId) {
        return null;
    }

    @Override
    public Observable<Exercise> getExercise(int exercise) {
        return null;
    }

    @Override
    public Observable<Void> saveExercise(@NonNull Exercise exercise) {
        return null;
    }

    @Override
    public Observable<Void> deleteExercise(@NonNull String exerciseId) {
        return null;
    }
}
