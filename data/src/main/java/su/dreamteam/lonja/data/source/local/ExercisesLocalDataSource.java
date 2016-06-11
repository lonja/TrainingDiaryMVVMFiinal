package su.dreamteam.lonja.data.source.local;

import android.support.annotation.NonNull;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.exceptions.RealmException;
import rx.Observable;
import su.dreamteam.lonja.data.model.Exercise;
import su.dreamteam.lonja.data.source.ExercisesDataSource;

public final class ExercisesLocalDataSource implements ExercisesDataSource {

    private Realm mRealm;

    private ExercisesLocalDataSource() {
        mRealm = Realm.getDefaultInstance();
    }

    private static ExercisesLocalDataSource INSTANCE;

    public static ExercisesLocalDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ExercisesLocalDataSource();
        }
        return INSTANCE;
    }

    @Override
    public Observable<RealmResults<Exercise>> getExercises() {
        return mRealm.where(Exercise.class)
                .findAllAsync()
                .asObservable();
    }

    @Override
    public Observable<Exercise> getExercise(String exerciseId) {
        Exercise exercise = mRealm.where(Exercise.class)
                .equalTo("id", exerciseId)
                .findFirst();
        if (exercise != null) {
            return exercise.asObservable();
        }
        return Observable.error(new RealmException("Exercise not found"));
    }

    @Override
    public Observable<Exercise> getExercise(int exercise) {
        switch (exercise) {
            case Exercise.BENCH_PRESS: {

            }
            case Exercise.BENT_OVER_ROW: {

            }
            case Exercise.DEADLIFT: {

            }
            case Exercise.PULL_UPS: {

            }
            case Exercise.SQUATS: {

            }
        }
        return null;
    }

    @Override
    public Observable saveExercise(@NonNull Exercise exercise) {
        try {
            mRealm.beginTransaction();
            Exercise realmExercise = mRealm.createObject(Exercise.class);
            realmExercise.setApproaches(exercise.getApproaches());
            realmExercise.setGroup(exercise.getGroup());
            realmExercise.setId(exercise.getId());
            realmExercise.setMuscles(exercise.getMuscles());
            realmExercise.setSynergists(exercise.getSynergists());
            realmExercise.setTitle(exercise.getTitle());
            mRealm.commitTransaction();
            return Observable.empty();
        } catch (RealmException exception) {
            mRealm.cancelTransaction();
            return Observable.error(exception);
        }
    }

    @Override
    public Observable deleteExercise(@NonNull String exerciseId) {
        try {
            mRealm.beginTransaction();
            mRealm.where(Exercise.class)
                    .equalTo("id", exerciseId)
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