package com.number26.bitcoin.data.api;

import com.number26.bitcoin.data.model.GraphChartValue;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;


public interface BlockChainChartsService {

    @GET("/charts/market-price?format=json")
    void getBitCoinMarketPriceChart(Callback<List<GraphChartValue>> graphChartValues);
}
