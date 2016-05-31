package su.dreamteam.lonja.data;

import android.support.annotation.NonNull;

import com.fernandocejas.frodo.annotation.RxLogObservable;

import io.realm.RealmResults;
import rx.Observable;
import su.dreamteam.lonja.data.model.Account;
import su.dreamteam.lonja.data.model.Measurement;
import su.dreamteam.lonja.data.source.AccountDataSource;
import su.dreamteam.lonja.data.source.MeasurementsDataSource;

import static com.google.common.base.Preconditions.checkNotNull;

public final class DataManager implements MeasurementsDataSource, AccountDataSource {

    private MeasurementsDataSource mMeasurementsLocalDataSource;

    private AccountDataSource mAccountLocalDataSource;

    private static DataManager INSTANCE;

    private DataManager(MeasurementsDataSource measurementsLocalDataSource,
                        AccountDataSource accountLocalDataSource) {
        mMeasurementsLocalDataSource = measurementsLocalDataSource;
        mAccountLocalDataSource = accountLocalDataSource;
    }

    public static DataManager getInstance(MeasurementsDataSource measurementsLocalDataSource,
                                          AccountDataSource accountLocalDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new DataManager(measurementsLocalDataSource, accountLocalDataSource);
        }
        return INSTANCE;
    }

    @RxLogObservable
    @Override
    public Observable<Account> getAccount() {
        return mAccountLocalDataSource.getAccount();
    }

    @Override
    public void saveAccount(Account account) {
        mAccountLocalDataSource.saveAccount(account);
    }

    @Override
    public void deleteAccount() {
        mAccountLocalDataSource.deleteAccount();
    }

    @RxLogObservable
    @Override
    public Observable<RealmResults<Measurement>> getMeasurements() {
        return mMeasurementsLocalDataSource.getMeasurements();
    }

    @RxLogObservable
    @Override
    public Observable<Measurement> getMeasurement(@NonNull String measurementId) {
        return mMeasurementsLocalDataSource.getMeasurement(measurementId);
    }

    @Override
    public void saveMeasurement(@NonNull Measurement measurement) {
        checkNotNull(measurement);
        mMeasurementsLocalDataSource.saveMeasurement(measurement);
    }

    @Override
    public void deleteMeasurement(@NonNull String measurementId) {
        checkNotNull(measurementId);
        mMeasurementsLocalDataSource.deleteMeasurement(measurementId);
    }
}
