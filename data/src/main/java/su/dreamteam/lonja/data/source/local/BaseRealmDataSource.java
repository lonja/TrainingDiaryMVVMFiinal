package su.dreamteam.lonja.data.source.local;

import io.realm.Realm;
import rx.Observable;
import rx.subjects.BehaviorSubject;

public abstract class BaseRealmDataSource {

    protected Observable executeTransactionAsync(Realm realm, Realm.Transaction transaction) {
        BehaviorSubject subject = BehaviorSubject.create();
        realm.executeTransactionAsync(transaction, subject::onCompleted, subject::onError);
        return subject.asObservable();
    }

    protected Observable executeTransaction(Realm realm, Realm.Transaction transaction) {
        BehaviorSubject subject = BehaviorSubject.create();
        try {
            realm.executeTransaction(transaction);
            subject.onCompleted();
        } catch (Exception e) {
            subject.onError(e);
        }
        return subject.asObservable();
    }

    protected void closeConnection(Realm realm) {
        if (realm.isClosed()) {
            return;
        }
        realm.close();
    }
}
