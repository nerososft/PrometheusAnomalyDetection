package com.pingcap.anomalydetection.exception;

/**
 * Author neroyang
 * Email  nerosoft@outlook.com
 * Date   2018/4/17
 * Time   下午5:48
 */
public class NoDetectionMethodException extends Exception {

    public NoDetectionMethodException() {
    }

    public NoDetectionMethodException(String message) {
        super(message);
    }
}
