package su.dreamteam.lonja.data.source;

import rx.Observable;
import su.dreamteam.lonja.data.model.Account;

public interface AccountDataSource {

    Observable<Account> getAccount();

    void saveAccount(Account account);

    void deleteAccount();
}
