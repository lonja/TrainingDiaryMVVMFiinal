package su.dreamteam.lonja.data.source.local;

import android.support.annotation.NonNull;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.exceptions.RealmException;
import rx.Observable;
import su.dreamteam.lonja.data.model.Training;
import su.dreamteam.lonja.data.source.TrainingsDataSource;


public final class TrainingsLocalDataSource implements TrainingsDataSource {

    private Realm mRealm;

    private TrainingsLocalDataSource() {
        mRealm = Realm.getDefaultInstance();
    }

    private static TrainingsLocalDataSource INSTANCE;

    public static TrainingsLocalDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TrainingsLocalDataSource();
        }
        return INSTANCE;
    }

    @Override
    public Observable<RealmResults<Training>> getTrainings() {
        return mRealm.where(Training.class)
                .findAllAsync()
                .asObservable();
    }

    @Override
    public Observable<Training> getTraining(String trainingId) {
        Training training = mRealm.where(Training.class)
                .equalTo("id", trainingId)
                .findFirst();
        if (training != null) {
            return training.asObservable();
        }
        return Observable.error(new RealmException("Exercise not found"));
    }

    @Override
    public Observable saveTraining(@NonNull Training training) {
        try {
            mRealm.beginTransaction();
            Training realmTraining = mRealm.createObject(Training.class);
            realmTraining.setDate(training.getDate());
            realmTraining.setDuration(training.getDuration());
            realmTraining.setExercises(training.getExercises());
            mRealm.commitTransaction();
            return Observable.empty();
        } catch (RealmException exception) {
            mRealm.cancelTransaction();
            return Observable.error(exception);
        }
    }

    @Override
    public Observable deleteTraining(@NonNull String trainingId) {
        try {
            mRealm.beginTransaction();
            mRealm.where(Training.class)
                    .equalTo("id", trainingId)
                    .findFirst()
                    .deleteFromRealm();
            mRealm.commitTransaction();
            return Observable.empty();
        } catch (RealmException exception) {
            mRealm.cancelTransaction();
            return Observable.error(exception);
        }
    }
}