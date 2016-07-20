package su.dreamteam.lonja.data.source;

import io.realm.Realm;
import io.realm.RealmModel;
import io.realm.RealmResults;
import rx.Observable;

public abstract class DataSource {

    Realm mRealm;

    public DataSource() {
        mRealm = Realm.getDefaultInstance();
    }

}
