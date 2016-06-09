package su.dreamteam.lonja.trainingdiaryfinal.ui.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Collections;

import su.dreamteam.lonja.data.DataManager;
import su.dreamteam.lonja.data.source.local.AccountLocalDataSource;
import su.dreamteam.lonja.data.source.local.MeasurementsLocalDataSource;
import su.dreamteam.lonja.trainingdiaryfinal.R;
import su.dreamteam.lonja.trainingdiaryfinal.adapter.MeasurementsAdapter;
import su.dreamteam.lonja.trainingdiaryfinal.databinding.ActivityMeasurementsBinding;
import su.dreamteam.lonja.trainingdiaryfinal.databinding.ContentMeasurementsBinding;
import su.dreamteam.lonja.trainingdiaryfinal.viewmodel.MeasurementsViewModel;

public class MeasurementsActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ActivityMeasurementsBinding mBinding;

    private MeasurementsViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_measurements);

        MeasurementsAdapter adapter = new MeasurementsAdapter(Collections.emptyList());

        LayoutInflater inflater = getLayoutInflater();

        ContentMeasurementsBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.content_measurements,
                mBinding.frameContent,
                true);

        mViewModel = new MeasurementsViewModel(
                DataManager.getInstance(
                        MeasurementsLocalDataSource.getInstance(),
                        AccountLocalDataSource.getInstance()),
                this,
                adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        binding.listMeasurements.setLayoutManager(layoutManager);

        binding.listMeasurements.setAdapter(adapter);

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
        navigationView.setCheckedItem(R.id.nav_measurements);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = mBinding.drawerLayout;
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.measurements, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
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

        } else if (id == R.id.nav_measurements) {

        } else if (id == R.id.nav_statistics) {

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
