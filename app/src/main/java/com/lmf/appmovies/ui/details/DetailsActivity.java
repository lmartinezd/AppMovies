package com.lmf.appmovies.ui.details;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.lmf.appmovies.R;
import com.lmf.appmovies.utils.Constans;

public class DetailsActivity extends AppCompatActivity {

    private DetailsViewModel viewModel;
    ImageView iv_poster, iv_image_movie_bg;
    TextView tv_title, tv_overview, tv_language, tv_release_date, tv_count, tv_vote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        setupUI();
        configureToolbar();
        configureObservers();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            listDetailsMovie(extras.getInt(Constans.PARAM_IDMOVIE));
        }
    }

    private void setupUI() {
        iv_poster = findViewById(R.id.image_poster);
        iv_image_movie_bg = findViewById(R.id.image_movie_bg);
        tv_title = findViewById(R.id.tv_title);
        tv_overview = findViewById(R.id.tv_overview);
        tv_language = findViewById(R.id.tv_language);
        tv_release_date = findViewById(R.id.tv_release_date);
        tv_count = findViewById(R.id.tv_count);
        tv_vote = findViewById(R.id.tv_vote);
    }

    private void configureToolbar() {
        Toolbar mToolbar = findViewById(R.id.toolbar_details);
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setElevation(4);

            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
        }
    }

    public void configureObservers() {
        viewModel = ViewModelProviders.of(this).get(DetailsViewModel.class);
    }

    private void listDetailsMovie(int idmovie) {
        viewModel.listMovies(idmovie).observe(this, movie -> {
            if (movie != null) {
                String url_poster = Constans.IMAGE_PATH + movie.getPosterPath();
                String url_bg = Constans.IMAGE_PATH + movie.getBackdropPath();

                Glide.with(this)
                        .load(url_poster)
                        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                        .thumbnail(Glide.with(this).load(R.drawable.loading_spinner))
                        .fitCenter()
                        .into(iv_poster);

                Glide.with(this)
                        .load(url_bg)
                        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                        .fitCenter()
                        .into(iv_image_movie_bg);

                tv_title.setText(movie.getTitle());
                tv_overview.setText(movie.getOverview());
                tv_language.setText(movie.getOriginalLanguage().toUpperCase());
                tv_release_date.setText(movie.getReleaseDate());
                tv_count.setText(getString(R.string.string_count_votes, movie.getVoteCount().toString()));
                tv_vote.setText(movie.getVoteAverage().toString());
            }
        });
    }
}
