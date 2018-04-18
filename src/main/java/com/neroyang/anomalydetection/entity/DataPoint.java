package com.neroyang.anomalydetection.entity;

import java.io.Serializable;

/**
 * Author neroyang
 * Email  nerosoft@outlook.com
 * Date   2018/4/18
 * Time   上午12:24
 */
public class DataPoint implements Serializable {

    private Long timeStamp;
    private Double value;

    public DataPoint() {
    }

    public DataPoint(Long timeStamp, Double value) {
        this.timeStamp = timeStamp;
        this.value = value;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "DataPoint{" +
                "timeStamp=" + timeStamp +
                ", value=" + value +
                '}';
    }
}
