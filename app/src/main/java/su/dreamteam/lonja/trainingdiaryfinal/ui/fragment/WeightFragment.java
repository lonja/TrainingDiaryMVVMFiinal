package su.dreamteam.lonja.trainingdiaryfinal.ui.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.heinrichreimersoftware.materialintro.app.SlideFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import hugo.weaving.DebugLog;
import su.dreamteam.lonja.trainingdiaryfinal.R;
import su.dreamteam.lonja.trainingdiaryfinal.databinding.FragmentAccountWeightBinding;
import su.dreamteam.lonja.trainingdiaryfinal.event.WeightValidationEvent;
import su.dreamteam.lonja.trainingdiaryfinal.viewmodel.AccountWizardViewModel;

public class WeightFragment extends SlideFragment
        implements su.dreamteam.lonja.trainingdiaryfinal.ui.View<AccountWizardViewModel> {

    private FragmentAccountWeightBinding mBinding;

    private AccountWizardViewModel mViewModel;

    private boolean isValidationPassed = false;

    @DebugLog
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_account_weight,
                container, false);
        mBinding.setViewModel(mViewModel);
        return mBinding.getRoot();
    }

    public static WeightFragment newInstance(AccountWizardViewModel viewModel) {
        WeightFragment fragment = new WeightFragment();
        fragment.setViewModel(viewModel);
        return fragment;
    }

    @Override
    public boolean canGoForward() {
        return isValidationPassed;
    }

    @Override
    public boolean canGoBackward() {
        return super.canGoBackward();
    }

    public void showValidationError(CharSequence errorMessage) {
        mBinding.weightEditWrapper.setErrorEnabled(true);
        mBinding.weightEditWrapper.setError(errorMessage);
    }

    public void hideValidationError() {
        mBinding.weightEditWrapper.setErrorEnabled(false);
    }

    @DebugLog
    @Override
    public void onResume() {
        super.onResume();
        mViewModel.subscribe();
        EventBus.getDefault().register(this);
    }

    @DebugLog
    @Override
    public void onPause() {
        super.onPause();
        mViewModel.unsubscribe();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onWeightValidation(WeightValidationEvent event) {
        isValidationPassed = event.isValidationPassed;
        if (isValidationPassed) {
            hideValidationError();
            return;
        }
        showValidationError("Weight must be in 30-250 range");
    }

    @Override
    public void setViewModel(AccountWizardViewModel viewModel) {
        mViewModel = viewModel;
    }
}
