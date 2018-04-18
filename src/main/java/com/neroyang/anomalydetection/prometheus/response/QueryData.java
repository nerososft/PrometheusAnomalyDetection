package com.neroyang.anomalydetection.prometheus.response;

import java.io.Serializable;

/**
 * Author neroyang
 * Email  nerosoft@outlook.com
 * Date   2018/4/18
 * Time   下午11:54
 */
public class QueryData<T> implements Serializable {
    private String resultType;//"matrix" | "vector" | "scalar" | "string",
    private T result;

    public QueryData() {
    }

    public QueryData(String resultType, T result) {
        this.resultType = resultType;
        this.result = result;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "QueryData{" +
                "resultType='" + resultType + '\'' +
                ", result=" + result +
                '}';
    }
}
