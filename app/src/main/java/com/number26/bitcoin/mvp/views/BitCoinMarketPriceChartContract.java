package com.number26.bitcoin.mvp.views;

import android.support.annotation.NonNull;

import com.number26.bitcoin.data.model.GraphChartValue;

import java.util.List;

public interface BitCoinMarketPriceChartContract {

    interface View {

        void showProgress();

        void hideProgress();

        void showBitCoinMarketPriceChart(List<GraphChartValue> graphChartValues);

        void showFailureMessage();
    }

    interface UserActionsListener {

        void loadBitCoinMarketPriceValues();
    }
}
