package su.dreamteam.lonja.trainingdiaryfinal.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.ObservableField;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;
import su.dreamteam.lonja.data.DataManager;
import su.dreamteam.lonja.data.RealmHelper;
import su.dreamteam.lonja.data.model.Account;

import static com.google.common.base.Preconditions.checkNotNull;

public class AccountViewModel extends BaseObservable implements ViewModel {

    private DataManager mDataManager;

    public final ObservableField<Account> account = new ObservableField<>();

    private RealmHelper mRealmHelper;

    private CompositeSubscription mSubscriptions;

    private Context mContext;

    public AccountViewModel(DataManager dataManager, RealmHelper realmHelper, Context context) {
        mDataManager = checkNotNull(dataManager);
        mRealmHelper = checkNotNull(realmHelper);
        mContext = checkNotNull(context);
        mSubscriptions = new CompositeSubscription();
    }

    @Override
    public void subscribe() {
        Subscription subscription = mDataManager.getAccount()
                .filter(account -> account.isLoaded())
                .first()
                .doOnNext(this::setAccount)
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

    private boolean isNowEditing() {
        return mRealmHelper.isInTransaction();
    }

    public void editAccount(Account account) {

    }
}