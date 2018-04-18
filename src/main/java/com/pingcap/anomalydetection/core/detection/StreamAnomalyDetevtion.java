package com.pingcap.anomalydetection.core.detection;

import com.pingcap.anomalydetection.CONSTANT.DetectionMethod;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Author neroyang
 * Email  nerosoft@outlook.com
 * Date   2018/4/17
 * Time   下午5:44
 */
public class StreamAnomalyDetevtion extends AnomalyDetection {
    private int poolSize;

    DetectionMethod detectionMethod;
    Double factor;
    Integer spacing;
    private Queue<Object> streampool;




    public StreamAnomalyDetevtion(int poolSize, DetectionMethod detectionMethod, Double factor) {
        this.poolSize = poolSize;
        this.detectionMethod = detectionMethod;
        this.factor = factor;
        this.spacing = 0;
        streampool = new LinkedBlockingQueue<Object>();
    }

    public StreamAnomalyDetevtion(int poolSize, DetectionMethod detectionMethod, Double factor, Integer spacing) {
        this.poolSize = poolSize;
        this.detectionMethod = detectionMethod;
        this.factor = factor;
        this.spacing = spacing;

    }

    public void initStreamPool(Queue<Object> streampool){
        this.setStreampool(streampool);
    }


    public boolean streamAnomalyDetection(Object obj){
        return true;
    }

    public boolean spacingStreamAnomalyDetection(Object obj){
        return true;
    }


    public int getPoolSize() {
        return poolSize;
    }

    public void setPoolSize(int poolSize) {
        this.poolSize = poolSize;
    }

    public DetectionMethod getDetectionMethod() {
        return detectionMethod;
    }

    public void setDetectionMethod(DetectionMethod detectionMethod) {
        this.detectionMethod = detectionMethod;
    }

    public Double getFactor() {
        return factor;
    }

    public void setFactor(Double factor) {
        this.factor = factor;
    }

    public Integer getSpacing() {
        return spacing;
    }

    public void setSpacing(Integer spacing) {
        this.spacing = spacing;
    }

    public Queue<Object> getStreampool() {
        return streampool;
    }

    public void setStreampool(Queue<Object> streampool) {
        this.streampool = streampool;
    }
}
