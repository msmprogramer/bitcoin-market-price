package com.number26.bitcoin.mvp.interactors;

import android.support.annotation.NonNull;

import com.number26.bitcoin.data.api.BitCoinChartsService;
import com.number26.bitcoin.data.model.BitCoinMarketPriceResponse;
import com.number26.bitcoin.data.model.GraphPoint;
import com.number26.bitcoin.data.rest.BitCoinChartsRestClient;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class BitCoinChartsInteractorImpl implements BitCoinChartsInteractor{

    private BitCoinChartsService bitCoinChartsService;

    public BitCoinChartsInteractorImpl() {
        bitCoinChartsService = new BitCoinChartsRestClient().getService();
    }

    @Override
    public void getBitCoinMarketPriceChart(@NonNull final OnFinishedListener<List<GraphPoint>> listener) {
        bitCoinChartsService.getBitCoinMarketPriceChart(new Callback<BitCoinMarketPriceResponse>() {
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
