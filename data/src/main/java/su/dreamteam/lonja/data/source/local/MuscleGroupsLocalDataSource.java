package su.dreamteam.lonja.data.source.local;

import android.support.annotation.NonNull;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.exceptions.RealmException;
import rx.Observable;
import su.dreamteam.lonja.data.model.MuscleGroup;
import su.dreamteam.lonja.data.source.contract.MuscleGroupsDataSourceContract;

public final class MuscleGroupsLocalDataSource extends BaseRealmDataSource implements MuscleGroupsDataSourceContract.MuscleGroupsRealmDataSource {

    private static MuscleGroupsLocalDataSource INSTANCE;
    private Realm mRealm;

    private MuscleGroupsLocalDataSource() {

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
        MuscleGroup muscleGroup = mRealm.where(MuscleGroup.class)
                .equalTo("id", muscleGroupId)
                .findFirst();
        if (muscleGroup != null) {
            return muscleGroup.asObservable();
        }
        return Observable.error(new RealmException("Muscle group not found"));
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
        return executeTransactionAsync(mRealm, realm -> realm.copyToRealmOrUpdate(muscleGroup));
    }

    @Override
    public Observable deleteMuscleGroup(@NonNull String muscleGroupId) {
        return executeTransactionAsync(mRealm, realm -> realm.where(MuscleGroup.class)
                .equalTo("id", muscleGroupId)
                .findFirst()
                .deleteFromRealm());
    }

    @Override
    public void openConnection() {
        if (mRealm != null) {
            return;
        }
        mRealm = Realm.getDefaultInstance();
    }

    @Override
    public void closeConnection() {
        if (mRealm == null) {
            return;
        }
        closeConnection(mRealm);
    }
}
