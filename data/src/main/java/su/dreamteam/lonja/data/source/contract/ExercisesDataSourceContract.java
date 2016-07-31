package su.dreamteam.lonja.data.source.contract;

import android.support.annotation.NonNull;

import io.realm.RealmResults;
import rx.Observable;
import su.dreamteam.lonja.data.model.Exercise;
import su.dreamteam.lonja.data.source.local.RealmLocalDataSource;

public interface ExercisesDataSourceContract {

    interface ExercisesRealmDataSource extends ExercisesDataSource, RealmLocalDataSource {

    }

    interface ExercisesDataSource {

        Observable<RealmResults<Exercise>> getExercises();

        Observable<Exercise> getExercise(String exerciseId);

        Observable<Exercise> getExercise(int exercise);

        Observable saveExercise(@NonNull Exercise exercise);

        Observable deleteExercise(@NonNull String exerciseId);

    }
}
