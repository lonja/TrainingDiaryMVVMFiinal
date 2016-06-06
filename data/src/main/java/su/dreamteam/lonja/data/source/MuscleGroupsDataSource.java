package su.dreamteam.lonja.data.source;

import android.support.annotation.NonNull;

import io.realm.RealmResults;
import rx.Observable;
import su.dreamteam.lonja.data.model.MuscleGroup;

public interface MuscleGroupsDataSource {

    Observable<RealmResults<MuscleGroup>> getMuscleGroups();

    Observable<MuscleGroup> getMuscleGroup(String muscleGroupId);

    Observable<MuscleGroup> getMuscleGroup(int muscleGroup);

    Observable<Void> saveMuscleGroup(@NonNull MuscleGroup muscleGroup);

    Observable<Void> deleteMuscleGroup(@NonNull String muscleGroupId);

}