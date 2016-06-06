package su.dreamteam.lonja.data.source.local;

import android.support.annotation.NonNull;

import io.realm.RealmResults;
import rx.Observable;
import su.dreamteam.lonja.data.model.Muscle;
import su.dreamteam.lonja.data.source.MusclesDataSource;

public final class MusclesLocalDataSource implements MusclesDataSource {


    @Override
    public Observable<RealmResults<Muscle>> getMuscles() {
        return null;
    }

    @Override
    public Observable<Muscle> getMuscle(String muscleId) {
        return null;
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
    public Observable<Void> saveMuscle(@NonNull Muscle muscle) {
        return null;
    }

    @Override
    public Observable<Void> deleteMuscle(@NonNull String muscleId) {
        return null;
    }
}
