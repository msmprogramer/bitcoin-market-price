package com.number26.bitcoin.data.model;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;



public class BitCoinMarketPriceResponse {

    @SerializedName("values")
    @Expose
    private List<GraphChartValue> values = new ArrayList<GraphChartValue>();

    /**
     *
     * @return
     * The values
     */
    public List<GraphChartValue> getValues() {
        return values;
    }

    /**
     *
     * @param values
     * The values
     */
    public void setValues(List<GraphChartValue> values) {
        this.values = values;
    }

}