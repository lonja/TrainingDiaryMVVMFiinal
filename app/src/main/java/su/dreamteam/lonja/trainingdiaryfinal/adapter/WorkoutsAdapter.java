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
import su.dreamteam.lonja.trainingdiaryfinal.R;
import su.dreamteam.lonja.trainingdiaryfinal.databinding.ItemTrainingBinding;

public class WorkoutsAdapter extends RecyclerView.Adapter<WorkoutsAdapter.WorkoutViewHolder> {

    private Context mContext;

    private List<Training> mTrainings;

    public WorkoutsAdapter(List<Training> trainings) {
        mTrainings = trainings;
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
        holder.binding.setTraining(mTrainings.get(position));
    }

    @Override
    public int getItemCount() {
        return mTrainings != null ? mTrainings.size() : 0;
    }

    private void setData(List<Training> trainings) {
        mTrainings = trainings;
        notifyDataSetChanged();
    }

    public void replaceData(RealmResults<Training> trainings) {
        setData(trainings);
    }

    class WorkoutViewHolder extends RecyclerView.ViewHolder {

        ItemTrainingBinding binding;

        WorkoutViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}
