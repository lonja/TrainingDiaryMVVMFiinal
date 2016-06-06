package su.dreamteam.lonja.data.source;


import android.support.annotation.NonNull;

import io.realm.RealmResults;
import rx.Observable;
import su.dreamteam.lonja.data.model.Training;

public interface TrainingsDataSource {

    Observable<RealmResults<Training>> getTrainings();

    Observable<Training> getTraining(String trainingId);

    Observable<Void> saveTraining(@NonNull Training training);

    Observable<Void> deleteTraining(@NonNull String trainingId);
}