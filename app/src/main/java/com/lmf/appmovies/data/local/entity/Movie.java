package com.lmf.appmovies.data.local.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "tb_movie")
public class Movie {

    @PrimaryKey
    @NonNull
    private Integer idmovie;

    @NonNull
    @ColumnInfo(name = "id")
    private Integer id;

    @NonNull
    @ColumnInfo(name = "vote_average")
    private Double voteAverage;
    @NonNull
    @ColumnInfo(name = "vote_count")
    private Integer voteCount;

    private Boolean video;
    @NonNull
    @ColumnInfo(name = "media_type")
    private String mediaType;
    private String title;
    private Double popularity;
    @NonNull
    @ColumnInfo(name = "poster_path")
    private String posterPath;
    @NonNull
    @ColumnInfo(name = "original_title")
    private String originalTitle;
    @NonNull
    @ColumnInfo(name = "backdrop_path")
    private String backdropPath;
    private String overview;
    @NonNull
    @ColumnInfo(name = "release_date")
    private String releaseDate;

    public Double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdmovie() {
        return idmovie;
    }

    public void setIdmovie(Integer idmovie) {
        this.idmovie = idmovie;
    }

    public Boolean getVideo() {
        return video;
    }

    public void setVideo(Boolean video) {
        this.video = video;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Movie(int idmovie,
                 int id,
                 double voteAverage,
                 Integer voteCount,
                 Boolean video,
                 String mediaType,
                 String title,
                 Double popularity,
                 String posterPath,
                 String originalTitle,
                 String backdropPath,
                 String overview,
                 String releaseDate) {
        this.id = idmovie;
        this.id = id;
        this.voteAverage = voteAverage;
        this.voteCount = voteCount;
        this.video = video;
        this.mediaType = mediaType;
        this.title = title;
        this.popularity = popularity;
        this.posterPath = posterPath;
        this.originalTitle = originalTitle;
        this.backdropPath = backdropPath;
        this.overview = overview;
        this.releaseDate = releaseDate;
    }

}
