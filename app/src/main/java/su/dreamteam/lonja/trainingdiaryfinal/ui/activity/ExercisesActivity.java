package su.dreamteam.lonja.trainingdiaryfinal.ui.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;

import java.util.Collections;

import su.dreamteam.lonja.data.repository.ExercisesRepository;
import su.dreamteam.lonja.data.source.local.ExercisesLocalDataSource;
import su.dreamteam.lonja.trainingdiaryfinal.R;
import su.dreamteam.lonja.trainingdiaryfinal.adapter.ExercisesAdapter;
import su.dreamteam.lonja.trainingdiaryfinal.databinding.ActivityExercisesBinding;
import su.dreamteam.lonja.trainingdiaryfinal.databinding.ContentExercisesBinding;
import su.dreamteam.lonja.trainingdiaryfinal.ui.decorator.ListItemDecorator;
import su.dreamteam.lonja.trainingdiaryfinal.viewmodel.ExercisesViewModel;

public class ExercisesActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ActivityExercisesBinding mBinding;

    private ExercisesViewModel mViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_exercises);

        ExercisesAdapter adapter = new ExercisesAdapter(Collections.emptyList());

        LayoutInflater inflater = getLayoutInflater();

        ContentExercisesBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.content_exercises,
                mBinding.frameContent,
                true);

        mViewModel = new ExercisesViewModel(
                ExercisesRepository.getInstance(ExercisesLocalDataSource.getInstance()),
                adapter,
                this
        );

        Toolbar toolbar = mBinding.toolbar;
        setSupportActionBar(toolbar);

        DrawerLayout drawer = mBinding.drawerLayout;
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = mBinding.navView;
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_exercises);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        binding.listExercises.setLayoutManager(layoutManager);

        binding.listExercises.setAdapter(adapter);

        binding.listExercises.addItemDecoration(new ListItemDecorator(getDrawable(R.drawable.divider)));

        mBinding.setViewModel(mViewModel);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        Intent intent;
        if (id == R.id.nav_profile) {
            intent = new Intent(this, AccountInfoActivity.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_exercises) {

        } else if (id == R.id.nav_trainings) {
            intent = new Intent(this, WorkoutsActivity.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_measurements) {
            intent = new Intent(this, MeasurementsActivity.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_statistics) {
            intent = new Intent(this, StatisticsActivity.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_settings) {

        }

        DrawerLayout drawer = mBinding.drawerLayout;
        drawer.closeDrawer(GravityCompat.START);
        return true;
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
}