package com.lmf.appmovies.ui.main;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.lmf.appmovies.R;
import com.lmf.appmovies.data.local.entity.Movie;
import com.lmf.appmovies.ui.adapter.AdapterMovies;
import com.lmf.appmovies.utils.Constans;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MainViewModel viewModel;
    private RecyclerView rvMovie;
    private AdapterMovies mAdapter;
    private ProgressBar progressBar;
    private List<Movie> listMovies = new ArrayList<>();

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
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_category_popular:
                getMoviesByCategory(Constans.PATH_POPULAR);
                return true;
            case R.id.action_category_toprated:
                getMoviesByCategory(Constans.PATH_TOPRATED);
                return true;
            case R.id.action_category_upcoming:
                getMoviesByCategory(Constans.PATH_UPCOMING);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void getMoviesByCategory(String category) {
        setProgressBarVisible();
        viewModel.getListMovies(category);
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);
    }

    private void setupView() {
        progressBar = findViewById(R.id.progressbar);
        rvMovie = findViewById(R.id.rvMovies);
    }

    public void configureObservers() {
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        getMoviesByCategory(Constans.PATH_POPULAR);

        viewModel.listMovies().observe(this, list -> {
            if (list != null || !list.isEmpty()) {
                listMovies = list;
                mAdapter.refreshAdapter(listMovies);
            }
            setProgressBarGone();
        });
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
        progressBar.setVisibility(View.GONE);
    }

    private void setProgressBarVisible() {
        progressBar.setVisibility(View.VISIBLE);
    }
}
