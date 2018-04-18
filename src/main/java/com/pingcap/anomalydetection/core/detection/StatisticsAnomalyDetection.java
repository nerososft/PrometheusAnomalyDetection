package com.pingcap.anomalydetection.core.detection;

import com.pingcap.anomalydetection.core.math.DataStatisticsUtils;

/**
 * Author neroyang
 * Email  nerosoft@outlook.com
 * Date   2018/4/16
 * Time   下午11:19
 */
public class StatisticsAnomalyDetection {

    /**
     * 基于正态分布的一元离群点检测方法
     *
     * 假设有 n 个点 (x_{1},...,x_{n})，那么可以计算出这 n 个点的均值 \mu 和方差\sigma。均值和方差分别被定义为：
     * \mu=\sum_{i=1}^{n}x_{i}/n,
     * \sigma^{2}=\sum_{i=1}^{n}(x_{i}-\mu)^{2}/n.
     * 在正态分布的假设下，区域 \mu\pm 3\sigma 包含了99.7% 的数据，如果某个值距离分布的均值 \mu 超过了 3\sigma，那么这个值就可以被简单的标记为一个异常点（outlier）。
     */
    public static Boolean normalDistribution(double[] objs, double obj){

        double avg = DataStatisticsUtils.getMean(objs);
        double variance = DataStatisticsUtils.getVariance(objs);

        return true;
    }



    /**
     * 基于一元正态分布的离群点检测方法
     *
     * 假设 n 维的数据集合形如 \vec{x}_{i}=(x_{i,1},...,x_{i,n}), i\in \{1,...,m\}，
     * 那么可以计算每个维度的均值和方差 \mu_{j},\sigma_{j}, j\in\{1,...,n\}.
     * 具体来说，对于 j\in \{1,...,n\}，可以计算
     * \mu_{j}=\sum_{i=1}^{m}x_{i,j}/m
     * \sigma_{j}^{2}=\sum_{i=1}^{m}(x_{i,j}-\mu_{j})^{2}/m
     * 在正态分布的假设下，如果有一个新的数据 \vec{x}，可以计算概率 p(\vec{x})如下：
     * p(\vec{x})=\prod_{j=1}^{n} p(x_{j};\mu_{j},\sigma_{j}^{2})=\prod_{j=1}^{n}\frac{1}{\sqrt{2\pi}\sigma_{j}}\exp(-\frac{(x_{j}-\mu_{j})^{2}}{2\sigma_{j}^{2}})
     * 根据概率值的大小就可以判断 x 是否属于异常值.
     */

    public static Boolean monisticNormalDistribution(){
        return true;
    }


    /**
     * 多元高斯分布的异常点检测
     *
     * 假设 n 维的数据集合 \vec{x}=(x_{1},...,x_{n}), ，可以计算 n 维的均值向量
     * \vec{\mu}=(E(x_{1}),...,E(x_{n}))
     * 和 n\times n 的协方差矩阵：
     * \Sigma=[Cov(x_{i},x_{j})], i,j \in \{1,...,n\}
     * 如果有一个新的数据 \vec{x}，可以计算
     * p(\vec{x})=\frac{1}{(2\pi)^{\frac{n}{2}}|\Sigma|^{\frac{1}{2}}} \exp(-\frac{1}{2}(\vec{x}-\vec{\mu})^{T}\Sigma^{-1}(\vec{x}-\vec{\mu}))
     * 根据概率值的大小就可以判断 \vec{x} 是否属于异常值。
     */
    public static Boolean multivariateGaussianDistribution(){
        return true;
    }


    /**
     * 使用 Mahalanobis 距离检测多元离群点
     *
     * 对于一个多维的数据集合 D，假设 \overline{a} 是均值向量，那么对于数据集 D 中的其他对象 a，从 a 到 \overline{a} 的 Mahalanobis 距离是
     * MDist(a,\overline{a})=\sqrt{(a-\overline{a})^{T}S^{-1}(a-\overline{a})},
     * 其中 S 是协方差矩阵。
     * 在这里，MDist(a,\overline{a}) 是数值，可以对这个数值进行排序，如果数值过大，那么就可以认为点 a 是离群点。
     * 或者对一元实数集合 \{MDist(a,\overline{a})|a\in D\} 进行离群点检测，如果 MDist(a,\overline{a}) 被检测为异常点.
     * 那么就认为 a 在多维的数据集合 D 中就是离群点。
     */

    public static Boolean mahalanobisDistanceDetection(){
        return true;
    }

    /**
     * 使用 \chi^{2} 统计量检测多元离群点
     *
     * 在正态分布的假设下，\chi^{2} 统计量可以用来检测多元离群点。对于某个对象 \bold{a}，\chi^{2} 统计量是
     * \chi^{2}=\sum_{i=1}^{n}(a_{i}-E_{i})^{2}/E_{i}.
     * 其中，a_{i} 是 \bold{a} 在第 i 维上的取值，E_{i} 是所有对象在第 i 维的均值，n 是维度。
     * 如果对象 \bold{a} 的 \chi^{2} 统计量很大，那么该对象就可以认为是离群点。
     */
    public static Boolean x2Statistic(){
        return true;
    }


}
