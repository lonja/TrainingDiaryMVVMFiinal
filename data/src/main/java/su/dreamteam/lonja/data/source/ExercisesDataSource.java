package su.dreamteam.lonja.data.source;

import android.support.annotation.NonNull;

import io.realm.RealmResults;
import rx.Observable;
import su.dreamteam.lonja.data.model.Exercise;

public interface ExercisesDataSource {

    Observable<RealmResults<Exercise>> getExercises();

    Observable<Exercise> getExercise(String exerciseId);

    Observable<Exercise> getExercise(int exercise);

    Observable saveExercise(@NonNull Exercise exercise);

    Observable deleteExercise(@NonNull String exerciseId);

}
