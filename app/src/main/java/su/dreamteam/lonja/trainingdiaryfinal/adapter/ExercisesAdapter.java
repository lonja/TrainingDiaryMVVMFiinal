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
import su.dreamteam.lonja.data.source.local.ExercisesLocalDataSource;
import su.dreamteam.lonja.trainingdiaryfinal.R;
import su.dreamteam.lonja.trainingdiaryfinal.databinding.ItemExerciseBinding;
import su.dreamteam.lonja.trainingdiaryfinal.viewmodel.ExerciseItemViewModel;

public class ExercisesAdapter extends RecyclerView.Adapter<ExercisesAdapter.ExerciseViewHolder> {

    private Context mContext;

    private List<Exercise> mExercises;

    public ExercisesAdapter(List<Exercise> exercises) {
        mExercises = exercises;
    }

    @Override
    public ExercisesAdapter.ExerciseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(mContext);
        ViewDataBinding mBinding = DataBindingUtil.inflate(inflater, R.layout.item_exercise, parent, false);
        return new ExercisesAdapter.ExerciseViewHolder(mBinding.getRoot());
    }

    @Override
    public void onBindViewHolder(ExercisesAdapter.ExerciseViewHolder holder, int position) {
        ExerciseItemViewModel viewModel = new ExerciseItemViewModel(mContext,
                ExercisesRepository.getInstance(ExercisesLocalDataSource.getInstance()),
                mExercises.get(position));
        holder.binding.setViewModel(viewModel);
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

    class ExerciseViewHolder extends RecyclerView.ViewHolder {

        ItemExerciseBinding binding;

        ExerciseViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}