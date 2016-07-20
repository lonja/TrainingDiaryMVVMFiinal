package su.dreamteam.lonja.trainingdiaryfinal.viewmodel;

import android.content.Context;
import android.databinding.ObservableField;

import su.dreamteam.lonja.data.model.Exercise;
import su.dreamteam.lonja.data.repository.ExercisesRepository;

public class ExerciseChoiceViewModel {

    private Context mContext;

    private ExercisesRepository mRepo;

    public final ObservableField<Exercise> exercise = new ObservableField<>();

    public ExerciseChoiceViewModel(Context context, ExercisesRepository repository, Exercise exercise) {
        mContext = context;
        mRepo = repository;
        this.exercise.set(exercise);
    }

    public void onExerciseSelected() {

    }


}
