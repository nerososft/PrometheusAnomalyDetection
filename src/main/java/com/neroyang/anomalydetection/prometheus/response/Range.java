package com.neroyang.anomalydetection.prometheus.response;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Map;

/**
 * Author neroyang
 * Email  nerosoft@outlook.com
 * Date   2018/4/19
 * Time   上午12:02
 */
public class Range implements Serializable {
    private Map<String,String> metric;
    private Object[][] values;


    public Range() {
    }

    public Range(Map<String, String> metric, Object[][] values) {
        this.metric = metric;
        this.values = values;
    }

    public Map<String, String> getMetric() {
        return metric;
    }

    public void setMetric(Map<String, String> metric) {
        this.metric = metric;
    }

    public Object[][] getValues() {
        return values;
    }

    public void setValues(Object[][] values) {
        this.values = values;
    }

    @Override
    public String toString() {
        return "Range{" +
                "metric=" + metric +
                ", values=" + Arrays.toString(values) +
                '}';
    }
}
