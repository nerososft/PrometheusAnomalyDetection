package com.pingcap.anomalydetection.core.detection;


import com.pingcap.anomalydetection.CONSTANT.DetectionMethod;

import java.util.HashMap;

import static com.pingcap.anomalydetection.CONSTANT.DetectionMethod.*;

/**
 * Author neroyang
 * Email  nerosoft@outlook.com
 * Date   2018/4/16
 * Time   下午11:16
 */
public class AnomalyDetection {

    private HashMap<DetectionMethod,String> methodStringHashMap;





    public AnomalyDetection() {
        methodStringHashMap = new HashMap<DetectionMethod, String>();
        initMethodMap();
    }



    private void initMethodMap(){
        methodStringHashMap.put(NormalDistribution,"normalDistribution");
        methodStringHashMap.put(MonisticNormalDistribution,"monisticNormalDistribution");
        methodStringHashMap.put(MultivariateGaussianDistribution,"multivariateGaussianDistribution");
        methodStringHashMap.put(MahalanobisDistanceDetection,"mahalanobisDistanceDetection");
        methodStringHashMap.put(X2Statistic,"x2Statistic");
        methodStringHashMap.put(MatrixDecomposition,"matrixDecomposition");
        methodStringHashMap.put(ReplicatorNeuralNetworksAnomalyDetections,"replicatorNeuralNetworksAnomalyDetections");
    }

    public boolean staticAnomalyDetection(Object[] objs, Object obj, DetectionMethod detectionMethod, Double factor){
        return true;
    }
}
