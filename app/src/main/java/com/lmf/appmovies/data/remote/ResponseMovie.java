package com.lmf.appmovies.data.remote;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseMovie {
    @SerializedName("results")
    @Expose
    private List<ResultMovie> results = null;

    public List<ResultMovie> getResults() {
        return results;
    }

    public void setResults(List<ResultMovie> results) {
        this.results = results;
    }

}
