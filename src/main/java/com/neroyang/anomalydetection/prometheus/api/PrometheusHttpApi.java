package com.neroyang.anomalydetection.prometheus.api;

/**
 * Author neroyang
 * Email  nerosoft@outlook.com
 * Date   2018/4/18
 * Time   下午11:43
 */
public class PrometheusHttpApi {

    private String  host = "localhost";
    private String  port = "9090";
    private String  nameSpace = "/api/v1/";

    public String instantQueries(){
        return host+":"+port+nameSpace+"query";
    }

    public String rangeQueries(){
        return host+":"+port+nameSpace+"query_range";
    }

    public String findSeriesByLabelMatchers(){
        return host+":"+port+nameSpace+"series";
    }

    public String queryingLabelValues(String labelName){
        return host+":"+port+nameSpace+"label/"+labelName+"/values";
    }

    public String targets(){
        return host+":"+port+nameSpace+"targets";
    }

    public String alertmanagers(){
        return host+":"+port+nameSpace+"alertmanagers";
    }

    public String config(){
        return host+":"+port+nameSpace+"status/config";
    }

    public String flags(){
        return host+":"+port+nameSpace+"status/flags";
    }


    public PrometheusHttpApi() {
    }

    public PrometheusHttpApi(String host, String port) {
        this.host = host;
        this.port = port;
    }

    public PrometheusHttpApi(String host, String port, String nameSpace) {
        this.host = host;
        this.port = port;
        this.nameSpace = nameSpace;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getNameSpace() {
        return nameSpace;
    }

    public void setNameSpace(String nameSpace) {
        this.nameSpace = nameSpace;
    }

    @Override
    public String toString() {
        return "PrometheusHttpApi{" +
                "host='" + host + '\'' +
                ", port='" + port +'\''+
                ", nameSpace='" + nameSpace + '\'' +
                '}';
    }
}
