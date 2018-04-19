package com.neroyang.anomalydetection.prometheus.client;

import com.neroyang.anomalydetection.prometheus.api.PrometheusHttpApi;
import com.neroyang.anomalydetection.prometheus.exception.ParametersIncorrectException;
import com.neroyang.anomalydetection.prometheus.exception.ServiceUnavailableException;
import com.neroyang.anomalydetection.prometheus.exception.UnprocessableEntityException;
import com.neroyang.anomalydetection.prometheus.response.HttpResponse;
import com.neroyang.anomalydetection.prometheus.response.QueryData;
import com.neroyang.anomalydetection.utils.ConfigUtil;
import org.apache.http.client.utils.URIBuilder;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Author neroyang
 * Email  nerosoft@outlook.com
 * Date   2018/4/19
 * Time   上午10:32
 */
public class PrometheusHttpClient extends HttpClient {

    private ConfigUtil configUtil;
    private PrometheusHttpApi prometheusHttpApi;

    public PrometheusHttpClient() throws IOException {
        configUtil = new ConfigUtil();
        //Load Config
        String PrometheusHost = configUtil.getConfig("prometheus.host");
        String PrometheusPort = configUtil.getConfig("prometheus.port");
        String PrometheusNameSpace = configUtil.getConfig("prometheus.namespace");

        this.prometheusHttpApi = new PrometheusHttpApi(PrometheusHost,PrometheusPort,PrometheusNameSpace);
    }

    public PrometheusHttpClient(String host, String port, String nameSpace) throws IOException {
        this.prometheusHttpApi = new PrometheusHttpApi(host,port,nameSpace);

    }

    /**
     *
     * query=<string>: Prometheus expression query string.
     * time=<rfc3339 | unix_timestamp>: Evaluation timestamp. Optional.
     * timeout=<duration>: Evaluation timeout. Optional. Defaults to and is capped by the value of the -query.timeout flag.
     * @param query
     * @param time
     * @param timeout
     * @return
     * @throws URISyntaxException
     * @throws ParametersIncorrectException
     * @throws UnprocessableEntityException
     * @throws ServiceUnavailableException
     * @throws IOException
     */
    HttpResponse<QueryData> instantQueries(String query, String time, Integer timeout) throws URISyntaxException, ParametersIncorrectException, UnprocessableEntityException, ServiceUnavailableException, IOException {
        URIBuilder uriBuilder = new URIBuilder(prometheusHttpApi.instantQueries());
        uriBuilder.addParameter("query",query);
        uriBuilder.addParameter("time",time);
        uriBuilder.addParameter("timeout",String.valueOf(timeout));


        HttpResponse<QueryData> httpResponse = doHttpRequest(HttpResponse.class,uriBuilder);
        return httpResponse;
    }

    /**
     *
     * query=<string>: Prometheus expression query string.
     * start=<rfc3339 | unix_timestamp>: Start timestamp.
     * end=<rfc3339 | unix_timestamp>: End timestamp.
     * step=<duration>: Query resolution step width.
     * timeout=<duration>: Evaluation timeout. Optional. Defaults to and is capped by the value of the -query.timeout flag.
     * @param query
     * @param start
     * @param end
     * @param step
     * @param timeout
     * @return
     * @throws URISyntaxException
     * @throws ParametersIncorrectException
     * @throws UnprocessableEntityException
     * @throws ServiceUnavailableException
     * @throws IOException
     */
    HttpResponse<QueryData> rangeQueries(String query,String start,String end,Integer step,Integer timeout) throws URISyntaxException, ParametersIncorrectException, UnprocessableEntityException, ServiceUnavailableException, IOException {
        URIBuilder uriBuilder = new URIBuilder(prometheusHttpApi.instantQueries());
        uriBuilder.addParameter("query",query);
        uriBuilder.addParameter("start",start);
        uriBuilder.addParameter("end",end);
        uriBuilder.addParameter("step",String.valueOf(step));
        uriBuilder.addParameter("timeout",String.valueOf(timeout));

        HttpResponse<QueryData> httpResponse = doHttpRequest(HttpResponse.class,uriBuilder);

        return httpResponse;
    }
}
