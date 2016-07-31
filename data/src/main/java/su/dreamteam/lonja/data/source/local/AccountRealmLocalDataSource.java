package su.dreamteam.lonja.data.source.local;

import io.realm.Realm;
import rx.Observable;
import su.dreamteam.lonja.data.model.Account;
import su.dreamteam.lonja.data.source.AccountDataSource;

public final class AccountRealmLocalDataSource extends RealmLocalDataSource implements AccountDataSource {

    private Realm mRealm;

    private static AccountRealmLocalDataSource INSTANCE;

    private AccountRealmLocalDataSource() {
        mRealm = Realm.getDefaultInstance();
    }

    public static AccountRealmLocalDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AccountRealmLocalDataSource();
        }
        return INSTANCE;
    }

    @Override
    public Observable<Account> getAccount() {
        Account account = mRealm.where(Account.class)
                .findFirst();
        if (account != null) {
            return account.asObservable();
        }
        return Observable.empty();
    }

    @Override
    public Observable saveAccount(Account account) {
        return executeTransactionAsync(mRealm, realm -> realm.copyToRealmOrUpdate(account));
    }

    @Override
    public Observable deleteAccount() {
        return executeTransactionAsync(mRealm, realm -> realm.delete(Account.class));
    }
}