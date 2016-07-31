package su.dreamteam.lonja.data.source.contract;

import android.support.annotation.NonNull;

import io.realm.RealmResults;
import rx.Observable;
import su.dreamteam.lonja.data.model.Workout;
import su.dreamteam.lonja.data.source.local.RealmLocalDataSource;

public interface WorkoutsDataSourceContract {

    interface WorkoutsRealmDataSource extends WorkoutsDataSource, RealmLocalDataSource {

    }

    interface WorkoutsDataSource {

        Observable<RealmResults<Workout>> getWorkouts();

        Observable<Workout> getWorkout(String trainingId);

        Observable saveWorkout(@NonNull Workout workout);

        Observable deleteWorkout(@NonNull String trainingId);
    }
}
