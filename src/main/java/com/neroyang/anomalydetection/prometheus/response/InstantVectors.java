package com.neroyang.anomalydetection.prometheus.response;

import java.io.Serializable;
import java.util.List;

/**
 * Author neroyang
 * Email  nerosoft@outlook.com
 * Date   2018/4/18
 * Time   下午11:56
 */
public class InstantVectors implements Serializable {
    private List<Instant> data;

    public InstantVectors() {
    }

    public InstantVectors(List<Instant> data) {
        this.data = data;
    }

    public List<Instant> getData() {
        return data;
    }

    public void setData(List<Instant> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "InstantVectors{" +
                "data=" + data +
                '}';
    }
}
