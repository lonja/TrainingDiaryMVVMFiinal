package su.dreamteam.lonja.trainingdiaryfinal.ui.fragment;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.heinrichreimersoftware.materialintro.app.SlideFragment;

import hugo.weaving.DebugLog;
import su.dreamteam.lonja.trainingdiaryfinal.R;
import su.dreamteam.lonja.trainingdiaryfinal.databinding.FragmentAccountBirthDateBinding;
import su.dreamteam.lonja.trainingdiaryfinal.viewmodel.AccountWizardViewModel;

public class BirthDateFragment extends SlideFragment
        implements su.dreamteam.lonja.trainingdiaryfinal.ui.View<AccountWizardViewModel> {

    private FragmentAccountBirthDateBinding mBinding;

    private AccountWizardViewModel mViewModel;

    @DebugLog
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_account_birth_date,
                container, false);
        mBinding.setViewModel(mViewModel);
        return mBinding.getRoot();
    }

    public static BirthDateFragment newInstance(AccountWizardViewModel viewModel) {
        BirthDateFragment fragment = new BirthDateFragment();
        fragment.setViewModel(viewModel);
        return fragment;
    }

    @Override
    public boolean canGoForward() {
        return super.canGoForward();
    }

    @Override
    public boolean canGoBackward() {
        return super.canGoBackward();
    }

    @Override
    public void setViewModel(AccountWizardViewModel viewModel) {
        mViewModel = viewModel;
    }

    @DebugLog
    @Override
    public void onResume() {
        super.onResume();
        mViewModel.subscribe();
    }

    @DebugLog
    @Override
    public void onPause() {
        super.onPause();
        mViewModel.unsubscribe();
    }
}
