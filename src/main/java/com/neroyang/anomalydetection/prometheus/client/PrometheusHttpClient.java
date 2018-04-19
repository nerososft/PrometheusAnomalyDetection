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


    /**
     * match[]=<series_selector>: Repeated series selector argument that selects the series to return. At least one match[] argument must be provided.
     * start=<rfc3339 | unix_timestamp>: Start timestamp.
     * end=<rfc3339 | unix_timestamp>: End timestamp.
     * @param match
     * @param start
     * @param end
     * @return
     * @throws URISyntaxException
     * @throws ParametersIncorrectException
     * @throws UnprocessableEntityException
     * @throws ServiceUnavailableException
     * @throws IOException
     */
    HttpResponse<QueryData> findSeriesByLabelMatchers(String match,String start,String end) throws URISyntaxException, ParametersIncorrectException, UnprocessableEntityException, ServiceUnavailableException, IOException {
        URIBuilder uriBuilder = new URIBuilder(prometheusHttpApi.findSeriesByLabelMatchers());
        uriBuilder.addParameter("match[]",match);
        uriBuilder.addParameter("start",start);
        uriBuilder.addParameter("end",end);

        HttpResponse<QueryData> httpResponse = doHttpRequest(HttpResponse.class,uriBuilder);
        return httpResponse;
    }

    /**
     * The following endpoint returns a list of label values for a provided label name:
     * @param label
     * @return
     * @throws URISyntaxException
     * @throws ParametersIncorrectException
     * @throws UnprocessableEntityException
     * @throws ServiceUnavailableException
     * @throws IOException
     */
    HttpResponse<QueryData> queryingLabelValues(String label) throws URISyntaxException, ParametersIncorrectException, UnprocessableEntityException, ServiceUnavailableException, IOException {
        URIBuilder uriBuilder = new URIBuilder(prometheusHttpApi.queryingLabelValues(label));

        HttpResponse<QueryData> httpResponse = doHttpRequest(HttpResponse.class,uriBuilder);
        return httpResponse;
    }

    /**
     * The following endpoint returns an overview of the current state of the Prometheus target discovery:
     * @return
     * @throws URISyntaxException
     * @throws ParametersIncorrectException
     * @throws UnprocessableEntityException
     * @throws ServiceUnavailableException
     * @throws IOException
     */
    HttpResponse<QueryData> targets() throws URISyntaxException, ParametersIncorrectException, UnprocessableEntityException, ServiceUnavailableException, IOException {
        URIBuilder uriBuilder = new URIBuilder(prometheusHttpApi.targets());

        HttpResponse<QueryData> httpResponse = doHttpRequest(HttpResponse.class,uriBuilder);
        return httpResponse;
    }

    /**
     * The following endpoint returns an overview of the current state of the Prometheus alertmanager discovery:
     * @return
     * @throws URISyntaxException
     * @throws ParametersIncorrectException
     * @throws UnprocessableEntityException
     * @throws ServiceUnavailableException
     * @throws IOException
     */
    HttpResponse<QueryData> alertmanagers() throws URISyntaxException, ParametersIncorrectException, UnprocessableEntityException, ServiceUnavailableException, IOException {
        URIBuilder uriBuilder = new URIBuilder(prometheusHttpApi.alertmanagers());

        HttpResponse<QueryData> httpResponse = doHttpRequest(HttpResponse.class,uriBuilder);
        return httpResponse;
    }

    /**
     * The following endpoint returns currently loaded configuration file:
     * @return
     * @throws URISyntaxException
     * @throws ParametersIncorrectException
     * @throws UnprocessableEntityException
     * @throws ServiceUnavailableException
     * @throws IOException
     */
    HttpResponse<QueryData> config() throws URISyntaxException, ParametersIncorrectException, UnprocessableEntityException, ServiceUnavailableException, IOException {
        URIBuilder uriBuilder = new URIBuilder(prometheusHttpApi.config());

        HttpResponse<QueryData> httpResponse = doHttpRequest(HttpResponse.class,uriBuilder);
        return httpResponse;
    }


    /**
     * The following endpoint returns flag values that Prometheus was configured with:
     * @return
     * @throws URISyntaxException
     * @throws ParametersIncorrectException
     * @throws UnprocessableEntityException
     * @throws ServiceUnavailableException
     * @throws IOException
     */
    HttpResponse<QueryData> flags() throws URISyntaxException, ParametersIncorrectException, UnprocessableEntityException, ServiceUnavailableException, IOException {
        URIBuilder uriBuilder = new URIBuilder(prometheusHttpApi.flags());

        HttpResponse<QueryData> httpResponse = doHttpRequest(HttpResponse.class,uriBuilder);
        return httpResponse;
    }




}
