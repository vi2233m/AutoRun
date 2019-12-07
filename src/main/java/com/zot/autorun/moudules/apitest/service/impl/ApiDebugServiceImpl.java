package com.zot.autorun.moudules.apitest.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zot.autorun.moudules.apitest.dto.RequestApiBody;
import com.zot.autorun.moudules.apitest.dto.ResponseBody;
import com.zot.autorun.moudules.apitest.service.ApiDebug;
import com.zot.autorun.moudules.apitest.service.ApiDebugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ApiDebugServiceImpl implements ApiDebugService {

    @Autowired
    private ApiDebug apiDebug;
    @Autowired
    private ResponseBody responseBody;

    @Override
    public ResponseBody apiPost(RequestApiBody requestApiBody){

        String url = requestApiBody.getUrl();
        Map<String, String > params = (Map) requestApiBody.getPreParams();
        Map<String, String> requestHeaders = (Map) requestApiBody.getHeaders();
        Map json = ((Map) requestApiBody.getRequestJson());
        Map data = (Map) requestApiBody.getRequestData();
        JSONObject asserts = JSONObject.parseObject(JSON.toJSONString(requestApiBody.getAsserts()));

        // 如果前端有传params参数，就替换
        if (params == null || params.size() == 0){
            apiDebug = new ApiDebugImpl();

        }else {
            apiDebug = new ApiDebugImpl(params);
        }

        if( url == null || "".equals(url) || url.toLowerCase().equals("string")){
            responseBody.setCode("E00006");
            responseBody.setMessage("请求URL不能为空！");
            return responseBody;
        }else if (json.size() == 0 || "".equals(json)){
            responseBody.setCode("E00007");
            responseBody.setMessage("请求body不能为空！");
            return responseBody;
        }else {
            responseBody.setCode("0");
            responseBody.setMessage("请求成功");
            responseBody.setBody(apiDebug.responseJson(requestApiBody));
            return responseBody;
        }
    }
}
