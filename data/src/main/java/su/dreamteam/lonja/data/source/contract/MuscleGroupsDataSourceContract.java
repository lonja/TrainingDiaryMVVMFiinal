package su.dreamteam.lonja.data.source.contract;

import android.support.annotation.NonNull;

import io.realm.RealmResults;
import rx.Observable;
import su.dreamteam.lonja.data.model.MuscleGroup;
import su.dreamteam.lonja.data.source.local.RealmLocalDataSource;

public interface MuscleGroupsDataSourceContract {

    interface MuscleGroupsRealmDataSource extends MuscleGroupsDataSource, RealmLocalDataSource {

    }

    interface MuscleGroupsDataSource {

        Observable<RealmResults<MuscleGroup>> getMuscleGroups();

        Observable<MuscleGroup> getMuscleGroup(String muscleGroupId);

        Observable<MuscleGroup> getMuscleGroup(int muscleGroup);

        Observable saveMuscleGroup(@NonNull MuscleGroup muscleGroup);

        Observable deleteMuscleGroup(@NonNull String muscleGroupId);

    }

}
