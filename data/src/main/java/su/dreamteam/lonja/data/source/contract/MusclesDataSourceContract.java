package su.dreamteam.lonja.data.source.contract;

import android.support.annotation.NonNull;

import io.realm.RealmResults;
import rx.Observable;
import su.dreamteam.lonja.data.model.Muscle;
import su.dreamteam.lonja.data.source.local.RealmLocalDataSource;

public interface MusclesDataSourceContract {

    interface MusclesRealmDataSource extends MusclesDataSource, RealmLocalDataSource {

    }

    interface MusclesDataSource {

        Observable<RealmResults<Muscle>> getMuscles();

        Observable<Muscle> getMuscle(String muscleId);

        Observable<Muscle> getMuscle(int muscle);

        Observable saveMuscle(@NonNull Muscle muscle);

        Observable deleteMuscle(@NonNull String muscleId);

    }

}
