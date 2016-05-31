package su.dreamteam.lonja.trainingdiaryfinal.viewmodel;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;
import su.dreamteam.lonja.data.DataManager;
import su.dreamteam.lonja.data.RealmHelper;
import su.dreamteam.lonja.data.model.Measurement;
import su.dreamteam.lonja.trainingdiaryfinal.R;
import su.dreamteam.lonja.trainingdiaryfinal.databinding.DialogCommentBinding;
import su.dreamteam.lonja.trainingdiaryfinal.ui.activity.AddEditMeasurementActivity;
import su.dreamteam.lonja.trainingdiaryfinal.ui.activity.MeasurementsActivity;

import static com.google.common.base.Preconditions.checkNotNull;

public class MeasurementDetailViewModel extends BaseObservable implements ViewModel {

    private DataManager mDataManager;

    private RealmHelper mRealmHelper;

    private String mMeasurementId;

    public final ObservableField<Measurement> measurement = new ObservableField<>();

    private CompositeSubscription mSubscriptions;

    private Context mContext;

    private boolean isNewMeasurement;

    private LayoutInflater mInflater;

    public MeasurementDetailViewModel(DataManager dataManager, RealmHelper realmHelper,
                                      String measurementId,
                                      Context context,
                                      LayoutInflater inflater) {
        mRealmHelper = checkNotNull(realmHelper);
        mDataManager = checkNotNull(dataManager);
        mContext = checkNotNull(context);
        mInflater = checkNotNull(inflater);
        mMeasurementId = measurementId;
        mSubscriptions = new CompositeSubscription();
    }

    @Override
    public void subscribe() {
        Subscription subscription = mDataManager.getMeasurement(mMeasurementId)
                .map(measurement -> {
                    if (measurement == null) {
                        startEditing();
                        Measurement newMeasurement = mRealmHelper.createRealmObject(Measurement.class);
                        newMeasurement.setDate(new Date());
                        isNewMeasurement = true;
                        return newMeasurement;
                    }
                    isNewMeasurement = false;
                    return measurement;
                })
                .filter(measurement -> measurement.isLoaded())
                .doOnNext(measurement -> {
                    setMeasurement(measurement);
                    startEditing();
                })
                .doOnError(this::showError)
                .subscribe();
        mSubscriptions.add(subscription);
    }

    @Override
    public void unsubscribe() {
        mSubscriptions.clear();
    }

    private void showMeasurements() {
        Intent intent = new Intent(mContext, MeasurementsActivity.class);
        mContext.startActivity(intent);
        AddEditMeasurementActivity activity = (AddEditMeasurementActivity) mContext;
        activity.finish();
    }

    public void doneEditing() {
        try {
            if (!measurement.get().isNotEmpty()) {
                return;
            }
            mRealmHelper.commitTransaction();
            showMeasurements();
        } catch (Exception e) {
            showError(e);
        }
    }

    public void cancelEditing() {
        try {
            mRealmHelper.cancelTransaction();
            showMeasurements();
        } catch (Exception e) {
            showError(e);
        }
    }

    private void startEditing() {
        try {
            mRealmHelper.beginTransaction();
        } catch (Exception e) {
            showError(e);
        }
    }

    public void setDateTime() {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(measurement.get().getDate());
        DatePickerDialog datePickerDialog = new DatePickerDialog(mContext,
                ((datePicker, year, monthOfYear, dayOfMonth) -> {
                    calendar.set(Calendar.YEAR, year);
                    calendar.set(Calendar.MONTH, monthOfYear);
                    calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    measurement.get().setDate(calendar.getTime());
                    TimePickerDialog timePickerDialog = new TimePickerDialog(mContext,
                            ((timePicker, hourOfDay, minute) -> {
                                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                                calendar.set(Calendar.MINUTE, minute);
                                measurement.get().setDate(calendar.getTime());
                                measurement.notifyChange();
                            }), calendar.get(Calendar.HOUR_OF_DAY),
                            calendar.get(Calendar.MINUTE),
                            true);
                    timePickerDialog.show();
                }), calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    public void setComment() {
        DialogCommentBinding binding = DataBindingUtil.inflate(mInflater, R.layout.dialog_comment, null, false);
        EditText commentEditText = binding.dialogCommentEdit;

        binding.setComment(measurement.get().getComment());

        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle(R.string.comment)
                .setView(binding.getRoot())
                .setCancelable(true)
                .setPositiveButton(R.string.add, (dialog, which) -> {
                            measurement.get().setComment(commentEditText.getText().toString());
                            measurement.notifyChange();
                        }
                )
                .setNegativeButton(R.string.cancel, (dialog, which) -> dialog.dismiss())
                .show();
    }

    private void setMeasurement(Measurement measurement) {
        this.measurement.set(measurement);
    }

    @Bindable
    public boolean isNewMeasurement() {
        return isNewMeasurement;
    }

    private void showError(Throwable throwable) {
        showMessage(throwable.getMessage());
    }

    private void showMessage(String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_LONG).show();
    }
}