package com.lmf.appmovies.utils.rx;

public interface RxAPICallback<T> {
    void onSuccess(T data);

    void onFailed(Throwable t);
}
