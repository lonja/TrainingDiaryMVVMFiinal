package su.dreamteam.lonja.data.source.local;

import io.realm.Realm;
import rx.Observable;
import su.dreamteam.lonja.data.model.Account;
import su.dreamteam.lonja.data.source.contract.AccountDataSourceContract;

public final class AccountLocalDataSource extends BaseRealmDataSource implements AccountDataSourceContract.AccountRealmDataSource {

    private Realm mRealm;

    private static AccountLocalDataSource INSTANCE;

    private AccountLocalDataSource() {

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
        return executeTransactionAsync(mRealm, realm -> realm.copyToRealmOrUpdate(account));
    }

    @Override
    public Observable deleteAccount() {
        return executeTransactionAsync(mRealm, realm -> realm.delete(Account.class));
    }

    @Override
    public void openConnection() {
        if (mRealm != null) {
            return;
        }
        mRealm = Realm.getDefaultInstance();
    }

    @Override
    public void closeConnection() {
        if (mRealm == null) {
            return;
        }
        closeConnection(mRealm);
    }
}