package su.dreamteam.lonja.data.source.local;

import android.support.annotation.NonNull;

import io.realm.Realm;
import io.realm.RealmResults;
import rx.Observable;
import su.dreamteam.lonja.data.model.MuscleGroup;
import su.dreamteam.lonja.data.source.MuscleGroupsDataSource;

public final class MuscleGroupsLocalDataSource implements MuscleGroupsDataSource {

    private Realm mRealm;

    private static MuscleGroupsLocalDataSource INSTANCE;

    private MuscleGroupsLocalDataSource() {
        mRealm = Realm.getDefaultInstance();
    }

    public static MuscleGroupsLocalDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new MuscleGroupsLocalDataSource();
        }
        return INSTANCE;
    }

    @Override
    public Observable<RealmResults<MuscleGroup>> getMuscleGroups() {
        return mRealm.where(MuscleGroup.class)
                .findAllAsync()
                .asObservable();
    }

    @Override
    public Observable<MuscleGroup> getMuscleGroup(String muscleGroupId) {
        return null;
    }

    @Override
    public Observable<MuscleGroup> getMuscleGroup(int muscleGroup) {
        switch (muscleGroup) {
            case MuscleGroup.ABS: {

            }
            case MuscleGroup.BACK: {

            }
            case MuscleGroup.BICEPS: {

            }
            case MuscleGroup.CALVES: {

            }
            case MuscleGroup.CHEST: {

            }
            case MuscleGroup.FOREARM: {

            }
            case MuscleGroup.GLUTES: {

            }
            case MuscleGroup.SHOULDER: {

            }
            case MuscleGroup.THIGHS: {

            }
            case MuscleGroup.TRICEPS: {

            }
        }
        return null;
    }

    @Override
    public Observable saveMuscleGroup(@NonNull MuscleGroup muscleGroup) {
        return null;
    }

    @Override
    public Observable deleteMuscleGroup(@NonNull String muscleGroupId) {
        return null;
    }
}
