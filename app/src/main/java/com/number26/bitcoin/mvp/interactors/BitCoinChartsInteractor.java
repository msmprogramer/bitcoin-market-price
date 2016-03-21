package com.number26.bitcoin.mvp.interactors;

import android.support.annotation.NonNull;

import com.number26.bitcoin.data.model.GraphChartValue;

import java.util.List;

public interface BitCoinChartsInteractor {

    void getBitCoinMarketPriceChart(@NonNull OnFinishedListener<List<GraphChartValue>> listener);

}
