package com.number26.bitcoin.data.api;

import com.number26.bitcoin.data.model.BitCoinMarketPriceResponse;
import com.number26.bitcoin.data.model.GraphChartValue;
import com.number26.bitcoin.mvp.presenters.BitCoinMarketPriceChartPresenter;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;


public interface BlockChainChartsService {

    @GET("/charts/market-price?format=json")
    void getBitCoinMarketPriceChart(Callback<BitCoinMarketPriceResponse> bitCoinMarketPriceResponseCallback);
}
