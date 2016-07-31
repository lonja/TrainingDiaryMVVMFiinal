package su.dreamteam.lonja.data.source.local;

import android.support.annotation.NonNull;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.exceptions.RealmException;
import rx.Observable;
import su.dreamteam.lonja.data.model.Muscle;
import su.dreamteam.lonja.data.source.MusclesDataSource;

public final class MusclesRealmLocalDataSource extends RealmLocalDataSource implements MusclesDataSource {

    private static MusclesRealmLocalDataSource INSTANCE;
    private Realm mRealm;

    private MusclesRealmLocalDataSource() {
        mRealm = Realm.getDefaultInstance();
    }

    public static MusclesRealmLocalDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new MusclesRealmLocalDataSource();
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
        return Observable.error(new RealmException("Muscle not found"));
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
        return executeTransactionAsync(mRealm, realm -> realm.copyToRealmOrUpdate(muscle));
    }

    @Override
    public Observable deleteMuscle(@NonNull String muscleId) {
        return executeTransactionAsync(mRealm, realm -> realm.where(Muscle.class)
                .equalTo("id", muscleId)
                .findFirst()
                .deleteFromRealm());
    }
}
