package com.neroyang.anomalydetection.prometheus.client;

import com.google.gson.Gson;
import com.neroyang.anomalydetection.prometheus.api.PrometheusHttpApi;
import com.neroyang.anomalydetection.prometheus.exception.ParametersIncorrectException;
import com.neroyang.anomalydetection.prometheus.exception.ServiceUnavailableException;
import com.neroyang.anomalydetection.prometheus.exception.UnprocessableEntityException;
import com.neroyang.anomalydetection.prometheus.response.HttpResponse;
import com.neroyang.anomalydetection.prometheus.response.QueryData;
import com.neroyang.anomalydetection.utils.spring.PropertyPlaceholder;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.management.Query;
import javax.xml.ws.Response;
import java.io.IOException;
import java.net.URISyntaxException;

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

    public HttpClient() {

        //Load Config
        final String PrometheusHost = PropertyPlaceholder.getProperty("prometheus.host").toString();
        final String PrometheusPort = PropertyPlaceholder.getProperty("prometheus.port").toString();
        final String PrometheusNameSpace = PropertyPlaceholder.getProperty("prometheus.namespace").toString();

        this.prometheusHttpApi = new PrometheusHttpApi(PrometheusHost,PrometheusPort,PrometheusNameSpace);
    }


    /**
     * query=<string>: Prometheus expression query string.
     * time=<rfc3339 | unix_timestamp>: Evaluation timestamp. Optional.
     * timeout=<duration>: Evaluation timeout. Optional. Defaults to and is capped by the value of the -query.timeout flag.
     * @return
     * @throws URISyntaxException
     */
    HttpResponse<QueryData> instantQueries(String query, String time, Integer timeout) throws URISyntaxException, ParametersIncorrectException, UnprocessableEntityException, ServiceUnavailableException, IOException {
        URIBuilder uriBuilder = new URIBuilder(prometheusHttpApi.instantQueries());
        uriBuilder.addParameter("query",query);
        uriBuilder.addParameter("time",time);

        HttpResponse httpResponse = doHttpRequest(HttpResponse.class,uriBuilder);
        return httpResponse;
    }

    private <T> T doHttpRequest(Class<T> data, URIBuilder uriBuilder) throws IOException, URISyntaxException, ParametersIncorrectException, UnprocessableEntityException, ServiceUnavailableException {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        try {
            httpClient = HttpClients.createDefault();

            HttpGet get = new HttpGet(uriBuilder.build());
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
            System.out.println(statusCode);

            HttpEntity entity = response.getEntity();

            String string = EntityUtils.toString(entity, "utf-8");

            Gson gson = new Gson();
            T result = gson.fromJson(string,data);
            return result;
        }finally {
            response.close();
            httpClient.close();
        }
    }
}
