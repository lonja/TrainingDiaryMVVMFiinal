package su.dreamteam.lonja.trainingdiaryfinal.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import su.dreamteam.lonja.data.model.Training;
import su.dreamteam.lonja.data.repository.TrainingsRepository;
import su.dreamteam.lonja.trainingdiaryfinal.ui.activity.AddEditWorkoutActivity;

public class WorkoutItemViewModel extends BaseObservable {

    private Context mContext;

    private TrainingsRepository mRepo;

    public final ObservableField<Training> workout = new ObservableField<>();

    public WorkoutItemViewModel(@NonNull Context context,
                                @NonNull TrainingsRepository repo) {
        mContext = context;
        mRepo = repo;
    }

    public void onWorkoutClick() {
        Intent intent = new Intent(mContext, AddEditWorkoutActivity.class);
        intent.putExtra(AddEditWorkoutActivity.ARG_WORKOUT_EXTRA, workout.get().getId());
        mContext.startActivity(intent);
    }

    public void omMeasurementDeleteClick() {
        mRepo.deleteTraining(workout.get().getId());
    }
}