package com.number26.bitcoin.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.number26.bitcoin.R;
import com.number26.bitcoin.data.model.GraphPoint;
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
    public void showBitCoinMarketPriceChart(List<GraphPoint> graphPoints) {
        lineChartView.setChartData(graphPoints);
    }

    @Override
    public void showFailureMessage() {
        Snackbar.make(getView(),
                getString(R.string.error_failed_load_bit_coin_market_price),
                Snackbar.LENGTH_SHORT).
                show();
    }
}
