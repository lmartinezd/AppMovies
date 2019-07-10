package com.lmf.appmovies.data.repository;

import android.arch.lifecycle.LiveData;

import com.lmf.appmovies.AppMovies;
import com.lmf.appmovies.data.MovieApi;
import com.lmf.appmovies.data.local.dao.MovieDao;
import com.lmf.appmovies.data.local.entity.Movie;
import com.lmf.appmovies.data.remote.ResponseMovie;
import com.lmf.appmovies.data.remote.ResultMovie;
import com.lmf.appmovies.utils.Constans;
import com.lmf.appmovies.utils.rx.RxAPICallback;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AppRepository {
    private MovieApi movieApi;
    private MovieDao movieDao;
    private Executor executor;

    public AppRepository() {
        movieDao = AppMovies.getMovieDao();
        movieApi = AppMovies.getMovieApi();
        executor = Executors.newSingleThreadExecutor();
    }

    public void getListMovies(String category, final RxAPICallback<ResponseMovie> data) {
        movieApi.getListMovies(Constans.API_TOKEN, category)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseMovie>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseMovie responseMovie) {
                        data.onSuccess(responseMovie);
                    }

                    @Override
                    public void onError(Throwable e) {
                        data.onFailed(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public LiveData<List<Movie>> getAllMovies() {
        return movieDao.getAllMovies();
    }

    public LiveData<Movie> getMoviebyId(int idmovie) {
        return movieDao.getMoviebyId(idmovie);
    }

    public LiveData<List<Movie>> getListMovieLike(String condition) {
        return movieDao.getListMovieLike(condition);
    }

    public void insertMovie(ResponseMovie responseMovie) {
        List<Movie> lMovie = new ArrayList<>();
        int idmov=1;
        for (ResultMovie mov : responseMovie.getResults()) {
            lMovie.add(new Movie(
                    idmov,
                    mov.getId(),
                    mov.getVoteAverage(),
                    mov.getVoteCount(),
                    mov.getVideo(),
                    mov.getMediaType(),
                    mov.getTitle(),
                    mov.getPopularity(),
                    mov.getPosterPath(),
                    mov.getOriginalTitle(),
                    mov.getBackdropPath(),
                    mov.getOverview(),
                    mov.getReleaseDate())
            );
            idmov++;
        }

        executor.execute(() -> {
            movieDao.insert(lMovie);
        });
    }
    public void deleteAllMovies() {
        executor.execute(() -> {
            movieDao.deleteAll();
        });
    }

}
