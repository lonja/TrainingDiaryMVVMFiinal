package su.dreamteam.lonja.trainingdiaryfinal.ui.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import su.dreamteam.lonja.trainingdiaryfinal.R;
import su.dreamteam.lonja.trainingdiaryfinal.databinding.FragmentAccountGenderBinding;
import su.dreamteam.lonja.trainingdiaryfinal.viewmodel.AccountWizardViewModel;

public class GenderFragment extends Fragment
        implements su.dreamteam.lonja.trainingdiaryfinal.ui.View<AccountWizardViewModel> {

    private FragmentAccountGenderBinding mBinding;

    private AccountWizardViewModel mViewModel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_account_gender,
                container, false);
        mBinding.setViewModel(mViewModel);
        return mBinding.getRoot();
    }

    public static GenderFragment newInstance(AccountWizardViewModel viewModel) {
        GenderFragment fragment = new GenderFragment();
        fragment.setViewModel(viewModel);
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        mViewModel.subscribe();
    }

    @Override
    public void onPause() {
        super.onPause();
        mViewModel.unsubscribe();
    }

    @Override
    public void setViewModel(AccountWizardViewModel viewModel) {
        mViewModel = viewModel;
    }
}
