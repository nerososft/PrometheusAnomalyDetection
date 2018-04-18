package com.neroyang.anomalydetection.prometheus.client;

import com.google.gson.Gson;
import com.neroyang.anomalydetection.prometheus.api.PrometheusHttpApi;
import com.neroyang.anomalydetection.prometheus.exception.ParametersIncorrectException;
import com.neroyang.anomalydetection.prometheus.exception.ServiceUnavailableException;
import com.neroyang.anomalydetection.prometheus.exception.UnprocessableEntityException;
import com.neroyang.anomalydetection.prometheus.response.HttpResponse;
import com.neroyang.anomalydetection.prometheus.response.QueryData;
import com.neroyang.anomalydetection.utils.ConfigUtil;
import com.neroyang.anomalydetection.utils.spring.PropertyPlaceholder;
import com.sun.javafx.fxml.builder.URLBuilder;
import org.apache.http.ConnectionReuseStrategy;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.DefaultBHttpClientConnection;
import org.apache.http.impl.DefaultConnectionReuseStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHttpRequest;
import org.apache.http.protocol.*;
import org.apache.http.util.EntityUtils;

import javax.management.Query;
import javax.xml.ws.Response;
import java.io.IOException;
import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import static com.neroyang.anomalydetection.prometheus.constant.CONSTANT.CODE_400;
import static com.neroyang.anomalydetection.prometheus.constant.CONSTANT.CODE_422;
import static com.neroyang.anomalydetection.prometheus.constant.CONSTANT.CODE_503;

/**
 * Author neroyang
 * Email  nerosoft@outlook.com
 * Date   2018/4/18
 * Time   上午12:01
 */
public class HttpClient {
    private PrometheusHttpApi prometheusHttpApi;
    private ConfigUtil configUtil;

    public HttpClient() throws IOException {

        configUtil = new ConfigUtil();
        //Load Config
        String PrometheusHost = configUtil.getConfig("prometheus.host");
        String PrometheusPort = configUtil.getConfig("prometheus.port");
        String PrometheusNameSpace = configUtil.getConfig("prometheus.namespace");

        this.prometheusHttpApi = new PrometheusHttpApi(PrometheusHost,PrometheusPort,PrometheusNameSpace);
    }

    public HttpClient(String host, String port, String nameSpace){
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



    private <T> T doHttpRequest(Class<T> data, URIBuilder uri) throws IOException, URISyntaxException, ParametersIncorrectException, UnprocessableEntityException, ServiceUnavailableException {

        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        try {
            httpClient = HttpClients.createDefault();

            HttpGet get = new HttpGet(uri.build());
            //执行请求
            response = httpClient.execute(get);
            //取响应的结果
            int statusCode = response.getStatusLine().getStatusCode();
            switch (statusCode){
                case 400:
                    throw new ParametersIncorrectException(CODE_400);
                case 422:
                    throw new UnprocessableEntityException(CODE_422);
                case 503:
                    throw new ServiceUnavailableException(CODE_503);
            }

            HttpEntity entity = response.getEntity();
            String string = EntityUtils.toString(entity, "utf-8");
            Gson gson = new Gson();
            T result = gson.fromJson(string,data);
            return result;
        }finally {
            if(response!=null) {
                response.close();
            }
            if(httpClient!=null) {
                httpClient.close();
            }

        }
    }
}
