package su.dreamteam.lonja.data.source.contract;

import rx.Observable;
import su.dreamteam.lonja.data.model.Account;
import su.dreamteam.lonja.data.source.local.RealmLocalDataSource;

public interface AccountDataSourceContract {

    interface AccountRealmDataSource extends AccountDataSource, RealmLocalDataSource {

    }

    interface AccountDataSource {

        Observable<Account> getAccount();

        Observable saveAccount(Account account);

        Observable deleteAccount();
    }
}
