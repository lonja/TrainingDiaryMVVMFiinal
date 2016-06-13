package su.dreamteam.lonja.trainingdiaryfinal.viewmodel;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableField;
import android.databinding.adapters.TextViewBindingAdapter;
import android.support.annotation.NonNull;

import org.greenrobot.eventbus.EventBus;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import rx.subscriptions.CompositeSubscription;
import su.dreamteam.lonja.data.DataManager;
import su.dreamteam.lonja.data.model.Account;
import su.dreamteam.lonja.trainingdiaryfinal.event.HeightValidationEvent;
import su.dreamteam.lonja.trainingdiaryfinal.event.NameValidationEvent;
import su.dreamteam.lonja.trainingdiaryfinal.event.WeightValidationEvent;
import su.dreamteam.lonja.trainingdiaryfinal.ui.activity.AccountInfoActivity;

import static com.google.common.base.Preconditions.checkNotNull;

public class AccountWizardViewModel extends BaseObservable implements ViewModel {

    private DataManager mDataManager;

    public final ObservableField<Account> account = new ObservableField<>();

    private CompositeSubscription mSubscriptions;

    private Context mContext;

    private Pattern mPattern;

    private Matcher mMatcher;

    private final String NAME_PATTERN = "^[\\p{L}]+$";

    public TextViewBindingAdapter.AfterTextChanged heightChanged = editable -> {
        Double value = null;
        if (Objects.equals(editable.toString(), "")) {
            EventBus.getDefault().post(new HeightValidationEvent(false));
            return;
        } else {
            value = Double.parseDouble(editable.toString());
        }
        if (value <= 250 && value >= 70) {
            EventBus.getDefault().post(new HeightValidationEvent(true));
            return;
        }
        EventBus.getDefault().post(new HeightValidationEvent(false));
    };

    public TextViewBindingAdapter.AfterTextChanged weightChanged = editable -> {
        Double value = null;
        if (Objects.equals(editable.toString(), "")) {
            EventBus.getDefault().post(new HeightValidationEvent(false));
            return;
        } else {
            value = Double.parseDouble(editable.toString());
        }
        if (value <= 250 && value >= 30) {
            EventBus.getDefault().post(new WeightValidationEvent(true));
            return;
        }
        EventBus.getDefault().post(new WeightValidationEvent(false));
    };

    public TextViewBindingAdapter.AfterTextChanged nameChanged = editable -> {
        String value = editable.toString();
        mPattern = Pattern.compile(NAME_PATTERN);
        mMatcher = mPattern.matcher(value);
        if (mMatcher.matches()) {
            EventBus.getDefault().post(new NameValidationEvent(true));
            return;
        }
        EventBus.getDefault().post(new NameValidationEvent(false));
    };

    public AccountWizardViewModel(@NonNull DataManager dataManager,
                                  @NonNull Context context) {
        mDataManager = checkNotNull(dataManager);
        mContext = checkNotNull(context);
        mSubscriptions = new CompositeSubscription();
        account.set(new Account());
    }

    public void setBirthDate() {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(account.get().getBirthDate());
        DatePickerDialog datePickerDialog = new DatePickerDialog(mContext,
                ((datePicker, year, monthOfYear, dayOfMonth) -> {
                    calendar.set(Calendar.YEAR, year);
                    calendar.set(Calendar.MONTH, monthOfYear);
                    calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    account.get().setBirthDate(calendar.getTime());
                    account.notifyChange();
                }), calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    private void showProfile() {
        Intent intent = new Intent(mContext, AccountInfoActivity.class);
        mContext.startActivity(intent);
    }

    // FIXME: 09.06.2016 exception when done editing and account is empty
    public void doneEditing() {
        mDataManager.saveAccount(account.get());
        showProfile();
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {
        mSubscriptions.clear();
    }

    @Bindable
    public boolean isMan() {
        return Objects.equals(account.get().getGender(), "male");
    }
}