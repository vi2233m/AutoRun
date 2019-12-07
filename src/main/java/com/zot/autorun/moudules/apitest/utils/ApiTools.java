package com.zot.autorun.moudules.apitest.utils;

import com.alibaba.fastjson.JSONObject;
import com.zot.autorun.moudules.apitest.dao.ApiMapper;
import com.zot.autorun.moudules.apitest.dto.asserts.Asserts;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ApiTools {

    @Autowired(required = false)
    private ApiMapper apiMapper;


    /**
     *  获取请求URL
     * @param apiName
     * @return
     */
    public String getUrl(String apiName){
        return apiMapper.selectApiByName(apiName).getUrl();
    }

    /**
     *  获取请求头
     * @param apiName
     * @return
     */
    public String getRequestHeaders(String apiName){
        return apiMapper.selectApiByName(apiName).getHeaders();
    }

    /**
     *  获取请求原始 JSON
     * @param apiName
     * @return
     */
    public String getRequestJson(String apiName){
        return apiMapper.selectApiByName(apiName).getRequestJson();
    }

    /**
     *  获取请求 DATA
     * @param apiName
     * @return
     */
    public Map<String, String> getRequestData(String apiName){

        String dataJson = apiMapper.selectApiByName(apiName).getRequestData();

        if(dataJson != null && !"".equals(dataJson)){
            return JsonUtil.jsonToMap(dataJson);
        }else {
            return new HashMap<>() ;
        }
    }

    /**
     * 获取断言字段
     * @param apiName
     * @return
     */
    public Asserts getAsserts(String apiName){
        String asserts = apiMapper.selectApiByName(apiName).getAsserts();

        JSONObject object = JSONObject.parseObject(asserts);
        Asserts asserts1 = new Asserts();
        asserts1.setHeaders( (List) object.getJSONArray("headers"));
        asserts1.setBody((List) object.getJSONArray("body"));
        return asserts1;
    }

    /**
     *  获取请求URL
     * @param id
     * @return
     */
    public String getUrl(int id){
        return apiMapper.selectApiById(id).getUrl();
    }

    /**
     *  获取请求头
     * @param id
     * @return
     */
    public String getRequestHeaders(int id){
        return apiMapper.selectApiById(id).getHeaders();
    }

    /**
     *  获取请求原始 JSON
     * @param id
     * @return
     */
    public String getRequestJson(int id){
        return apiMapper.selectApiById(id).getRequestJson();
    }

    /**
     *  获取请求 DATA
     * @param id
     * @return
     */
    public Map<String, String> getRequestData(int id){
        String dataJson = apiMapper.selectApiById(id).getRequestData();

        if(dataJson != null && !"".equals(dataJson)){
            return JsonUtil.jsonToMap(dataJson);
        }else {
            return new HashMap<>() ;
        }
    }

    /**
     * 获取断言字段
     * @param id
     * @return
     */
    public Asserts getAsserts(int id){
        String asserts = apiMapper.selectApiById(id).getAsserts();

        JSONObject object = JSONObject.parseObject(asserts);
        Asserts asserts1 = new Asserts();
        if (asserts != null && "".equals(asserts)) {
            asserts1.setHeaders((List) object.getJSONArray("headers"));
            asserts1.setBody((List) object.getJSONArray("body"));
        }
        return asserts1;
    }

}
