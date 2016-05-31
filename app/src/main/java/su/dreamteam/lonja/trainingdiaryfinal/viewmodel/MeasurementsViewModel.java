package su.dreamteam.lonja.trainingdiaryfinal.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.annotation.NonNull;

import io.realm.RealmResults;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;
import su.dreamteam.lonja.data.DataManager;
import su.dreamteam.lonja.trainingdiaryfinal.BR;
import su.dreamteam.lonja.trainingdiaryfinal.adapter.MeasurementsAdapter;
import su.dreamteam.lonja.trainingdiaryfinal.ui.activity.AddEditMeasurementActivity;

import static com.google.common.base.Preconditions.checkNotNull;

public class MeasurementsViewModel extends BaseObservable implements ViewModel {

    private DataManager mDataManager;

    private int mMeasurementsSize = 0;

    private CompositeSubscription mSubscription;

    private MeasurementsAdapter mAdapter;

    private Context mContext;

    public MeasurementsViewModel(@NonNull DataManager dataManager,
                                 @NonNull Context context,
                                 @NonNull MeasurementsAdapter adapter) {
        mDataManager = checkNotNull(dataManager);
        mContext = checkNotNull(context);
        mAdapter = checkNotNull(adapter);
        mSubscription = new CompositeSubscription();
    }

    @Override
    public void subscribe() {
        Subscription subscription = mDataManager.getMeasurements()
                .filter(RealmResults::isLoaded)
                .first()
                .doOnNext(measurements -> {
                    mAdapter.replaceData(measurements);
                    measurements.addChangeListener(changedData -> {
                        setMeasurementsListSize(changedData.size());
                        mAdapter.notifyDataSetChanged();
                    });
                    setMeasurementsListSize(measurements.size());
                })
                .subscribe();
        mSubscription.add(subscription);
    }

    public void addMeasurement() {
        Intent intent = new Intent(mContext, AddEditMeasurementActivity.class);
        mContext.startActivity(intent);
    }

    @Override
    public void unsubscribe() {
        mSubscription.clear();
    }

    @Bindable
    public boolean isEmpty() {
        return mMeasurementsSize == 0;
    }

    private void setMeasurementsListSize(int measurementsListSize) {
        mMeasurementsSize = measurementsListSize;
        notifyPropertyChanged(BR.empty);
    }
}