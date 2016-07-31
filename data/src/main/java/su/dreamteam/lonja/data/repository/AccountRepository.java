package su.dreamteam.lonja.data.repository;

import android.support.annotation.NonNull;

import io.realm.Realm;
import rx.Observable;
import su.dreamteam.lonja.data.model.Account;
import su.dreamteam.lonja.data.source.contract.AccountDataSourceContract.AccountDataSource;

public class AccountRepository extends Repository implements AccountDataSource {

    private AccountDataSource mAccountLocalDataSource;

    protected AccountRepository(@NonNull Realm realm) {
        super();
    }

    @Override
    public Observable<Account> getAccount() {
        return mAccountLocalDataSource.getAccount();
    }

    @Override
    public Observable saveAccount(Account account) {
        return mAccountLocalDataSource.saveAccount(account);
    }

    @Override
    public Observable deleteAccount() {
        return mAccountLocalDataSource.deleteAccount();
    }

    @Override
    void closeDatabaseConnection() {
//        ((AccountRealmLocalDataSource)mAccountLocalDataSource).closeConnection();
    }
}