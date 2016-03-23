package com.number26.bitcoin.data.api;

import com.number26.bitcoin.data.model.BitCoinMarketPriceResponse;

import retrofit.Callback;
import retrofit.http.GET;


public interface BitCoinChartsService {

    @GET("/charts/market-price?format=json")
    void getBitCoinMarketPriceChart(Callback<BitCoinMarketPriceResponse> bitCoinMarketPriceResponseCallback);
}
