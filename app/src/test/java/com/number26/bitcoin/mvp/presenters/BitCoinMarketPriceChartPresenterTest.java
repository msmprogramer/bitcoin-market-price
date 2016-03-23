package com.number26.bitcoin.mvp.presenters;

import com.number26.bitcoin.data.model.GraphPoint;
import com.number26.bitcoin.mvp.interactors.BitCoinChartsInteractor;
import com.number26.bitcoin.mvp.interactors.OnFinishedListener;
import com.number26.bitcoin.mvp.views.BitCoinMarketPriceChartContract;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class BitCoinMarketPriceChartPresenterTest {

    private final static List<GraphPoint> GRAPH_POINTs = new ArrayList<>();

    @Mock
    private BitCoinMarketPriceChartContract.View bitCoinMarketPriceView;
    @Mock
    private BitCoinChartsInteractor bitCoinChartsInteractor;

    private BitCoinMarketPriceChartPresenter bitCoinMarketPriceChartPresenter;

    @Captor
    private ArgumentCaptor<OnFinishedListener<List<GraphPoint>>> callBack;

    @Before
    public void setUp() throws Exception {
        bitCoinMarketPriceChartPresenter = new BitCoinMarketPriceChartPresenter(bitCoinMarketPriceView, bitCoinChartsInteractor);
    }

    @Test
    public void loadBitCoinMarketPriceValuesWhenSuccess() {

        bitCoinMarketPriceChartPresenter.loadBitCoinMarketPriceValues();

        verify(bitCoinMarketPriceView).showProgress();

        verify(bitCoinChartsInteractor).getBitCoinMarketPriceChart(callBack.capture());
        callBack.getValue().onSuccess(GRAPH_POINTs);

        verify(bitCoinMarketPriceView).hideProgress();
        verify(bitCoinMarketPriceView).showBitCoinMarketPriceChart(GRAPH_POINTs);
    }

    @Test
    public void loadBitCoinMarketPriceValuesWhenFailure() {

        bitCoinMarketPriceChartPresenter.loadBitCoinMarketPriceValues();

        verify(bitCoinMarketPriceView).showProgress();

        verify(bitCoinChartsInteractor).getBitCoinMarketPriceChart(callBack.capture());
        callBack.getValue().onFailure();

        verify(bitCoinMarketPriceView).hideProgress();
        verify(bitCoinMarketPriceView).showFailureMessage();
    }



}