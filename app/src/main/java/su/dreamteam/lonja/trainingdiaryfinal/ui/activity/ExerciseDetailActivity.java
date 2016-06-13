package su.dreamteam.lonja.trainingdiaryfinal.ui.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import su.dreamteam.lonja.data.repository.ExercisesRepository;
import su.dreamteam.lonja.data.source.local.ExercisesLocalDataSource;
import su.dreamteam.lonja.trainingdiaryfinal.R;
import su.dreamteam.lonja.trainingdiaryfinal.adapter.ExercisePagerAdapter;
import su.dreamteam.lonja.trainingdiaryfinal.databinding.ActivityExerciseDetailBinding;
import su.dreamteam.lonja.trainingdiaryfinal.ui.fragment.ExerciseDescriptionFragment;
import su.dreamteam.lonja.trainingdiaryfinal.ui.fragment.ExerciseStatisticsFragment;
import su.dreamteam.lonja.trainingdiaryfinal.ui.fragment.ExerciseTechniqueFragment;
import su.dreamteam.lonja.trainingdiaryfinal.viewmodel.ExerciseDetailViewModel;

public class ExerciseDetailActivity extends AppCompatActivity {

    public static final String ARG_EXERCISE_EXTRA = "ARG_EXERCISE_EXTRA";

    private ActivityExerciseDetailBinding mBinding;

    private ExerciseDetailViewModel mViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_exercise_detail);
        setSupportActionBar(mBinding.toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        String exerciseId = null;
        if (getIntent().hasExtra(ARG_EXERCISE_EXTRA)) {
            exerciseId = getIntent().getStringExtra(ARG_EXERCISE_EXTRA);
        }

        mViewModel = new ExerciseDetailViewModel(
                ExercisesRepository.getInstance(ExercisesLocalDataSource.getInstance()),
                this,
                exerciseId
        );
        TabLayout tabLayout = mBinding.tabs;
        ViewPager viewPager = mBinding.viewpager;
        ExercisePagerAdapter adapter = new ExercisePagerAdapter(getSupportFragmentManager());
        adapter.addFragment(ExerciseDescriptionFragment.newInstance(mViewModel),
                getString(ExerciseDescriptionFragment.TITLE));
        adapter.addFragment(ExerciseTechniqueFragment.newInstance(mViewModel),
                getString(ExerciseTechniqueFragment.TITLE));
        adapter.addFragment(ExerciseStatisticsFragment.newInstance(mViewModel),
                getString(ExerciseStatisticsFragment.TITLE));
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        mBinding.setViewModel(mViewModel);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mViewModel.subscribe();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mViewModel.unsubscribe();
    }
}
