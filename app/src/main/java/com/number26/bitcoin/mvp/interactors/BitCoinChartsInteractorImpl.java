package com.number26.bitcoin.mvp.interactors;

import android.support.annotation.NonNull;

import com.number26.bitcoin.data.api.BlockChainChartsService;
import com.number26.bitcoin.data.model.BitCoinMarketPriceResponse;
import com.number26.bitcoin.data.model.GraphChartValue;
import com.number26.bitcoin.data.rest.BlockChainRestClient;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class BitCoinChartsInteractorImpl implements BitCoinChartsInteractor{

    private BlockChainChartsService blockChainChartsService;

    public BitCoinChartsInteractorImpl() {
        blockChainChartsService = new BlockChainRestClient().getService();
    }

    @Override
    public void getBitCoinMarketPriceChart(@NonNull final OnFinishedListener<List<GraphChartValue>> listener) {
        blockChainChartsService.getBitCoinMarketPriceChart(new Callback<BitCoinMarketPriceResponse>() {
            @Override
            public void success(BitCoinMarketPriceResponse bitCoinMarketPriceResponse, Response response) {
                listener.onSuccess(bitCoinMarketPriceResponse.getValues());
            }

            @Override
            public void failure(RetrofitError error) {
                listener.onFailure();
            }
        });
    }
}
