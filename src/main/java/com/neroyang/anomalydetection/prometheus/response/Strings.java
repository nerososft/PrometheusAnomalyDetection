package com.neroyang.anomalydetection.prometheus.response;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Author neroyang
 * Email  nerosoft@outlook.com
 * Date   2018/4/18
 * Time   下午11:56
 */
public class Strings implements Serializable {
    private Object[] data;

    public Strings() {
    }

    public Strings(Object[] data) {
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
        return "Strings{" +
                "data=" + Arrays.toString(data) +
                '}';
    }
}
