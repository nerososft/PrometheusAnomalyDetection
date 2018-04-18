package com.neroyang.anomalydetection.prometheus.response;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Author neroyang
 * Email  nerosoft@outlook.com
 * Date   2018/4/18
 * Time   下午11:56
 */
public class Scalars implements Serializable {
    private Object[] data;

    public Scalars() {
    }

    public Scalars(Object[] data) {
        this.data = data;
    }

    public Object[] getData() {
        return data;
    }

    public void setData(Object[] data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Scalars{" +
                "data=" + Arrays.toString(data) +
                '}';
    }
}
