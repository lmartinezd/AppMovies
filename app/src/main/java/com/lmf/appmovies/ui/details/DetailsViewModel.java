package com.lmf.appmovies.ui.details;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.lmf.appmovies.data.local.entity.Movie;
import com.lmf.appmovies.data.repository.AppRepository;

public class DetailsViewModel extends ViewModel {

    private final AppRepository mRepository;

    public DetailsViewModel() {
        mRepository = new AppRepository();
    }

    LiveData<Movie> listMovies(int idmovie) {
        return mRepository.getMoviebyId(idmovie);
    }
}