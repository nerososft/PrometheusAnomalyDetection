package com.pingcap.anomalydetection.web.core;

import org.springframework.context.ApplicationContext;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Author neroyang
 * Email  nerosoft@outlook.com
 * Date   2017/8/25
 * Time   下午12:41
 */
public class ApiStore {

        private ApplicationContext applicationContext;


        private static HashMap<String,ApiRunnable> apiRunnableHashMap = new HashMap<String, ApiRunnable>();
        final ParameterNameDiscoverer parameterNameDiscoverer;


    public ApiStore(ApplicationContext applicationContext,ParameterNameDiscoverer parameterNameDiscoverer) {
        Assert.notNull(applicationContext);
        this.applicationContext = applicationContext;
        this.parameterNameDiscoverer = parameterNameDiscoverer;
    }

    public void loadApiFromSpringBeans(){
        String[] names = applicationContext.getBeanDefinitionNames();
        Class<?> type;

        for(String name:names){
            type = applicationContext.getType(name);
            for(Method method:type.getDeclaredMethods()){
                ApiMapping apiMapping = method.getAnnotation(ApiMapping.class);
                if(apiMapping!=null){
                    addApiItem(apiMapping,name,method);
                }
            }
        }
    }

    public ApiRunnable findApiRunnable(String apiName){
        if(apiName==null){
            throw new IllegalArgumentException("apiName不能为空");
        }
        return apiRunnableHashMap.get(apiName);
    }

    private void addApiItem(ApiMapping apiMapping, String name, Method method) {

        for(Field f:method.getReturnType().getFields()){
            if(f.getType().equals(Object.class)){
                throw new RuntimeException("接口模型不符合规范，请改正："+method.getName());
            }
        }

        ApiRunnable apiRunnable = new ApiRunnable();
        apiRunnable.apiName = apiMapping.value();
        apiRunnable.targetMethod = method;
        apiRunnable.returnType = method.getReturnType();
        apiRunnable.parameters = method.getParameters();
        apiRunnable.paramsName = Arrays.asList(parameterNameDiscoverer.getParameterNames(method));
        apiRunnable.annotations = method.getDeclaredAnnotations();
        apiRunnable.paramType = method.getParameterTypes();
        apiRunnable.targetName = name;
        apiRunnableHashMap.put(apiMapping.value(),apiRunnable);
    }

    public ApiRunnable findApiRunnable(String apiName,String Version){
        if(apiName==null){
            throw new IllegalArgumentException("apiName不能为空");
        }
        return (ApiRunnable) apiRunnableHashMap.get(apiName+"_"+Version);
    }

    public static List<ApiRunnable> findApiRunnables(){

        List<ApiRunnable> list = new ArrayList<ApiRunnable>(20);
        for(ApiRunnable apiRunnable:apiRunnableHashMap.values()){
            list.add(apiRunnable);
        }
        return list;
    }



    public class ApiRunnable implements Serializable{


        String apiName;
        String targetName;

        Object target;
        Method targetMethod;
        Class<?> returnType;
        Class<?>[] paramType;
        Parameter[] parameters;
        List<String> paramsName;
        Annotation[] annotations;


        public Class<?> getReturnType() {
            return returnType;
        }

        public void setReturnType(Class<?> returnType) {
            this.returnType = returnType;
        }

        public Class<?>[] getParamType() {
            return paramType;
        }

        public void setParamType(Class<?>[] paramType) {
            this.paramType = paramType;
        }

        public Parameter[] getParameters() {
            return parameters;
        }

        public void setParameters(Parameter[] parameters) {
            this.parameters = parameters;
        }

        public List<String> getParamsName() {
            return paramsName;
        }

        public void setParamsName(List<String> paramsName) {
            this.paramsName = paramsName;
        }

        public Annotation[] getAnnotations() {
            return annotations;
        }

        public void setAnnotations(Annotation[] annotations) {
            this.annotations = annotations;
        }

        public Object run(Object... args) throws InvocationTargetException, IllegalAccessException, InstantiationException {
            if(target==null){
                //target = Consumer.singleton().getBean(targetName);
                target = applicationContext.getBean(targetName);
            }
            System.out.println(target.toString());
            System.out.println(target.getClass().toString());
            return targetMethod.invoke(target,args);
        }

        public Class<?>[] getParamTypes(){
            return targetMethod.getParameterTypes();
        }

        public String getApiName() {
            return apiName;
        }

        public void setApiName(String apiName) {
            this.apiName = apiName;
        }

        public String getTargetName() {
            return targetName;
        }

        public void setTargetName(String targetName) {
            this.targetName = targetName;
        }

        public Object getTarget() {
            return target;
        }

        public void setTarget(Object target) {
            this.target = target;
        }

        public Method getTargetMethod() {
            return targetMethod;
        }

        public void setTargetMethod(Method targetMethod) {
            this.targetMethod = targetMethod;
        }

        @Override
        public String toString() {
            return "ApiRunnable{" +
                    "apiName='" + apiName + '\'' +
                    ", targetName='" + targetName + '\'' +
                    ", target=" + target +
                    ", targetMethod=" + targetMethod +
                    ", returnType=" + returnType +
                    ", paramType=" + Arrays.toString(paramType) +
                    ", parameters=" + Arrays.toString(parameters) +
                    ", paramsName=" + paramsName +
                    ", annotations=" + Arrays.toString(annotations) +
                    '}';
        }
    }
}

