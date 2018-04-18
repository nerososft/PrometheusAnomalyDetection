package com.neroyang.anomalydetection.prometheus.response;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Map;

/**
 * Author neroyang
 * Email  nerosoft@outlook.com
 * Date   2018/4/18
 * Time   下午11:59
 */
public class Instant implements Serializable {

        private Map<String,String> metric;
        private Object[] value;

    public Instant() {
    }

    public Instant(Map<String, String> metric, Object[] value) {
        this.metric = metric;
        this.value = value;
    }

    public Map<String, String> getMetric() {
        return metric;
    }

    public void setMetric(Map<String, String> metric) {
        this.metric = metric;
    }

    public Object[] getValue() {
        return value;
    }

    public void setValue(Object[] value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Instant{" +
                "metric=" + metric +
                ", value=" + Arrays.toString(value) +
                '}';
    }
}
