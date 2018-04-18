package com.neroyang.anomalydetection.prometheus.response;

import java.io.Serializable;
import java.util.List;
import java.util.Random;

/**
 * Author neroyang
 * Email  nerosoft@outlook.com
 * Date   2018/4/18
 * Time   下午11:55
 */
public class RangeVectors implements Serializable {
    private List<Range> data;

    public RangeVectors() {
    }

    public RangeVectors(List<Range> data) {
        this.data = data;
    }

    public List<Range> getData() {
        return data;
    }

    public void setData(List<Range> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RangeVectors{" +
                "data=" + data +
                '}';
    }
}
