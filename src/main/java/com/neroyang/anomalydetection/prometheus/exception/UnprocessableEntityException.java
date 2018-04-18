package com.neroyang.anomalydetection.prometheus.exception;

/**
 * Author neroyang
 * Email  nerosoft@outlook.com
 * Date   2018/4/19
 * Time   上午12:36
 */
public class UnprocessableEntityException extends Exception {
    public UnprocessableEntityException() {
    }

    public UnprocessableEntityException(String message) {
        super(message);
    }
}
