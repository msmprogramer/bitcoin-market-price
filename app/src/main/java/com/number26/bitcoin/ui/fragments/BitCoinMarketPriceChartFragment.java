package com.number26.bitcoin.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.number26.bitcoin.R;
import com.number26.bitcoin.data.model.GraphChartValue;
import com.number26.bitcoin.mvp.interactors.BitCoinChartsInteractorImpl;
import com.number26.bitcoin.mvp.presenters.BitCoinMarketPriceChartPresenter;
import com.number26.bitcoin.mvp.views.BitCoinMarketPriceChartContract;

import java.util.List;

public class BitCoinMarketPriceChartFragment extends Fragment implements BitCoinMarketPriceChartContract.View {

    private static final String TAG = "BitCoinMarketFragment";

    private BitCoinMarketPriceChartPresenter bitCoinMarketPriceChartPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bitCoinMarketPriceChartPresenter = new BitCoinMarketPriceChartPresenter(
                this,
                new BitCoinChartsInteractorImpl()
                );
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bitcoin_market_price_chart, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        bitCoinMarketPriceChartPresenter.loadBitCoinMarketPriceValues();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showBitCoinMarketPriceChart(List<GraphChartValue> graphChartValues) {
        Log.d(TAG, graphChartValues.size()+"");
    }

    @Override
    public void showFailureMessage() {
        Log.d(TAG, "showFailureMessage: ");    }
}
