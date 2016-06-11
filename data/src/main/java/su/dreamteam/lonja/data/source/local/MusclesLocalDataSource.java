package su.dreamteam.lonja.data.source.local;

import android.support.annotation.NonNull;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.exceptions.RealmException;
import rx.Observable;
import su.dreamteam.lonja.data.model.Muscle;
import su.dreamteam.lonja.data.source.MusclesDataSource;

public final class MusclesLocalDataSource implements MusclesDataSource {

    private Realm mRealm;

    private MusclesLocalDataSource() {
        mRealm = Realm.getDefaultInstance();
    }

    private static MusclesLocalDataSource INSTANCE;

    public static MusclesLocalDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new MusclesLocalDataSource();
        }
        return INSTANCE;
    }

    @Override
    public Observable<RealmResults<Muscle>> getMuscles() {
        return mRealm.where(Muscle.class)
                .findAllAsync()
                .asObservable();
    }

    @Override
    public Observable<Muscle> getMuscle(String muscleId) {
        Muscle muscle = mRealm.where(Muscle.class)
                .equalTo("id", muscleId)
                .findFirst();
        if (muscle != null) {
            return muscle.asObservable();
        }
        return Observable.error(new RealmException("Exercise not found"));
    }

    @Override
    public Observable<Muscle> getMuscle(int muscle) {
        switch (muscle) {
            case Muscle.BICEPS_LONG_HEAD: {

            }
            case Muscle.BICEPS_SHORT_HEAD: {

            }
            case Muscle.BRACHIALIS: {

            }
            case Muscle.PECTORALIS_MAJOR: {

            }
            case Muscle.PECTORALIS_MINOR: {

            }
            case Muscle.SHOULDER_ANTERIOR_HEAD: {

            }
            case Muscle.SHOULDER_MIDDLE_HEAD: {

            }
            case Muscle.SHOULDER_POSTERIOR_HEAD: {

            }
            case Muscle.TRAPEZIUS: {

            }
            case Muscle.TRICEPS_LATERAL_HEAD: {

            }
            case Muscle.TRICEPS_LONG_HEAD: {

            }
            case Muscle.TRICEPS_MEDIAL_HEAD: {

            }
        }
        return null;
    }

    @Override
    public Observable saveMuscle(@NonNull Muscle muscle) {
        try {
            mRealm.beginTransaction();
            Muscle realmMuscle = mRealm.createObject(Muscle.class);
            realmMuscle.setExercises(muscle.getExercises());
            realmMuscle.setId(muscle.getId());
            realmMuscle.setTitle(muscle.getTitle());
            realmMuscle.setMuscleGroup(muscle.getMuscleGroup());
            mRealm.commitTransaction();
            return Observable.empty();
        } catch (RealmException exception) {
            mRealm.cancelTransaction();
            return Observable.error(exception);
        }
    }

    @Override
    public Observable deleteMuscle(@NonNull String muscleId) {
        try {
            mRealm.beginTransaction();
            mRealm.where(Muscle.class)
                    .equalTo("id", muscleId)
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
