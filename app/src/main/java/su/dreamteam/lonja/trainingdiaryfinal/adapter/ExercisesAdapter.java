package su.dreamteam.lonja.trainingdiaryfinal.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import io.realm.RealmResults;
import su.dreamteam.lonja.data.model.Exercise;
import su.dreamteam.lonja.data.repository.ExercisesRepository;
import su.dreamteam.lonja.data.source.local.ExercisesRealmLocalDataSource;
import su.dreamteam.lonja.trainingdiaryfinal.R;
import su.dreamteam.lonja.trainingdiaryfinal.databinding.ItemExerciseBinding;
import su.dreamteam.lonja.trainingdiaryfinal.databinding.ItemExerciseCheckableBinding;
import su.dreamteam.lonja.trainingdiaryfinal.viewmodel.ExerciseChoiceViewModel;
import su.dreamteam.lonja.trainingdiaryfinal.viewmodel.ExerciseItemViewModel;

public class ExercisesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int TYPE_ITEM = 0;

    public static final int TYPE_ITEM_CHOICE = 1;

    private Context mContext;

    private List<Exercise> mExercises;

    private int mType;

    public ExercisesAdapter(List<Exercise> exercises, int type) {
        mExercises = exercises;
        mType = type;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(mContext);
        ViewDataBinding binding;
        switch (mType) {
            case TYPE_ITEM:
                binding = DataBindingUtil.inflate(inflater, R.layout.item_exercise, parent, false);
                return new ExerciseViewHolder(binding.getRoot());
            case TYPE_ITEM_CHOICE:
                binding = DataBindingUtil.inflate(inflater, R.layout.item_exercise_checkable, parent, false);
                return new ExerciseChoiceViewHolder(binding.getRoot());
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ExerciseViewHolder) {
            ExerciseItemViewModel viewModel = new ExerciseItemViewModel(mContext,
                    ExercisesRepository.getInstance(ExercisesRealmLocalDataSource.getInstance()),
                    mExercises.get(position));
            ((ExerciseViewHolder) holder).binding.setViewModel(viewModel);
        } else if (holder instanceof ExerciseChoiceViewHolder) {
            ExerciseChoiceViewModel viewModel = new ExerciseChoiceViewModel(mContext,
                    ExercisesRepository.getInstance(ExercisesRealmLocalDataSource.getInstance()),
                    mExercises.get(position));
            ((ExerciseChoiceViewHolder) holder).binding.setViewModel(viewModel);
        }

    }

    @Override
    public int getItemCount() {
        return mExercises != null ? mExercises.size() : 0;
    }

    private void setData(List<Exercise> exercises) {
        mExercises = exercises;
        notifyDataSetChanged();
    }

    public void replaceData(RealmResults<Exercise> exercises) {
        setData(exercises);
    }

    private class ExerciseViewHolder extends RecyclerView.ViewHolder {

        ItemExerciseBinding binding;

        ExerciseViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }

    private class ExerciseChoiceViewHolder extends RecyclerView.ViewHolder {

        ItemExerciseCheckableBinding binding;

        ExerciseChoiceViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}