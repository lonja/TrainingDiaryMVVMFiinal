package su.dreamteam.lonja.trainingdiaryfinal.ui.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import su.dreamteam.lonja.data.RealmHelper;
import su.dreamteam.lonja.data.repository.MeasurementsRepository;
import su.dreamteam.lonja.data.source.local.MeasurementsRealmLocalDataSource;
import su.dreamteam.lonja.trainingdiaryfinal.R;
import su.dreamteam.lonja.trainingdiaryfinal.databinding.ActivityAddEditMeasurementBinding;
import su.dreamteam.lonja.trainingdiaryfinal.viewmodel.MeasurementDetailViewModel;

public class AddEditMeasurementActivity extends AppCompatActivity {

    public static final String ARG_MEASUREMENT_EXTRA = "ARG_MEASUREMENT_EXTRA";

    private ActivityAddEditMeasurementBinding mBinding;

    private MeasurementDetailViewModel mViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_add_edit_measurement);
        setSupportActionBar(mBinding.toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        String measurementId = null;
        if (getIntent().hasExtra(ARG_MEASUREMENT_EXTRA)) {
            measurementId = getIntent().getStringExtra(ARG_MEASUREMENT_EXTRA);
        }

        RealmHelper helper = RealmHelper.getInstance(this);
        mViewModel = new MeasurementDetailViewModel(
                MeasurementsRepository.getInstance(
                        MeasurementsRealmLocalDataSource.getInstance()
                ),
                helper,
                measurementId,
                this,
                getLayoutInflater()
        );

        mBinding.setViewModel(mViewModel);

        mBinding.fabEditMeasurementDone.setOnClickListener(view -> {
            mViewModel.doneEditing();
            finish();
        });

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
        mViewModel.cancelEditing();
        this.finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mViewModel.unsubscribe();
    }
}