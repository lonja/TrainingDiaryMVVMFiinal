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
import su.dreamteam.lonja.trainingdiaryfinal.databinding.FragmentAccountHeightBinding;
import su.dreamteam.lonja.trainingdiaryfinal.event.HeightValidationEvent;
import su.dreamteam.lonja.trainingdiaryfinal.viewmodel.AccountWizardViewModel;

public class HeightFragment extends SlideFragment
        implements su.dreamteam.lonja.trainingdiaryfinal.ui.View<AccountWizardViewModel> {

    private FragmentAccountHeightBinding mBinding;

    private AccountWizardViewModel mViewModel;

    private boolean isValidationPassed = false;

    @DebugLog
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_account_height,
                container, false);
        mBinding.setViewModel(mViewModel);
        return mBinding.getRoot();
    }

    public static HeightFragment newInstance(AccountWizardViewModel viewModel) {
        HeightFragment fragment = new HeightFragment();
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
        mBinding.heightEditWrapper.setErrorEnabled(true);
        mBinding.heightEditWrapper.setError(errorMessage);
    }

    public void hideValidationError() {
        mBinding.heightEditWrapper.setErrorEnabled(false);
    }

    @DebugLog
    @Override
    public void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
        mViewModel.subscribe();
    }

    @DebugLog
    @Override
    public void onPause() {
        super.onPause();
        mViewModel.unsubscribe();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onHeightValidation(HeightValidationEvent event) {
        isValidationPassed = event.isValidationPassed;
        if (isValidationPassed) {
            hideValidationError();
            return;
        }
        showValidationError("Height must be in 70-250 range");
    }

    @Override
    public void setViewModel(AccountWizardViewModel viewModel) {
        mViewModel = viewModel;
    }
}
