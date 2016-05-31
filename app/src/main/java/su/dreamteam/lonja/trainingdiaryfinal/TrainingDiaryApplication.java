package su.dreamteam.lonja.trainingdiaryfinal;

import android.app.Application;

import su.dreamteam.lonja.data.RealmHelper;

public class TrainingDiaryApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        RealmHelper.getInstance(this);
    }

}