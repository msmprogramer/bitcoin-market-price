package com.number26.bitcoin.mvp.presenters;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.number26.bitcoin.data.model.GraphChartValue;
import com.number26.bitcoin.mvp.interactors.BitCoinChartsInteractor;
import com.number26.bitcoin.mvp.interactors.OnFinishedListener;
import com.number26.bitcoin.mvp.views.BitCoinMarketPriceChartContract;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

public class BitCoinMarketPriceChartPresenter implements BitCoinMarketPriceChartContract.UserActionsListener {

    private BitCoinMarketPriceChartContract.View bitCoinMarketPriceChartView;

    @NonNull
    private final BitCoinChartsInteractor bitCoinChartsInteractor;

    public BitCoinMarketPriceChartPresenter(
            @NonNull BitCoinMarketPriceChartContract.View bitCoinMarketPriceChartView,
            @NonNull BitCoinChartsInteractor bitCoinChartsInteractor
    )
    {
        this.bitCoinMarketPriceChartView = checkNotNull(bitCoinMarketPriceChartView);
        this.bitCoinChartsInteractor = checkNotNull(bitCoinChartsInteractor);
    }


    @Override
    public void loadBitCoinMarketPriceValues() {
        bitCoinMarketPriceChartView.showProgress();

        bitCoinChartsInteractor.getBitCoinMarketPriceChart(new OnFinishedListener<List<GraphChartValue>>() {
            @Override
            public void onSuccess(@Nullable List<GraphChartValue> data) {

                if (bitCoinMarketPriceChartView == null) {
                    return;
                }

                bitCoinMarketPriceChartView.hideProgress();
                bitCoinMarketPriceChartView.showBitCoinMarketPriceChart(data);
            }

            @Override
            public void onFailure() {

                if (bitCoinMarketPriceChartView == null) {
                    return;
                }

                bitCoinMarketPriceChartView.hideProgress();
                bitCoinMarketPriceChartView.showFailureMessage();
            }
        });
    }

    @Override
    public void stopPresenter() {
        this.bitCoinMarketPriceChartView = null;
    }
}
