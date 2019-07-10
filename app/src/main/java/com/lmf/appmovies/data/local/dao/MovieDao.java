package com.lmf.appmovies.data.local.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.lmf.appmovies.data.local.entity.Movie;

import java.util.List;

@Dao
public interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Movie> movie);

    @Query("DELETE FROM tb_movie")
    void deleteAll();

    @Query("SELECT * from tb_movie")
    LiveData<List<Movie>> getAllMovies();

    @Query("SELECT * from tb_movie WHERE id ==  :idmovie")
    LiveData<Movie> getMoviebyId(int idmovie);

    @Query("SELECT * from tb_movie WHERE title LIKE '%'||:condition||'%' ")
    LiveData<List<Movie>> getListMovieLike(String condition);
}
