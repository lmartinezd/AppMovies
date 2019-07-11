package com.lmf.appmovies;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import com.lmf.appmovies.data.MovieApi;
import com.lmf.appmovies.data.local.MovieRoomDatabase;
import com.lmf.appmovies.data.local.dao.MovieDao;
import com.lmf.appmovies.utils.Constans;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class AppMovies extends Application {

    private static AppMovies appInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        appInstance = this;
        Stetho.initializeWithDefaults(this);
    }

    public static AppMovies getApp() {
        if (appInstance == null)
            throw new IllegalStateException("The application is not created yet!");
        return appInstance;
    }

    public static Retrofit getClient() {
        return new Retrofit.Builder()
                .baseUrl(Constans.BASE_URL)
                .client(okHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    private static OkHttpClient okHttpClient() {
        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
        okHttpClient
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS);
        if (BuildConfig.DEBUG) {
            okHttpClient.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .addNetworkInterceptor(new StethoInterceptor());
        }
        return okHttpClient.build();
    }

    public static MovieApi getMovieApi() {
        return getClient().create(MovieApi.class);
    }

    public static MovieRoomDatabase geMovieRoomDatabase() {
        return MovieRoomDatabase.getDatabase(AppMovies.getApp().getApplicationContext());
    }

    public static MovieDao getMovieDao() {
        return geMovieRoomDatabase().movieDao();
    }

    public static boolean hasNetwork() {
        ConnectivityManager connectivityManager = (ConnectivityManager)
                getApp().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activenet = connectivityManager.getActiveNetworkInfo();
        return activenet != null;
    }
}
