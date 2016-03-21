package com.number26.bitcoin.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class GraphChartValue {

    @SerializedName("x")
    @Expose
    private Integer x;
    @SerializedName("y")
    @Expose
    private Double y;

    /**
     *
     * @return
     * The x
     */
    public Integer getX() {
        return x;
    }

    /**
     *
     * @param x
     * The x
     */
    public void setX(Integer x) {
        this.x = x;
    }

    /**
     *
     * @return
     * The y
     */
    public Double getY() {
        return y;
    }

    /**
     *
     * @param y
     * The y
     */
    public void setY(Double y) {
        this.y = y;
    }

}