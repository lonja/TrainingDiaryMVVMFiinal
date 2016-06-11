package su.dreamteam.lonja.trainingdiaryfinal.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import su.dreamteam.lonja.data.model.Exercise;
import su.dreamteam.lonja.data.repository.ExercisesRepository;

import static com.google.common.base.Preconditions.checkNotNull;

public class ExerciseItemViewModel extends BaseObservable {

    private Context mContext;

    private ExercisesRepository mDataManager;

    public final ObservableField<Exercise> exercise = new ObservableField<>();

    public ExerciseItemViewModel(@NonNull Context context,
                                 @NonNull ExercisesRepository dataManager,
                                 @NonNull Exercise exercise) {
        mContext = checkNotNull(context);
        mDataManager = checkNotNull(dataManager);
        checkNotNull(exercise);
        this.exercise.set(exercise);
    }

    public void onExerciseClick() {

    }
}
