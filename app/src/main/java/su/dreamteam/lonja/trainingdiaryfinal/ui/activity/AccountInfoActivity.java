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
import android.view.MenuItem;

import su.dreamteam.lonja.data.DataManager;
import su.dreamteam.lonja.data.source.local.AccountLocalDataSource;
import su.dreamteam.lonja.data.source.local.MeasurementsLocalDataSource;
import su.dreamteam.lonja.trainingdiaryfinal.R;
import su.dreamteam.lonja.trainingdiaryfinal.databinding.ActivityAccountInfoBinding;
import su.dreamteam.lonja.trainingdiaryfinal.viewmodel.AccountInfoViewModel;

public class AccountInfoActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    ActivityAccountInfoBinding mBinding;

    private AccountInfoViewModel mViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_account_info);

        Toolbar toolbar = mBinding.toolbar;
        setSupportActionBar(toolbar);

        DrawerLayout drawer = mBinding.drawerLayout;
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = mBinding.navView;
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_profile);

        mViewModel = new AccountInfoViewModel(DataManager.getInstance(
                MeasurementsLocalDataSource.getInstance(),
                AccountLocalDataSource.getInstance()
        ), this);
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
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        Intent intent;
        if (id == R.id.nav_profile) {

        } else if (id == R.id.nav_exercises) {

        } else if (id == R.id.nav_trainings) {

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
