package su.dreamteam.lonja.data.repository;

import android.support.annotation.NonNull;

import io.realm.RealmResults;
import rx.Observable;
import su.dreamteam.lonja.data.model.Training;
import su.dreamteam.lonja.data.source.TrainingsDataSource;

public class TrainingsRepository implements TrainingsDataSource {

    private TrainingsDataSource mLocalDataSource;

    private static TrainingsRepository INSTANCE;

    private TrainingsRepository(TrainingsDataSource localDataSource) {
        mLocalDataSource = localDataSource;
    }

    public static TrainingsRepository getInstance(TrainingsDataSource localDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new TrainingsRepository(localDataSource);
        }
        return INSTANCE;
    }

    @Override
    public Observable<RealmResults<Training>> getTrainings() {
        return mLocalDataSource.getTrainings();
    }

    @Override
    public Observable<Training> getTraining(String trainingId) {
        return mLocalDataSource.getTraining(trainingId);
    }

    @Override
    public Observable saveTraining(@NonNull Training training) {
        return mLocalDataSource.saveTraining(training);
    }

    @Override
    public Observable deleteTraining(@NonNull String trainingId) {
        return mLocalDataSource.deleteTraining(trainingId);
    }
}
