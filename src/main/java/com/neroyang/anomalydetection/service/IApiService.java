package com.neroyang.anomalydetection.service;

import com.neroyang.anomalydetection.CONSTANT.DetectionMethod;
import com.neroyang.anomalydetection.dto.Metric;
import com.neroyang.anomalydetection.dto.Result;
import com.neroyang.anomalydetection.exception.ResponseReturnFalseException;
import com.neroyang.anomalydetection.prometheus.exception.ParametersIncorrectException;
import com.neroyang.anomalydetection.prometheus.exception.ServiceUnavailableException;
import com.neroyang.anomalydetection.prometheus.exception.UnprocessableEntityException;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Author neroyang
 * Email  nerosoft@outlook.com
 * Date   2018/4/17
 * Time   下午11:50
 */
public interface IApiService {

    Result<Metric> currentMetricDetection(DetectionMethod detectionMethod,String query) throws ParametersIncorrectException, IOException, UnprocessableEntityException, ServiceUnavailableException, URISyntaxException, ResponseReturnFalseException;

}
