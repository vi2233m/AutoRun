package com.zot.autorun.moudules.apitest.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zot.autorun.moudules.apitest.dto.RequestApiBody;
import com.zot.autorun.moudules.apitest.service.ApiDebug;
import com.zot.autorun.moudules.apitest.utils.JsonUtil;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import java.util.Map;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.*;

@Service
public class ApiDebugImpl implements ApiDebug {

    private Logger log = Logger.getLogger(ApiDebugImpl.class);
    private Map<String, String> params;

    public ApiDebugImpl(){
    }
    public ApiDebugImpl(Map<String, String> params){
        this.params = params;
    }

    /**
     * api请求模板
     * @param requestApiBody  请求实体
     * @return
     */
    @Override
    public String responseJson(RequestApiBody requestApiBody) {
        RestAssured.baseURI =requestApiBody.getUrl();
        // 添加请求头信息
        RequestSpecification addHeaders = given();
        if( requestApiBody.getHeaders() != null && !"".equals(requestApiBody.getHeaders())) {
            int i = 0;
            Map<String, Object> headers = (Map) requestApiBody.getHeaders();
            for(String key : headers.keySet()){
                addHeaders = addHeaders.header(key, headers.get(key));
                i++ ;
                if (i != headers.size() - 1){
                    addHeaders.and();
                }
            }
        }
        // 第一次组装,requestJson 和 requestData 合并
        String myjson = JsonUtil.changeJsonObj((Map) requestApiBody.getRequestJson(), (Map) requestApiBody.getRequestData()).toJSONString();
        // 第二次组装,如果前置处理器中有参数传递进来,则合并成,组装成最终发送的json报文
        if (this.params != null && this.params.size() != 0){
            myjson = JsonUtil.changeJsonObj((Map) JSONObject.parseObject(myjson), params).toJSONString();
        }

        log.info("**************************************************************----(Request Log)-----*****************************************************************");
        Response r = addHeaders.body(myjson).log().all().when().post();

        log.info("**************************************************************----(Response Log)----*****************************************************************");
        Response response = r.then().log().all().extract().response();

        // TODO: 2019/5/29 实现断言等于、包含、不包含
        // (已实现) 断言,实现请求header 和 body 的字段值的比较（等于、包含、不包含）
        if(requestApiBody.getAsserts() != null && !"".equals(requestApiBody.getAsserts())){
            JSONObject asserts = JSONObject.parseObject(JSON.toJSONString(requestApiBody.getAsserts()));
            for (String key : asserts.keySet()){
                JSONArray assertBody = asserts.getJSONArray(key);

                for(int i = 0; i < assertBody.size(); i++){
                    String path = ((JSONObject)assertBody.get(i)) .get("path").toString();
                    String relation = ((JSONObject)assertBody.get(i)) .get("relation").toString();
                    String value = ((JSONObject)assertBody.get(i)) .get("value").toString();
                    log.info("path: " + path + " relation: " + relation + " value: " +value);

                    if( key.equals("headers")){
                        if (relation.equals("isEqualTo")){
                            assertThat(response.getHeader(path)).isEqualTo(value);

                        }else if (relation.equals("contains")){
                            assertThat(response.getHeader(path)).contains(value);

                        }else if (relation.equals("doesNotContain")){
                            assertThat(response.getHeader(path)).doesNotContain(value);

                        }else {
                            log.error(key +"暂时不支持此断言方式！", new Throwable("暂时不支持此断言方式"));
                        }
                    }else {
                        if (relation.equals("isEqualTo")) {
                            assertThat(response.jsonPath().getString(path)).isEqualTo(value);

                        } else if (relation.equals("contains")) {
                            assertThat(response.jsonPath().getString(path)).contains(value);

                        } else if (relation.equals("doesNotContain")) {
                            assertThat(response.jsonPath().getString(path)).doesNotContain(value);

                        } else {
                            log.error(key + "暂时不支持此断言方式！", new Throwable("暂时不支持此断言方式"));
                        }
                    }
                }
            }

        }

        return response.asString();
    }
}

/**  断言数据结构
 {
     "body":[
         {
             "path":"data.code",
             "assert":"isEqualTo",
             "value":""
         },
         {
             "path":"",
             "assert":"contains",
             "value":""
         },
         {
             "path":"",
             "assert":"doesNotContain",
             "value":""
         }
         ],
         "headers":[
         {
             "path":"",
             "assert":"",
             "value":""
         },
         {
             "path":"",
             "assert":"",
             "value":""
         }
     ]
 }
 */