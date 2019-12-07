package com.zot.autorun.moudules.apitest.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zot.autorun.moudules.apitest.dto.RequestApiBody;
import com.zot.autorun.moudules.apitest.dto.ResponseBody;
import com.zot.autorun.moudules.apitest.service.ApiDebugService;
import com.zot.autorun.moudules.apitest.service.CaseDebugService;
import com.zot.autorun.moudules.apitest.utils.ApiTools;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springfox.documentation.spring.web.json.Json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.restassured.path.json.JsonPath.with;

@Service
public class CaseDebugServiceImpl implements CaseDebugService, Runnable{

    Logger log = Logger.getLogger(ApiDebugImpl.class);
    public JSONObject jsonObject;

    @Autowired
    ApiTools apiTools;
    @Autowired
    RequestApiBody requestApiBody;
    @Autowired
    ApiDebugService apiDebugService;

    // 多线程传仓方式，要用方法传参，不能用构造函数传参，否则服务启动的时候会报错，找不到参数
    @Override
    public void setJson(JSONObject jsonObject){
        this.jsonObject = jsonObject;
    }

    @Override
    public void run() {
        executTest(this.jsonObject);
    }

    /**
     * 执行用例接口，多线程执行
     * @param jsonObject  controller传进来的用例执行参数
     * @return
     */
    @Override
    public void executTest(JSONObject jsonObject){

        JSONArray cases = jsonObject.getJSONArray("cases");
        // params 保存关联参数
        Map<String, Object> params = new HashMap<>();
        // 遍历所有的测试用例数
        for (int i = 0; i < cases.size(); i ++){
            log.info("case.size() = " + cases.size() +" ; "+ "case " + (i+1) + " : "+ (cases.getJSONObject(i)));
            JSONArray caseInfo = cases.getJSONObject(i).getJSONArray("caseInfo");
//            JSONObject caseInfo = cases.getJSONObject(i).getJSONObject("caseInfo");
            // 执行用例
            String responseBody = executeCase(caseInfo, params);

            Map<String, String> postParams = (Map) caseInfo.getJSONObject(0).getJSONObject("postParams");
            // 获取关联参数
            if(postParams != null && postParams.size() > 0){
                for (String key : postParams.keySet()){
                    // 前端传来的postParams参数，其中key 为jsonpath， value为参数名称
                    try {
                        Object value = with(responseBody).get(key);//通过前端传入的jsonpath 获取前一个请求返回的指定参数值
                        String paramKey = postParams.get(key); // paramKey 为前端传入的关联参数名称，后面通过此关联参数名称获取到关联的值
                        params.put(paramKey, value);
                    }catch (Exception e){
                        log.error("关联参数不存在,请检查jsonpath对应的返回参数！", e);
                    }
                }
//                for (String para : params.keySet()){
//                    System.out.println("=======" + para + ": " + params.get(para));
//                }
            }
        }
    }


    /**
     *  执行用例单个接口
     * @param caseInfo 用例信息
     * @param postParams 在脚本中已经通过jsonpath 关联的参数
     * @return
     */
    @Override
    public String executeCase(JSONArray caseInfo, Map<String, Object> postParams){

        int caseNo = caseInfo.getJSONObject(0).getInteger("apiNo");

        // 获取请求信息，set 请求数据
        requestApiBody.setUrl(apiTools.getUrl(caseNo));
        requestApiBody.setHeaders(JSONObject.parseObject(apiTools.getRequestHeaders(caseNo)));
        requestApiBody.setRequestJson(JSONObject.parseObject(apiTools.getRequestJson(caseNo)));
        requestApiBody.setRequestData(apiTools.getRequestData(caseNo));
        requestApiBody.setAsserts(apiTools.getAsserts(caseNo));

        // TODO: 2019/7/24 加个判断实现请求json 中 带有 "${...}" 的参数才去替换，否则不替换
        //暂时还没有做关联请求头
        // String requestheaders = requestApiBody.getHeaders().toString();
        // 关联参数处理
        String requestJson = requestApiBody.getRequestJson().toString();
        // 提取出请求json中 带 ${...} 的参列表
        List<String> prams = getMatcher(requestJson, "\\$\\{(.+?)\\}");
        // 存放脚本中需要替换的参数（即带 ${...} 的参数）
        Map<String, Object> paragrams = new HashMap<>();

        // TODO: 2019/6/14 前置参数 preParams 如何传进来
        // 前端如果有前置参数传进来，则替换（已完成）
        Map<String, String> preParams =(Map) caseInfo.getJSONObject(0).getJSONObject("preParams");

        for (String pram : prams) {
            if (postParams != null && postParams.size() > 0){
                // 如果请求json 中不包含 ${...} 这种的参数就不需要去替换( 目前保存的key的名称 只能是与请求json里面的一致才行，因为替换参数值统一在最底层)
                if (postParams.containsKey( pram )){
                    paragrams.put(pram, postParams.get(pram));
                    log.info("关联参数： " +  "key: " + pram + ", value: " + paragrams.get(pram));
                }
            }
            // 参数化数据覆盖关联数据（参数化参数优先级比关联参数优先级高！）
            if (preParams != null && preParams.size() > 0){
                if ( preParams.containsKey(pram)){
                    paragrams.put(pram, preParams.get(pram));
                    log.info("参数化参数： "  +  "key: " + pram + ", value: " + paragrams.get(pram));
                }
            }
        }
        requestApiBody.setPreParams(paragrams);
        // 发送请求
        ResponseBody responseBody = apiDebugService.apiPost(requestApiBody);

        return responseBody.getBody().toString();
    }

    /**
     *  jsonPath 获取指定的返回值
     * @param responseJsonStr
     * @param path
     * @return
     */
    public static Object getValuesFromJsonPath(String responseJsonStr, String path) {
        if (getType(with(responseJsonStr).get(path)).equals("ArrayList")){
            // 如果通过jsonpath 取出的是一个列表，则默认只返回第一个值
            List values = with(responseJsonStr).get(path);
            Object value = values.get(0);
            return value;
        }else if (getType(with(responseJsonStr).get(path)).equals("String")){
            String value1 = with(responseJsonStr).get(path);
            return value1;
        }else {
            return null;
        }
    }

    /**
     *  正则匹配获取参数化的 参数名称
     * @param requestJson   json串
     * @param matcherStr    匹配串
     * @return
     */
    public List<String> getMatcher(String requestJson, String matcherStr){
//        String matcherStr = "\\$\\{(.+?)\\}";
        Pattern pattern = Pattern.compile(matcherStr);
        Matcher matcher = pattern.matcher(requestJson);

        List<String> postParams = new ArrayList();
        while (matcher.find()){
            postParams.add(matcher.group(1));
        }

        return postParams;
    }

    /**
     * 获取对象类型
     * @param object
     * @return
     */
    public static String getType(Object object){
        String typeName = object.getClass().getTypeName();
        int length = typeName.lastIndexOf(".");
        String type = typeName.substring(length + 1);
        return type;
    }
}
