package com.number26.bitcoin.data.rest;

import android.support.annotation.Nullable;

import com.number26.bitcoin.data.api.BlockChainChartsService;
import com.number26.bitcoin.utils.Constants;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;

public class BlockChainRestClient {

    private BlockChainChartsService blockChainChartsService;


    RequestInterceptor requestInterceptor = new RequestInterceptor() {
        @Override
        public void intercept(RequestFacade request) {
            request.addHeader("Content-Type", "application/json");
        }
    };

    public BlockChainRestClient() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(Constants.BACKEND_API_URL)
                .setRequestInterceptor(requestInterceptor)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();

        blockChainChartsService = restAdapter.create(BlockChainChartsService.class);
    }

    @Nullable
    public BlockChainChartsService getService() {
        return blockChainChartsService;
    }
}
