package su.dreamteam.lonja.trainingdiaryfinal.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import org.parceler.Parcels;

import su.dreamteam.lonja.data.DataManager;
import su.dreamteam.lonja.data.model.Measurement;
import su.dreamteam.lonja.trainingdiaryfinal.ui.activity.AddEditMeasurementActivity;

import static com.google.common.base.Preconditions.checkNotNull;

public class MeasurementItemViewModel extends BaseObservable implements ViewModel {

    private Context mContext;

    private DataManager mDataManager;

    public final ObservableField<Measurement> measurement = new ObservableField<>();

    public MeasurementItemViewModel(@NonNull Context context,
                                    @NonNull DataManager dataManager,
                                    @NonNull Measurement measurement) {
        mContext = checkNotNull(context);
        mDataManager = checkNotNull(dataManager);
        checkNotNull(measurement);
        this.measurement.set(measurement);
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }

    public void onMeasurementClick() {
        Intent intent = new Intent(mContext, AddEditMeasurementActivity.class);
        intent.putExtra(AddEditMeasurementActivity.ARG_MEASUREMENT_EXTRA, measurement.get().getId());
        mContext.startActivity(intent);
    }

    public void omMeasurementDeleteClick() {
        mDataManager.deleteMeasurement(measurement.get().getId());
    }

    public void onMeasurementShareClick() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, measurement.get().toString());
        intent.setType("text/plain");
        mContext.startActivity(intent);
    }
}