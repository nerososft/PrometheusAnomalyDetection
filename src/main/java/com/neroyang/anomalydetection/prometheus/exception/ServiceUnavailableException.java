package com.neroyang.anomalydetection.prometheus.exception;

/**
 * Author neroyang
 * Email  nerosoft@outlook.com
 * Date   2018/4/19
 * Time   上午12:38
 */
public class ServiceUnavailableException extends Exception {

    public ServiceUnavailableException() {
    }

    public ServiceUnavailableException(String message) {
        super(message);
    }
}
