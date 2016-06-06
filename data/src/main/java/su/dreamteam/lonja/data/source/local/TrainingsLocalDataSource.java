package su.dreamteam.lonja.data.source.local;

import android.support.annotation.NonNull;

import io.realm.RealmResults;
import rx.Observable;
import su.dreamteam.lonja.data.model.Training;
import su.dreamteam.lonja.data.source.TrainingsDataSource;


public final class TrainingsLocalDataSource implements TrainingsDataSource {

    @Override
    public Observable<RealmResults<Training>> getTrainings() {
        return null;
    }

    @Override
    public Observable<Training> getTraining(String trainingId) {
        return null;
    }

    @Override
    public Observable<Void> saveTraining(@NonNull Training training) {
        return null;
    }

    @Override
    public Observable<Void> deleteTraining(@NonNull String trainingId) {
        return null;
    }
}
