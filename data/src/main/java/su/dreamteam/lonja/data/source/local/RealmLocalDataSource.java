package su.dreamteam.lonja.data.source.local;

import io.realm.Realm;
import rx.Observable;
import rx.subjects.BehaviorSubject;

public interface RealmLocalDataSource {

    void openConnection();

    void closeConnection();

    void beginTransaction();

    void commitTransaction();

    void cancelTransaction();

    default Observable executeTransactionAsync(Realm realm, Realm.Transaction transaction) {
        BehaviorSubject subject = BehaviorSubject.create();
        realm.executeTransactionAsync(transaction, subject::onCompleted, subject::onError);
        return subject.asObservable();
    }

    default Observable executeTransaction(Realm realm, Realm.Transaction transaction) {
        BehaviorSubject subject = BehaviorSubject.create();
        try {
            realm.executeTransaction(transaction);
            subject.onCompleted();
        } catch (Exception e) {
            subject.onError(e);
        }
        return subject;

    }
}