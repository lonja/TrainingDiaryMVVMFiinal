package su.dreamteam.lonja.trainingdiaryfinal.ui.activity;

import android.os.Bundle;

import com.heinrichreimersoftware.materialintro.app.IntroActivity;
import com.heinrichreimersoftware.materialintro.slide.FragmentSlide;
import com.heinrichreimersoftware.materialintro.slide.Slide;

import su.dreamteam.lonja.data.DataManager;
import su.dreamteam.lonja.data.source.local.AccountLocalDataSource;
import su.dreamteam.lonja.data.source.local.MeasurementsLocalDataSource;
import su.dreamteam.lonja.trainingdiaryfinal.R;
import su.dreamteam.lonja.trainingdiaryfinal.ui.fragment.BirthDateFragment;
import su.dreamteam.lonja.trainingdiaryfinal.ui.fragment.GenderFragment;
import su.dreamteam.lonja.trainingdiaryfinal.ui.fragment.HeightFragment;
import su.dreamteam.lonja.trainingdiaryfinal.ui.fragment.NameFragment;
import su.dreamteam.lonja.trainingdiaryfinal.ui.fragment.WeightFragment;
import su.dreamteam.lonja.trainingdiaryfinal.viewmodel.AccountWizardViewModel;

public class NewAccountActivity extends IntroActivity {

    private AccountWizardViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new AccountWizardViewModel(DataManager.getInstance(
                MeasurementsLocalDataSource.getInstance(),
                AccountLocalDataSource.getInstance()
        ),
                this);
        initSlides();
        setSkipEnabled(false);
    }

    private void initSlides() {
        final Slide birthDateSlide = new FragmentSlide.Builder()
                .background(R.color.colorPrimary)
                .backgroundDark(R.color.colorPrimaryDark)
                .fragment(BirthDateFragment.newInstance(mViewModel))
                .build();
        final Slide genderSlide = new FragmentSlide.Builder()
                .background(R.color.colorPrimary)
                .backgroundDark(R.color.colorPrimaryDark)
                .fragment(GenderFragment.newInstance(mViewModel))
                .build();
        final Slide heightSlide = new FragmentSlide.Builder()
                .background(R.color.colorPrimary)
                .backgroundDark(R.color.colorPrimaryDark)
                .fragment(HeightFragment.newInstance(mViewModel))
                .build();
        final Slide nameSlide = new FragmentSlide.Builder()
                .background(R.color.colorPrimary)
                .backgroundDark(R.color.colorPrimaryDark)
                .fragment(NameFragment.newInstance(mViewModel))
                .build();
        final Slide weightSlide = new FragmentSlide.Builder()
                .background(R.color.colorPrimary)
                .backgroundDark(R.color.colorPrimaryDark)
                .fragment(WeightFragment.newInstance(mViewModel))
                .build();
        addSlides(nameSlide, genderSlide, birthDateSlide, heightSlide, weightSlide);
    }

    private void addSlides(Slide... slides) {
        for (Slide slide : slides) {
            addSlide(slide);
        }
    }

    // FIXME: 13.06.2016 on back press on first side closing
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mViewModel.doneEditing();
    }
}