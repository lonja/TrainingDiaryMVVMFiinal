package su.dreamteam.lonja.trainingdiaryfinal.ui.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import su.dreamteam.lonja.trainingdiaryfinal.R;
import su.dreamteam.lonja.trainingdiaryfinal.databinding.FragmentExerciseDescriptionBinding;
import su.dreamteam.lonja.trainingdiaryfinal.ui.View;
import su.dreamteam.lonja.trainingdiaryfinal.viewmodel.ExerciseDetailViewModel;

public class ExerciseDescriptionFragment extends Fragment implements View<ExerciseDetailViewModel> {

    private ExerciseDetailViewModel mViewModel;

    private FragmentExerciseDescriptionBinding mBinding;

    @StringRes
    public static final int TITLE = R.string.description;

    @Nullable
    @Override
    public android.view.View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_exercise_description,
                container, false);
        mBinding.setViewModel(mViewModel);
        return mBinding.getRoot();
    }

    public static ExerciseDescriptionFragment newInstance(ExerciseDetailViewModel viewModel) {
        ExerciseDescriptionFragment fragment = new ExerciseDescriptionFragment();
        fragment.setViewModel(viewModel);
        return fragment;
    }

    @Override
    public void setViewModel(ExerciseDetailViewModel viewModel) {
        mViewModel = viewModel;
    }
}
