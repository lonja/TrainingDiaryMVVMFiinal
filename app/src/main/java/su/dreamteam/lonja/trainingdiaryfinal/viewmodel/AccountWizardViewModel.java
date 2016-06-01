package su.dreamteam.lonja.trainingdiaryfinal.viewmodel;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import java.util.Calendar;
import java.util.GregorianCalendar;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;
import su.dreamteam.lonja.data.DataManager;
import su.dreamteam.lonja.data.RealmHelper;
import su.dreamteam.lonja.data.model.Account;
import su.dreamteam.lonja.trainingdiaryfinal.ui.activity.AccountInfoActivity;

import static com.google.common.base.Preconditions.checkNotNull;

public class AccountWizardViewModel implements ViewModel {

    private DataManager mDataManager;

    private RealmHelper mRealmHelper;

    public final ObservableField<Account> account = new ObservableField<>();

    private CompositeSubscription mSubscriptions;

    private Context mContext;

    public AccountWizardViewModel(@NonNull DataManager dataManager,
                                  @NonNull RealmHelper realmHelper,
                                  @NonNull Context context) {
        mDataManager = checkNotNull(dataManager);
        mRealmHelper = checkNotNull(realmHelper);
        mContext = checkNotNull(context);
        mSubscriptions = new CompositeSubscription();
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

    public void showProfile() {
        Intent intent = new Intent(mContext, AccountInfoActivity.class);
        mContext.startActivity(intent);
    }

    public void doneEditing() {
        try {
            if (!account.get().isNotEmpty()) {
                return;
            }
            mRealmHelper.commitTransaction();
        } catch (Exception e) {
            showError(e);
        }
    }

    public void cancelEditing() {
        try {
            mRealmHelper.cancelTransaction();
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

    private void showError(Throwable e) {

    }

    @Override
    public void subscribe() {
        Subscription subscription = mDataManager.getAccount()
                .map(account -> {
                    startEditing();
                    if (account == null) {
                        Account newAccount = mRealmHelper.createRealmObject(Account.class);
                        Calendar calendar = new GregorianCalendar(1990, 5, 15);
                        newAccount.setBirthDate(calendar.getTime());
                        return newAccount;
                    }
                    return account;
                })
                .filter(account -> account.isLoaded())
                .doOnNext(this.account::set)
                .doOnError(this::showError)
                .subscribe();
        mSubscriptions.add(subscription);
    }

    @Override
    public void unsubscribe() {
        mSubscriptions.clear();
    }
}