package com.pingcap.anomalydetection.exception;

/**
 * Author neroyang
 * Email  nerosoft@outlook.com
 * Date   2017/8/25
 * Time   下午1:11
 */
public class ApiException extends Exception {
    public ApiException() {
    }

    public ApiException(String message) {
        super(message);
    }

    public ApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApiException(Throwable cause) {
        super(cause);
    }
}
