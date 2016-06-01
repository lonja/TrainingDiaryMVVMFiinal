package su.dreamteam.lonja.trainingdiaryfinal.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.github.paolorotolo.appintro.AppIntro2;

import su.dreamteam.lonja.data.DataManager;
import su.dreamteam.lonja.data.RealmHelper;
import su.dreamteam.lonja.data.source.local.AccountLocalDataSource;
import su.dreamteam.lonja.data.source.local.MeasurementsLocalDataSource;
import su.dreamteam.lonja.trainingdiaryfinal.ui.fragment.BirthDateFragment;
import su.dreamteam.lonja.trainingdiaryfinal.ui.fragment.GenderFragment;
import su.dreamteam.lonja.trainingdiaryfinal.ui.fragment.HeightFragment;
import su.dreamteam.lonja.trainingdiaryfinal.ui.fragment.NameFragment;
import su.dreamteam.lonja.trainingdiaryfinal.ui.fragment.WeightFragment;
import su.dreamteam.lonja.trainingdiaryfinal.viewmodel.AccountWizardViewModel;

public class NewAccountActivity extends AppIntro2 {

    private AccountWizardViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new AccountWizardViewModel(DataManager.getInstance(
                MeasurementsLocalDataSource.getInstance(),
                AccountLocalDataSource.getInstance()
        ), RealmHelper.getInstance(this), this);
        addSlide(NameFragment.newInstance(mViewModel));
        addSlide(GenderFragment.newInstance(mViewModel));
        addSlide(BirthDateFragment.newInstance(mViewModel));
        addSlide(HeightFragment.newInstance(mViewModel));
        addSlide(WeightFragment.newInstance(mViewModel));
    }

    @Override
    public void onSlideChanged(@Nullable Fragment oldFragment, @Nullable Fragment newFragment) {
        super.onSlideChanged(oldFragment, newFragment);
        mViewModel.doneEditing();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mViewModel.cancelEditing();
    }

    @Override
    public boolean isSkipButtonEnabled() {
        return false;
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        mViewModel.doneEditing();
        mViewModel.showProfile();
    }
}