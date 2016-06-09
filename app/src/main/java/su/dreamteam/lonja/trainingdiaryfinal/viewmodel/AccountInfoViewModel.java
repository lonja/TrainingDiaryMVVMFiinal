package su.dreamteam.lonja.trainingdiaryfinal.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.ObservableField;
import android.util.Log;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;
import su.dreamteam.lonja.data.DataManager;
import su.dreamteam.lonja.data.RealmHelper;
import su.dreamteam.lonja.data.model.Account;

import static com.google.common.base.Preconditions.checkNotNull;

public class AccountInfoViewModel extends BaseObservable implements ViewModel {

    private DataManager mDataManager;

    public final ObservableField<Account> account = new ObservableField<>();

    private CompositeSubscription mSubscriptions;

    private Context mContext;

    public AccountInfoViewModel(DataManager dataManager, Context context) {
        mDataManager = checkNotNull(dataManager);
        mContext = checkNotNull(context);
        mSubscriptions = new CompositeSubscription();
    }

    @Override
    public void subscribe() {
        Subscription subscription = mDataManager.getAccount()
                .filter(account -> account.isLoaded())
                .first()
                .doOnNext((account) -> {
                    Log.e("Frodo", account.toString());
                    setAccount(account);
                })
                .subscribe();
        mSubscriptions.add(subscription);
    }

    private void retrieveAccount() {

    }

    @Override
    public void unsubscribe() {
        mSubscriptions.clear();
    }

    private void setAccount(Account account) {
        this.account.set(account);
    }

    public void editAccount(Account account) {

    }
}