package su.dreamteam.lonja.data.source.local;

import io.realm.Realm;
import io.realm.exceptions.RealmException;
import rx.Observable;
import su.dreamteam.lonja.data.model.Account;
import su.dreamteam.lonja.data.source.AccountDataSource;

public final class AccountLocalDataSource implements AccountDataSource {

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
    public void saveAccount(Account account) {
        try {
            mRealm.beginTransaction();
            Account realmAccount = mRealm.createObject(Account.class);
            realmAccount.setId(account.getId());
            realmAccount.setName(account.getName());
            realmAccount.setGender(account.getGender());
            realmAccount.setBirthDate(account.getBirthDate());
            realmAccount.setHeight(account.getHeight());
            realmAccount.setWeight(account.getWeight());
            mRealm.commitTransaction();
        } catch (RealmException e) {
            mRealm.cancelTransaction();
        }
    }

    @Override
    public void deleteAccount() {
        try {
            mRealm.beginTransaction();
            mRealm.delete(Account.class);
            mRealm.commitTransaction();
        } catch (RealmException e) {
            mRealm.cancelTransaction();
        }
    }
}