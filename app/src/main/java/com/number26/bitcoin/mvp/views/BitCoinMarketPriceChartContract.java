package com.number26.bitcoin.mvp.views;

import com.number26.bitcoin.data.model.GraphPoint;

import java.util.List;

public interface BitCoinMarketPriceChartContract {

    interface View {

        void showProgress();

        void hideProgress();

        void showBitCoinMarketPriceChart(List<GraphPoint> graphPoints);

        void showFailureMessage();
    }

    interface UserActionsListener {

        void loadBitCoinMarketPriceValues();

        void stopPresenter();
    }
}
