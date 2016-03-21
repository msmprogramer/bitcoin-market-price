package com.number26.bitcoin.mvp.interactors;

import android.support.annotation.Nullable;

public interface OnFinishedListener<T> {

    void onSuccess(@Nullable T data);
    void onFailure();
}
