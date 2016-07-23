package su.dreamteam.lonja.data.source.local;

import io.realm.Realm;
import io.realm.exceptions.RealmException;
import rx.Observable;
import su.dreamteam.lonja.data.model.Account;
import su.dreamteam.lonja.data.source.AccountDataSource;

public final class AccountLocalDataSource extends LocalDataSource implements AccountDataSource {

    private Realm mRealm;

    private static AccountLocalDataSource INSTANCE;

    private AccountLocalDataSource() {
        mRealm = Realm.getDefaultInstance();
    }

    public static AccountLocalDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AccountLocalDataSource();
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
        return executeTransactionAsync(realm -> realm.copyToRealmOrUpdate(account));
    }

    @Override
    public Observable deleteAccount() {
        return executeTransactionAsync(realm -> realm.delete(Account.class));
    }
}