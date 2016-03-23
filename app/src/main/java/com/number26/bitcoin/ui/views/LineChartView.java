package com.number26.bitcoin.ui.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import com.google.common.collect.Ordering;
import com.number26.bitcoin.data.model.GraphPoint;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class LineChartView extends View {

    private static final String TAG = "LineChartView";

    private static final int MIN_LINES = 4;
    private static final int MAX_LINES = 20;
    private static final int[] DISTANCES = { 1, 2, 5 };

    private List<GraphPoint> datapoints = Collections.emptyList();
    private Paint paint = new Paint();

    public LineChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    public void setChartData(List<GraphPoint> datapoints) {
        this.datapoints = datapoints;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (this.datapoints.isEmpty()) {
            return;
        }

        getDifferentBetweenDatesInDays();
        float maxValue = getMax(datapoints);
        drawBackground(canvas, maxValue);
        drawLineChart(canvas, maxValue);
    }

    private void drawBackground(Canvas canvas, float maxValue) {
        int range = getLineDistance(maxValue);
        paint.setStyle(Style.FILL);
        paint.setColor(Color.GRAY);
        paint.setTextAlign(Paint.Align.LEFT);
        paint.setTextSize(16);
        paint.setStrokeWidth(1);
        for (int y = 0; y <= maxValue; y += range) {
            final int yPos = (int) getYPos(y, maxValue);

            // turn off anti alias for lines, they get crisper then
            paint.setAntiAlias(false);
            canvas.drawLine(0, yPos, getWidth(), yPos, paint);

            // turn on anti alias again for the text
            paint.setAntiAlias(true);
            canvas.drawText(String.valueOf(y), 0, yPos - 2, paint);
        }
    }

    private int getLineDistance(float maxValue) {
        int distance;
        int distanceIndex = 0;
        int distanceMultiplier = 1;
        int numberOfLines = MIN_LINES;

        do {
            distance = DISTANCES[distanceIndex] * distanceMultiplier;
            numberOfLines = (int) Math.ceil(maxValue / distance);

            distanceIndex++;
            if (distanceIndex == DISTANCES.length) {
                distanceIndex = 0;
                distanceMultiplier *= 10;
            }
        } while (numberOfLines < MIN_LINES || numberOfLines > MAX_LINES);

        return distance;
    }


    private void drawLineChart(Canvas canvas, float maxValue) {
        Path path = new Path();
        path.moveTo(getXPos(0), getYPos(datapoints.get(0).getY(), maxValue));
        for (int i = 1; i < datapoints.size(); i++) {
            path.lineTo(getXPos(i), getYPos(datapoints.get(i).getY(), maxValue));
        }

        paint.setStyle(Style.STROKE);
        paint.setStrokeWidth(4);
        paint.setColor(0xFF33B5E5);
        paint.setAntiAlias(true);
        paint.setShadowLayer(4, 2, 2, 0x80000000);
        canvas.drawPath(path, paint);
        paint.setShadowLayer(0, 0, 0, 0);
    }

    private int getDifferentBetweenDatesInDays() {
        Date startDate = new Date(getMinDate(datapoints));

        Date endDate =  new Date(getMaxDate(datapoints));

        long diff = endDate.getTime() - startDate.getTime() ;

        int days = (int) (diff / (24 * 60 * 60 * 1000));

        return days;
    }


    private long getMinDate(List<GraphPoint> datapoints) {
        Collections.sort(datapoints, new Comparator<GraphPoint>() {
            @Override
            public int compare(GraphPoint lhs, GraphPoint rhs) {
                return new Date(lhs.getX()).compareTo(new Date(rhs.getX()));
            }
        });

        return datapoints.get(0).getX()  * 1000l;
    }

    private long getMaxDate(List<GraphPoint> datapoints) {
        Collections.sort(datapoints, new Comparator<GraphPoint>() {
            @Override
            public int compare(GraphPoint lhs, GraphPoint rhs) {
                return new Date(lhs.getX()).compareTo(new Date(rhs.getX()));
            }
        });

        Log.d(TAG, "getMaxDate: " + datapoints.get(datapoints.size() - 1).getX());

        return datapoints.get(datapoints.size() - 1).getX() * 1000l;
    }


    private float getMax(List<GraphPoint> datapoints) {
        Ordering<GraphPoint> o = new Ordering<GraphPoint>() {
            @Override
            public int compare(GraphPoint left, GraphPoint right) {
                return Float.compare(left.getY(), right.getY());
            }
        };

        return o.max(datapoints).getY();
    }

    private float getYPos(float value, float maxValue) {
        float height = getHeight() - getPaddingTop() - getPaddingBottom();

        // scale it to the view size
        value = (value / maxValue) * height;

        // invert it so that higher values have lower y
        value = height - value;

        // offset it to adjust for padding
        value += getPaddingTop();

        return value;
    }

    private float getXPos(float value) {
        float width = getWidth() - getPaddingLeft() - getPaddingRight();
        float maxValue = datapoints.size() - 1;

        // scale it to the view size
        value = (value / maxValue) * width;

        // offset it to adjust for padding
        value += getPaddingLeft();

        return value;
    }

}