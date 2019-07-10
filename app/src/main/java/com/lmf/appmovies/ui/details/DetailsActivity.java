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
    ImageView iv_photo;
    TextView tv_title, tv_overview;

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
        iv_photo = findViewById(R.id.img_Photo);
        tv_title = findViewById(R.id.tv_title);
        tv_overview = findViewById(R.id.tv_overview);
    }

    private void configureToolbar() {
        Toolbar mToolbar = findViewById(R.id.toolbar_main);
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setElevation(4);
            getSupportActionBar().setDisplayShowTitleEnabled(true);
            getSupportActionBar().setTitle(R.string.title_overview);

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
                String url = Constans.IMAGE_PATH + movie.getPosterPath();

                Glide.with(this)
                        .load(url)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .placeholder(R.drawable.circle_progress)
                        .into(iv_photo);

                tv_title.setText(movie.getTitle());
                tv_overview.setText(movie.getOverview());
            }
        });
    }
}
