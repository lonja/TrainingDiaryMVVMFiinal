package su.dreamteam.lonja.data.source;

import rx.Observable;
import su.dreamteam.lonja.data.model.Account;

public interface AccountDataSource {

    Observable<Account> getAccount();

    Observable saveAccount(Account account);

    Observable deleteAccount();
}
