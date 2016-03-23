package com.number26.bitcoin.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class GraphChartValue {

    @SerializedName("x")
    @Expose
    private long x;
    @SerializedName("y")
    @Expose
    private float y;

    /**
     *
     * @return
     * The x
     */
    public long getX() {
        return x;
    }

    /**
     *
     * @param x
     * The x
     */
    public void setX(long x) {
        this.x = x;
    }

    /**
     *
     * @return
     * The y
     */
    public float getY() {
        return y;
    }

    /**
     *
     * @param y
     * The y
     */
    public void setY(float y) {
        this.y = y;
    }

}