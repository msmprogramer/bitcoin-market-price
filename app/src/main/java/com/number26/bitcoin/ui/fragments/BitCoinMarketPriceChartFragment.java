package com.number26.bitcoin.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.number26.bitcoin.R;
import com.number26.bitcoin.data.model.GraphChartValue;
import com.number26.bitcoin.mvp.interactors.BitCoinChartsInteractorImpl;
import com.number26.bitcoin.mvp.presenters.BitCoinMarketPriceChartPresenter;
import com.number26.bitcoin.mvp.views.BitCoinMarketPriceChartContract;
import com.number26.bitcoin.ui.views.LineChartView;

import java.util.List;

public class BitCoinMarketPriceChartFragment extends Fragment implements BitCoinMarketPriceChartContract.View {

    private static final String TAG = "BitCoinMarketFragment";

    private LineChartView lineChartView;

    private ProgressBar progressBarLoading;

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
        return inflater.inflate(R.layout.fragment_bit_coin_market_price, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        lineChartView = (LineChartView) view.findViewById(R.id.lineChart_view);

        progressBarLoading = (ProgressBar) view.findViewById(R.id.loading_progressBar);

        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        bitCoinMarketPriceChartPresenter.loadBitCoinMarketPriceValues();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        bitCoinMarketPriceChartPresenter.stopPresenter();
    }

    @Override
    public void showProgress() {
        progressBarLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBarLoading.setVisibility(View.GONE);
    }

    @Override
    public void showBitCoinMarketPriceChart(List<GraphChartValue> graphChartValues) {
        Log.d(TAG, graphChartValues.size() + "");

        lineChartView.setChartData(graphChartValues);
    }

//    private float[] getRandomData() {
//        return new float[] { 220,260.5f, 266.07f, 247.83f, 245.68f, 251.98f, 248.63f,
//                251.52f, 244.05f, 245.18f, 242.92f, 242.7f, 252.44f};
//    }

    @Override
    public void showFailureMessage() {
        //lineChartView.setChartData(getRandomData());
        Log.d(TAG, "showFailureMessage: ");    }
}
