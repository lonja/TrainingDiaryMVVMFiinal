package su.dreamteam.lonja.trainingdiaryfinal.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import su.dreamteam.lonja.data.DataManager;
import su.dreamteam.lonja.data.source.local.AccountLocalDataSource;
import su.dreamteam.lonja.data.source.local.MeasurementsLocalDataSource;

public class AccountCheckActivity extends AppCompatActivity {

    DataManager mDataManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDataManager = DataManager.getInstance(MeasurementsLocalDataSource.getInstance(),
                AccountLocalDataSource.getInstance());
        mDataManager.getAccount()
                .doOnNext(account -> {
                    Intent intent;
                    if (account == null) {
                        intent = new Intent(this, NewAccountActivity.class);
                        startActivity(intent);
                        return;
                    }
                    intent = new Intent(this, AccountInfoActivity.class);
                    startActivity(intent);
                })
                .doOnError(throwable -> Toast.makeText(this, throwable.getMessage(),
                        Toast.LENGTH_LONG).show())
                .doOnCompleted(this::finish)
                .subscribe();
    }
}
