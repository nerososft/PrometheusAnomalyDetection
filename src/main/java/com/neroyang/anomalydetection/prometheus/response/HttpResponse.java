package com.neroyang.anomalydetection.prometheus.response;

import java.io.Serializable;

/**
 * Author neroyang
 * Email  nerosoft@outlook.com
 * Date   2018/4/18
 * Time   下午11:50
 */
public class HttpResponse<T> implements Serializable {

    private String status;// "success" | "error",
    private T data;
    private String errorType;
    private String error;

    public HttpResponse() {
    }

    public HttpResponse(String status, T data) {
        this.status = status;
        this.data = data;
    }

    public HttpResponse(String status, String errorType, String error) {
        this.status = status;
        this.errorType = errorType;
        this.error = error;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getErrorType() {
        return errorType;
    }

    public void setErrorType(String errorType) {
        this.errorType = errorType;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "HttpResponse{" +
                "status='" + status + '\'' +
                ", data=" + data +
                ", errorType='" + errorType + '\'' +
                ", error='" + error + '\'' +
                '}';
    }
}
