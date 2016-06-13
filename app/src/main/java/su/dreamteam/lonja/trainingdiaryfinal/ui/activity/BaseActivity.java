package su.dreamteam.lonja.trainingdiaryfinal.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

public abstract class BaseActivity extends AppCompatActivity {

    protected abstract void configureToolbar();

    protected abstract void configureList(RecyclerView recyclerView,
                                          RecyclerView.LayoutManager layoutManager,
                                          RecyclerView.Adapter adapter,
                                          RecyclerView.ItemDecoration decoration);

    protected abstract void configureNavigationView();


}
