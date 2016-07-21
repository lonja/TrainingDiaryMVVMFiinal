package su.dreamteam.lonja.data;

import android.content.Context;

import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmModel;

public final class RealmHelper {

    private static Realm mRealm;

    private static RealmHelper INSTANCE;

    private RealmHelper(Context context) {
        try {
            mRealm = Realm.getDefaultInstance();
        } catch (NullPointerException e) {
            configure(context, null);
            mRealm = Realm.getDefaultInstance();
        }
    }

    private RealmHelper(Context context, Realm.Transaction initialData) {
        try {
            mRealm = Realm.getDefaultInstance();
        } catch (NullPointerException e) {
            configure(context, initialData);
            mRealm = Realm.getDefaultInstance();
        }
    }

    public static RealmHelper getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new RealmHelper(context);
        }
        return INSTANCE;
    }

    public static RealmHelper getInstance(Context context, Realm.Transaction initialData) {
        if (INSTANCE == null) {
            INSTANCE = new RealmHelper(context, initialData);
        }
        return INSTANCE;
    }

    public void beginTransaction() {
        if (mRealm.isInTransaction()) {
            return;
        }
        mRealm.beginTransaction();
    }

    public void commitTransaction() {
        if (!mRealm.isInTransaction()) {
            return;
        }
        mRealm.commitTransaction();
    }

    public void cancelTransaction() {
        if (!mRealm.isInTransaction()) {
            return;
        }
        mRealm.cancelTransaction();
    }

    public <E extends RealmModel> E createRealmObject(Class<E> clazz) {
        return mRealm.createObject(clazz, UUID.randomUUID().toString());
    }

    private void configure(Context context, Realm.Transaction initialData) {
        RealmConfiguration configuration = new RealmConfiguration.Builder(context)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(configuration);
        final Realm realm = Realm.getDefaultInstance();
        if (realm.isEmpty()) {
            realm.executeTransactionAsync(initialData, realm::close);
        }
    }
}