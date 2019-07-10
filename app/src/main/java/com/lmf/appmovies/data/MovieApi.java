package com.lmf.appmovies.data;

import com.lmf.appmovies.data.remote.ResponseMovie;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface MovieApi {

    @Headers({"Content-Type:application/json;charset=utf-8"})
    @GET("list/1")
    Observable<ResponseMovie> getListMovies(@Header("Authorization") String authKey,
                                          @Query("sort_by") String sortby);

}
