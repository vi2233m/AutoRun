package com.zot.autorun.moudules.apitest.dto;

import com.zot.autorun.moudules.apitest.dto.asserts.Asserts;
import org.springframework.stereotype.Component;

@Component
public class RequestApiBody {

    private String url;
    private Object preParams; // 前置处理器需传入的参数
    private Object jsonPath; //后置处理器的jsonPath路径
    private Object headers;
    private Object requestJson;
    private Object requestData;
    private Asserts asserts;

    @Override
    public String toString() {
        return "RequestApiBody{" +
                "url='" + url + '\'' +
                ", preParams=" + preParams +
                ", jsonPath=" + jsonPath +
                ", headers=" + headers +
                ", requestJson=" + requestJson +
                ", requestData=" + requestData +
                ", asserts=" + asserts +
                '}';
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Object getPreParams() {
        return preParams;
    }

    public void setPreParams(Object preParams) {
        this.preParams = preParams;
    }

    public Object getJsonPath() {
        return jsonPath;
    }

    public void setJsonPath(Object jsonPath) {
        this.jsonPath = jsonPath;
    }

    public Object getHeaders() {
        return headers;
    }

    public void setHeaders(Object headers) {
        this.headers = headers;
    }

    public Object getRequestJson() {
        return requestJson;
    }

    public void setRequestJson(Object requestJson) {
        this.requestJson = requestJson;
    }

    public Object getRequestData() {
        return requestData;
    }

    public void setRequestData(Object requestData) {
        this.requestData = requestData;
    }

    public Asserts getAsserts() {
        return asserts;
    }

    public void setAsserts(Asserts asserts) {
        this.asserts = asserts;
    }
}
