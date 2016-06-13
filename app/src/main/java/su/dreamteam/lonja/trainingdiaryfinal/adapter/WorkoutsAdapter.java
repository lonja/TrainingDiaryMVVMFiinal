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
import su.dreamteam.lonja.data.model.Training;
import su.dreamteam.lonja.data.repository.TrainingsRepository;
import su.dreamteam.lonja.data.source.local.TrainingsLocalDataSource;
import su.dreamteam.lonja.trainingdiaryfinal.R;
import su.dreamteam.lonja.trainingdiaryfinal.databinding.ItemTrainingBinding;
import su.dreamteam.lonja.trainingdiaryfinal.viewmodel.WorkoutItemViewModel;

public class WorkoutsAdapter extends RecyclerView.Adapter<WorkoutsAdapter.WorkoutViewHolder> {

    private Context mContext;

    private List<Training> mWorkouts;

    public WorkoutsAdapter(List<Training> workouts) {
        mWorkouts = workouts;
    }

    @Override
    public WorkoutsAdapter.WorkoutViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(mContext);
        ViewDataBinding mBinding = DataBindingUtil.inflate(inflater, R.layout.item_training, parent, false);
        return new WorkoutsAdapter.WorkoutViewHolder(mBinding.getRoot());
    }

    @Override
    public void onBindViewHolder(WorkoutsAdapter.WorkoutViewHolder holder, int position) {
        WorkoutItemViewModel viewModel = new WorkoutItemViewModel(mContext,
                TrainingsRepository.getInstance(TrainingsLocalDataSource.getInstance()));
        holder.binding.setViewModel(viewModel);
    }

    @Override
    public int getItemCount() {
        return mWorkouts != null ? mWorkouts.size() : 0;
    }

    private void setData(List<Training> workouts) {
        mWorkouts = workouts;
        notifyDataSetChanged();
    }

    public void replaceData(RealmResults<Training> workouts) {
        setData(workouts);
    }

    class WorkoutViewHolder extends RecyclerView.ViewHolder {

        ItemTrainingBinding binding;

        WorkoutViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}
