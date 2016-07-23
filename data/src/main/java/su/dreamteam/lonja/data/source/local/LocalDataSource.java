package su.dreamteam.lonja.data.source.local;


import io.realm.Realm;
import rx.Observable;
import rx.subjects.BehaviorSubject;

public abstract class LocalDataSource {

    protected Realm mRealm;

    protected LocalDataSource() {
        mRealm = Realm.getDefaultInstance();
    }

    protected Observable executeTransactionAsync(Realm.Transaction transaction) {
        BehaviorSubject subject = BehaviorSubject.create();
        mRealm.executeTransactionAsync(transaction, subject::onCompleted, subject::onError);
        return subject.asObservable();
    }
}
