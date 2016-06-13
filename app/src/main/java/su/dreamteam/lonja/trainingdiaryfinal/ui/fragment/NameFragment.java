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
import su.dreamteam.lonja.trainingdiaryfinal.databinding.FragmentAccountNameBinding;
import su.dreamteam.lonja.trainingdiaryfinal.event.NameValidationEvent;
import su.dreamteam.lonja.trainingdiaryfinal.viewmodel.AccountWizardViewModel;

public class NameFragment extends SlideFragment
        implements su.dreamteam.lonja.trainingdiaryfinal.ui.View<AccountWizardViewModel> {

    private FragmentAccountNameBinding mBinding;

    private AccountWizardViewModel mViewModel;

    private boolean isValidationPassed = false;

    @DebugLog
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_account_name,
                container, false);
        mBinding.setViewModel(mViewModel);
        return mBinding.getRoot();
    }

    public static NameFragment newInstance(AccountWizardViewModel viewModel) {
        NameFragment fragment = new NameFragment();
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

    private void showValidationError(CharSequence errorMessage) {
        mBinding.nameEditWrapper.setErrorEnabled(true);
        mBinding.nameEditWrapper.setError(errorMessage);
    }

    private void hideValidationError() {
        mBinding.nameEditWrapper.setErrorEnabled(false);
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
    public void onNameValidation(NameValidationEvent event) {
        isValidationPassed = event.isValidationPassed;
        if (isValidationPassed) {
            hideValidationError();
            return;
        }
        showValidationError("You're using not valid symbols");
    }

    @Override
    public void setViewModel(AccountWizardViewModel viewModel) {
        mViewModel = viewModel;
    }
}
