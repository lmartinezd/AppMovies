package com.lmf.appmovies.ui.main;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;

import com.lmf.appmovies.AppMovies;
import com.lmf.appmovies.data.local.entity.Movie;
import com.lmf.appmovies.data.remote.ResponseMovie;
import com.lmf.appmovies.data.repository.AppRepository;
import com.lmf.appmovies.utils.rx.RxAPICallback;

import java.util.List;

public class MainViewModel extends ViewModel {

    private final AppRepository mRepository;
    private LiveData<List<Movie>> mMovies;
    private LiveData<List<Movie>> mFilterMovies;
    private MutableLiveData<String> mTextChange = new MutableLiveData();

    public MainViewModel() {
        mRepository = new AppRepository();

        mMovies = mRepository.getAllMovies();
        mFilterMovies = Transformations.switchMap(mTextChange, it ->
                mRepository.getMoviesLike(it));
    }

    void getListMovies(String category) {
        if (AppMovies.hasNetwork()) {
            mRepository.getListMovies(category, new RxAPICallback<ResponseMovie>() {

                @Override
                public void onSuccess(ResponseMovie data) {
                    mRepository.deleteAllMovies();
                    mRepository.insertMovie(data);
                }

                @Override
                public void onFailed(Throwable t) {

                    return;
                }
            });
        }
    }

    void getMoviesOrderBy(String condition) {
        this.mTextChange.setValue(condition);
    }

    LiveData<List<Movie>> listMovies() {
        return mMovies;
    }

    LiveData<List<Movie>> filterMovies() {
        return mFilterMovies;
    }
}
