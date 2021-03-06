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
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;

import su.dreamteam.lonja.trainingdiaryfinal.R;
import su.dreamteam.lonja.trainingdiaryfinal.databinding.ActivityStatisticsBinding;
import su.dreamteam.lonja.trainingdiaryfinal.databinding.ContentStatisticsBinding;
import su.dreamteam.lonja.trainingdiaryfinal.viewmodel.StatisticsViewModel;

public class StatisticsActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ActivityStatisticsBinding mBinding;

    private StatisticsViewModel mViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_statistics);

        LayoutInflater inflater = getLayoutInflater();

        ContentStatisticsBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.content_statistics,
                mBinding.frameContent,
                true);

        mViewModel = new StatisticsViewModel();

        mBinding.setViewModel(mViewModel);

        Toolbar toolbar = mBinding.toolbar;
        setSupportActionBar(toolbar);

        DrawerLayout drawer = mBinding.drawerLayout;
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = mBinding.navView;
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_statistics);
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
            intent = new Intent(this, WorkoutsActivity.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_measurements) {
            intent = new Intent(this, MeasurementsActivity.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_statistics) {

        } else if (id == R.id.nav_settings) {

        }

        DrawerLayout drawer = mBinding.drawerLayout;
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
