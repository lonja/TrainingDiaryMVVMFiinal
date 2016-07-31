package su.dreamteam.lonja.data.source;


import android.support.annotation.NonNull;

import io.realm.RealmResults;
import rx.Observable;
import su.dreamteam.lonja.data.model.Workout;

public interface TrainingsDataSource {

    Observable<RealmResults<Workout>> getTrainings();

    Observable<Workout> getTraining(String trainingId);

    Observable saveTraining(@NonNull Workout training);

    Observable deleteTraining(@NonNull String trainingId);
}