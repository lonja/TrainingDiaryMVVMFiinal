package su.dreamteam.lonja.trainingdiaryfinal.ui.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;

import java.util.Collections;

import su.dreamteam.lonja.data.repository.ExercisesRepository;
import su.dreamteam.lonja.data.source.local.ExercisesLocalDataSource;
import su.dreamteam.lonja.trainingdiaryfinal.R;
import su.dreamteam.lonja.trainingdiaryfinal.adapter.ExercisesAdapter;
import su.dreamteam.lonja.trainingdiaryfinal.databinding.ActivityExercisesChoiceBinding;
import su.dreamteam.lonja.trainingdiaryfinal.databinding.ContentExercisesChoiceBinding;
import su.dreamteam.lonja.trainingdiaryfinal.ui.decoration.ListItemDecorator;
import su.dreamteam.lonja.trainingdiaryfinal.viewmodel.ExercisesChoiceViewModel;

public class ExercisesChoiceActivity extends AppCompatActivity {


    ActivityExercisesChoiceBinding mBinding;

    ExercisesChoiceViewModel mViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_exercises_choice);

        ExercisesAdapter adapter = new ExercisesAdapter(Collections.emptyList(),
                ExercisesAdapter.TYPE_ITEM_CHOICE);

        LayoutInflater inflater = getLayoutInflater();

        ContentExercisesChoiceBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.content_exercises_choice,
                mBinding.frameContent,
                true);

        mViewModel = new ExercisesChoiceViewModel(
                ExercisesRepository.getInstance(ExercisesLocalDataSource.getInstance()),
                adapter,
                this
        );

        Toolbar toolbar = mBinding.toolbar;
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        binding.listExercises.setLayoutManager(layoutManager);

        binding.listExercises.setAdapter(adapter);

        binding.listExercises.addItemDecoration(new ListItemDecorator(getDrawable(R.drawable.divider)));

        mBinding.setViewModel(mViewModel);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mViewModel.subscribe();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mViewModel.unsubscribe();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
