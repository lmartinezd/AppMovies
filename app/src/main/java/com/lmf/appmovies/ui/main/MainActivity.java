package com.lmf.appmovies.ui.main;

import android.arch.lifecycle.ViewModelProviders;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.lmf.appmovies.AppMovies;
import com.lmf.appmovies.R;
import com.lmf.appmovies.data.local.entity.Movie;
import com.lmf.appmovies.ui.adapter.AdapterMovies;
import com.lmf.appmovies.utils.AppUtils;
import com.lmf.appmovies.utils.Constans;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MainViewModel viewModel;
    private RecyclerView rvMovie;
    private AdapterMovies mAdapter;
    private ConstraintLayout layout_progressbar;
    private List<Movie> listMovies = new ArrayList<>();
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupView();
        setupToolbar();
        setProgressBarVisible();
        configureObservers();
        setupAdapter();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_category, menu);

        AppUtils.tintMenuIcon(this, menu.findItem(R.id.action_filter_toolbar), R.color.color_white);

        SearchView mSearchView = (SearchView) menu.findItem(R.id.action_search_toolbar).getActionView();
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                viewModel.getMoviesOrderBy(newText);
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_category_popular:
                setTitleToolbar(getString(R.string.string_category_popular));
                getMoviesByCategory(Constans.PATH_POPULAR);
                return true;
            case R.id.action_category_toprated:
                setTitleToolbar(getString(R.string.string_category_toprated));
                getMoviesByCategory(Constans.PATH_TOPRATED);
                return true;
            case R.id.action_category_upcoming:
                setTitleToolbar(getString(R.string.string_category_upcoming));
                getMoviesByCategory(Constans.PATH_UPCOMING);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void getMoviesByCategory(String category) {
        if (AppMovies.hasNetwork()) {
            setProgressBarVisible();
            viewModel.getListMovies(category);
        }
    }

    private void setupToolbar() {
        mToolbar = findViewById(R.id.toolbar_main);

        if (mToolbar != null)
            setSupportActionBar(mToolbar);
    }

    private void setTitleToolbar(String title) {
        if (mToolbar != null) {
            getSupportActionBar().setTitle(title);
        }
    }

    private void setupView() {
        layout_progressbar = findViewById(R.id.layout_progressbar);
        rvMovie = findViewById(R.id.rvMovies);
    }

    public void configureObservers() {
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        getMoviesByCategory(Constans.PATH_POPULAR);
        setTitleToolbar(getString(R.string.string_category_popular));

        viewModel.listMovies().observe(this, list -> {
            if (list != null || !list.isEmpty()) {
                fetListMovies(list);
            }
            setProgressBarGone();
        });

        viewModel.filterMovies().observe(this, list -> {
            if (list == null || list.isEmpty()) {
                list = new ArrayList<>();
            }
            fetListMovies(list);
        });
    }

    private void fetListMovies(List<Movie> list) {
        listMovies = list;
        mAdapter.refreshAdapter(listMovies);
    }

    private void setupAdapter() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        linearLayoutManager.scrollToPosition(0);

        rvMovie.setLayoutManager(linearLayoutManager);
        rvMovie.setHasFixedSize(true);
        rvMovie.setItemAnimator(new DefaultItemAnimator());

        rvMovie.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));
        mAdapter = new AdapterMovies(this);

        rvMovie.setAdapter(mAdapter);
    }

    private void setProgressBarGone() {
        layout_progressbar.setVisibility(View.GONE);
    }

    private void setProgressBarVisible() {
        layout_progressbar.setVisibility(View.VISIBLE);
    }
}
