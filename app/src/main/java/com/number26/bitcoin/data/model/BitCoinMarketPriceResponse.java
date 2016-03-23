package com.number26.bitcoin.data.model;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;



public class BitCoinMarketPriceResponse {

    @SerializedName("values")
    @Expose
    private List<GraphPoint> values = new ArrayList<GraphPoint>();

    /**
     *
     * @return
     * The values
     */
    public List<GraphPoint> getValues() {
        return values;
    }

    /**
     *
     * @param values
     * The values
     */
    public void setValues(List<GraphPoint> values) {
        this.values = values;
    }

}