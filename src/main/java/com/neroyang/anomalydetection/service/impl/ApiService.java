package com.neroyang.anomalydetection.service.impl;

import com.neroyang.anomalydetection.CONSTANT.DetectionMethod;
import com.neroyang.anomalydetection.dto.Metric;
import com.neroyang.anomalydetection.dto.Result;
import com.neroyang.anomalydetection.exception.ResponseReturnFalseException;
import com.neroyang.anomalydetection.prometheus.client.PrometheusHttpClient;
import com.neroyang.anomalydetection.prometheus.exception.ParametersIncorrectException;
import com.neroyang.anomalydetection.prometheus.exception.ServiceUnavailableException;
import com.neroyang.anomalydetection.prometheus.exception.UnprocessableEntityException;
import com.neroyang.anomalydetection.prometheus.response.HttpResponse;
import com.neroyang.anomalydetection.prometheus.response.QueryData;
import com.neroyang.anomalydetection.service.IApiService;
import com.neroyang.anomalydetection.web.core.ApiMapping;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Author neroyang
 * Email  nerosoft@outlook.com
 * Date   2018/4/17
 * Time   下午11:50
 */
public class ApiService implements IApiService {

    private PrometheusHttpClient prometheusHttpClient;

    public ApiService() throws IOException {
        prometheusHttpClient = new PrometheusHttpClient();
    }

    private Boolean isSuccess(HttpResponse httpResponse) throws ResponseReturnFalseException {
        if("success".equals(httpResponse.getStatus())){
            return true;
        }
        throw new ResponseReturnFalseException(httpResponse.getErrorType()+":"+httpResponse.getError());
    }

    /**
     * default current Metric Detection
     * @param detectionMethod
     * @param query
     * @return
     */
    @ApiMapping("api.prometheus.metric.detection")
    public Result<Metric> currentMetricDetection(DetectionMethod detectionMethod, String query) throws ParametersIncorrectException, IOException, UnprocessableEntityException, ServiceUnavailableException, URISyntaxException, ResponseReturnFalseException {
        String start = "";
        String end = "";
        HttpResponse<QueryData> queryDataHttpResponse = prometheusHttpClient.rangeQueries(query,start,end,null,null);

        isSuccess(queryDataHttpResponse);

        return null;
    }
}
