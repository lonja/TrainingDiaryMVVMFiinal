package su.dreamteam.lonja.data.source;


import android.support.annotation.NonNull;

import io.realm.RealmResults;
import rx.Observable;
import su.dreamteam.lonja.data.model.Workout;

public interface WorkoutsDataSource {

    Observable<RealmResults<Workout>> getWorkouts();

    Observable<Workout> getWorkout(String trainingId);

    Observable<Workout> saveWorkout(@NonNull Workout workout);

    Observable<Workout> deleteWorkout(@NonNull String trainingId);
}