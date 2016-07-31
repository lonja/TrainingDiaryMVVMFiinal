package su.dreamteam.lonja.trainingdiaryfinal.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import su.dreamteam.lonja.data.DataManager;
import su.dreamteam.lonja.data.source.local.AccountRealmLocalDataSource;
import su.dreamteam.lonja.data.source.local.MeasurementsRealmLocalDataSource;

public class AccountCheckActivity extends Activity {

    DataManager mDataManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDataManager = DataManager.getInstance(MeasurementsRealmLocalDataSource.getInstance(),
                AccountRealmLocalDataSource.getInstance());
        mDataManager.getAccount()
                .doOnNext(account -> {
                    Intent intent = new Intent(this, AccountInfoActivity.class);
                    startActivity(intent);
                    finish();
                })
                .doOnError(throwable -> Toast.makeText(this, throwable.getMessage(),
                        Toast.LENGTH_LONG).show())
                .doOnCompleted(() -> {
                    Intent intent = new Intent(this, NewAccountActivity.class);
                    startActivity(intent);
                    finish();
                })
                .subscribe();
    }
}