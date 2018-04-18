package com.pingcap.anomalydetection.core.forecast;

import com.pingcap.anomalydetection.entity.DataPoint;

import java.util.List;

/**
 * Author neroyang
 * Email  nerosoft@outlook.com
 * Date   2018/4/18
 * Time   上午12:17
 */
public class LinearRegression {

    private Double a;
    private Double b;
    private List<DataPoint> dataPoints;

    public void init(List<DataPoint> dataPoints){
        this.dataPoints = dataPoints;
    }




}
