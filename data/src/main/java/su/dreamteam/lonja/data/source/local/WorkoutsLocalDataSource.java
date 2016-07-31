package su.dreamteam.lonja.data.source.local;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.exceptions.RealmException;
import rx.Observable;
import su.dreamteam.lonja.data.model.Workout;
import su.dreamteam.lonja.data.source.contract.WorkoutsDataSourceContract;


public final class WorkoutsLocalDataSource extends BaseRealmDataSource implements WorkoutsDataSourceContract.WorkoutsRealmDataSource {

    private static WorkoutsLocalDataSource INSTANCE;
    private Realm mRealm;

    private WorkoutsLocalDataSource() {

    }

    public static WorkoutsLocalDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new WorkoutsLocalDataSource();
        }
        return INSTANCE;
    }

    @Nullable
    @Override
    public Observable<RealmResults<Workout>> getWorkouts() {
        return mRealm.where(Workout.class)
                .findAllAsync()
                .asObservable();
    }

    @Nullable
    @Override
    public Observable<Workout> getWorkout(String trainingId) {
        Workout workout = mRealm.where(Workout.class)
                .equalTo("id", trainingId)
                .findFirst();
        if (workout != null) {
            return workout.asObservable();
        }
        return Observable.error(new RealmException("Exercise not found"));
    }

    @Override
    public Observable saveWorkout(@NonNull Workout workout) {
        return executeTransactionAsync(mRealm, realm -> realm.copyToRealmOrUpdate(workout));
    }

    @Override
    public Observable deleteWorkout(@NonNull String trainingId) {
        return executeTransactionAsync(mRealm, realm -> realm.where(Workout.class)
                .equalTo("id", trainingId)
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