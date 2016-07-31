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
import su.dreamteam.lonja.data.DataManager;
import su.dreamteam.lonja.data.model.Measurement;
import su.dreamteam.lonja.data.source.local.AccountRealmLocalDataSource;
import su.dreamteam.lonja.data.source.local.MeasurementsRealmLocalDataSource;
import su.dreamteam.lonja.trainingdiaryfinal.R;
import su.dreamteam.lonja.trainingdiaryfinal.databinding.ItemMeasurementBinding;
import su.dreamteam.lonja.trainingdiaryfinal.viewmodel.MeasurementItemViewModel;

public class MeasurementsAdapter extends RecyclerView.Adapter<MeasurementsAdapter.MeasurementViewHolder> {

    private Context mContext;

    private List<Measurement> mMeasurements;

    public MeasurementsAdapter(List<Measurement> measurements) {
        mMeasurements = measurements;
    }

    @Override
    public MeasurementViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(mContext);
        ViewDataBinding mBinding = DataBindingUtil.inflate(inflater, R.layout.item_measurement, parent, false);
        return new MeasurementViewHolder(mBinding.getRoot());
    }

    @Override
    public void onBindViewHolder(MeasurementViewHolder holder, int position) {
        MeasurementItemViewModel viewModel = new MeasurementItemViewModel(
                mContext,
                DataManager.getInstance(MeasurementsRealmLocalDataSource.getInstance(),
                        AccountRealmLocalDataSource.getInstance()),
                mMeasurements.get(position));

        holder.binding.setViewModel(viewModel);
    }

    @Override
    public int getItemCount() {
        return mMeasurements != null ? mMeasurements.size() : 0;
    }

    private void setData(List<Measurement> measurements) {
        mMeasurements = measurements;
        notifyDataSetChanged();
    }

    public void replaceData(RealmResults<Measurement> measurements) {
        setData(measurements);
    }

    class MeasurementViewHolder extends RecyclerView.ViewHolder {

        ItemMeasurementBinding binding;

        MeasurementViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}