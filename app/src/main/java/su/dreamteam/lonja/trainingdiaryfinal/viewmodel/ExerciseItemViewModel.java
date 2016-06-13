package su.dreamteam.lonja.trainingdiaryfinal.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import su.dreamteam.lonja.data.model.Exercise;
import su.dreamteam.lonja.data.repository.ExercisesRepository;
import su.dreamteam.lonja.trainingdiaryfinal.ui.activity.ExerciseDetailActivity;

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
        Intent intent = new Intent(mContext, ExerciseDetailActivity.class);
        intent.putExtra(ExerciseDetailActivity.ARG_EXERCISE_EXTRA, exercise.get().getId());
        mContext.startActivity(intent);
    }
}
