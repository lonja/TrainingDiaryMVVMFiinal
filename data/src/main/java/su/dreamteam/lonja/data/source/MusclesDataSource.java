package su.dreamteam.lonja.data.source;

import android.support.annotation.NonNull;

import io.realm.RealmResults;
import rx.Observable;
import su.dreamteam.lonja.data.model.Muscle;

public interface MusclesDataSource {

    Observable<RealmResults<Muscle>> getMuscles();

    Observable<Muscle> getMuscle(String muscleId);

    Observable<Muscle> getMuscle(int muscle);

    Observable<Void> saveMuscle(@NonNull Muscle muscle);

    Observable<Void> deleteMuscle(@NonNull String muscleId);

}