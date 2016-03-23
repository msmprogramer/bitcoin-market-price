package com.number26.bitcoin.data.rest;

import android.support.annotation.Nullable;

import com.number26.bitcoin.data.api.BitCoinChartsService;
import com.number26.bitcoin.utils.Constants;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;

public class BitCoinChartsRestClient {

    private BitCoinChartsService bitCoinChartsService;


    RequestInterceptor requestInterceptor = new RequestInterceptor() {
        @Override
        public void intercept(RequestFacade request) {
            request.addHeader("Content-Type", "application/json");
        }
    };

    public BitCoinChartsRestClient() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(Constants.BACKEND_API_URL)
                .setRequestInterceptor(requestInterceptor)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();

        bitCoinChartsService = restAdapter.create(BitCoinChartsService.class);
    }

    @Nullable
    public BitCoinChartsService getService() {
        return bitCoinChartsService;
    }
}
