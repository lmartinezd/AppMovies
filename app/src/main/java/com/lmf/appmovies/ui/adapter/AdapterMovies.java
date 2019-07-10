package com.lmf.appmovies.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.lmf.appmovies.R;
import com.lmf.appmovies.data.local.entity.Movie;
import com.lmf.appmovies.ui.details.DetailsActivity;
import com.lmf.appmovies.utils.Constans;

import java.util.ArrayList;
import java.util.List;

public class AdapterMovies extends RecyclerView.Adapter<AdapterMovies.MoviesViewHolder> {

    private List<Movie> lMovies = new ArrayList<>();
    private Context context;

    public class MoviesViewHolder extends RecyclerView.ViewHolder {
        ImageView img_mini;
        TextView tv_title_movie, tv_release_date, tv_vote_average;
        CardView cardview_movie;

        public MoviesViewHolder(View itemView) {
            super(itemView);
            img_mini = itemView.findViewById(R.id.img_mini);
            tv_title_movie = itemView.findViewById(R.id.tv_title_movie);
            tv_release_date = itemView.findViewById(R.id.tv_release_date);
            tv_vote_average = itemView.findViewById(R.id.tv_vote_average);
            cardview_movie = itemView.findViewById(R.id.cardview_movie);
        }
    }

    public AdapterMovies(Context context) {
        this.context = context;
    }

    @Override
    public void onBindViewHolder(final MoviesViewHolder holder, final int position) {
        Movie movie = lMovies.get(position);

        final int idMovie = movie.getId();
        String url = Constans.IMAGE_PATH + movie.getBackdropPath();
        String releaseDte = context.getString(R.string.string_release_date) +
                movie.getReleaseDate() == null ? "-" : movie.getReleaseDate();

        Glide.with(context)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.circle_progress)
                .into(holder.img_mini);

        holder.tv_title_movie.setText(movie.getTitle() == null ? "-" : movie.getTitle());
        holder.tv_release_date.setText(releaseDte);
        holder.tv_vote_average.setText(movie.getVoteAverage() == null ? "0.0" : movie.getVoteAverage().toString());

        holder.cardview_movie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailsActivity.class);
                intent.putExtra(Constans.PARAM_IDMOVIE, idMovie);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (null != lMovies ? lMovies.size() : 0);
    }

    @Override
    public AdapterMovies.MoviesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_movie, parent, false);

        return new AdapterMovies.MoviesViewHolder(itemView);
    }

    public void refreshAdapter(List<Movie> Movies) {
        if (Movies.size() > 0) {
            lMovies = Movies;
            notifyDataSetChanged();
        }
    }
}