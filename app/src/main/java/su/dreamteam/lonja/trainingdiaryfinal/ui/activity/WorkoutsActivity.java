package su.dreamteam.lonja.trainingdiaryfinal.ui.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;

import java.util.Collections;

import su.dreamteam.lonja.data.repository.TrainingsRepository;
import su.dreamteam.lonja.data.source.local.TrainingsLocalDataSource;
import su.dreamteam.lonja.trainingdiaryfinal.R;
import su.dreamteam.lonja.trainingdiaryfinal.adapter.WorkoutsAdapter;
import su.dreamteam.lonja.trainingdiaryfinal.databinding.ActivityWorkoutsBinding;
import su.dreamteam.lonja.trainingdiaryfinal.databinding.ContentTrainingsBinding;
import su.dreamteam.lonja.trainingdiaryfinal.ui.decoration.ListItemDecorator;
import su.dreamteam.lonja.trainingdiaryfinal.viewmodel.WorkoutsViewModel;

public class WorkoutsActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ActivityWorkoutsBinding mBinding;

    private WorkoutsViewModel mViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_workouts);

        WorkoutsAdapter adapter = new WorkoutsAdapter(Collections.emptyList());

        LayoutInflater inflater = getLayoutInflater();

        ContentTrainingsBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.content_trainings,
                mBinding.frameContent,
                true);

        mViewModel = new WorkoutsViewModel(
                TrainingsRepository.getInstance(TrainingsLocalDataSource.getInstance()),
                adapter,
                this
        );

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        configureToolbar();

        configureNavigationView();

        configureList(binding.listWorkouts,
                layoutManager,
                adapter,
                new ListItemDecorator(getDrawable(R.drawable.divider)));

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
            intent = new Intent(this, ExercisesActivity.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_trainings) {

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

    @Override
    protected void configureToolbar() {
        Toolbar toolbar = mBinding.toolbar;
        setSupportActionBar(toolbar);

        DrawerLayout drawer = mBinding.drawerLayout;
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    protected void configureList(RecyclerView recyclerView,
                                 RecyclerView.LayoutManager layoutManager,
                                 RecyclerView.Adapter adapter,
                                 @Nullable RecyclerView.ItemDecoration decoration) {
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(decoration);
    }

    @Override
    protected void configureNavigationView() {
        NavigationView navigationView = mBinding.navView;
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_trainings);
    }
}
