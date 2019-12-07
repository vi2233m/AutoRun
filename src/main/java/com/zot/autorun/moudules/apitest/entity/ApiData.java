package com.zot.autorun.moudules.apitest.entity;

public class ApiData {

    private int id;
    private String url;
    private String headers;
    private String interfaceName;
    private String requestJson;
    private String requestData;
    private String asserts;

    @Override
    public String toString() {
        return "ApiData{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", headers='" + headers + '\'' +
                ", interfaceName='" + interfaceName + '\'' +
                ", requestJson='" + requestJson + '\'' +
                ", requestData='" + requestData + '\'' +
                ", Asserts='" + asserts + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHeaders() {
        return headers;
    }

    public void setHeaders(String headers) {
        this.headers = headers;
    }

    public String getAsserts() {
        return asserts;
    }

    public void setAsserts(String asserts) {
        this.asserts = asserts;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public String getRequestJson() {
        return requestJson;
    }

    public void setRequestJson(String requestJson) {
        this.requestJson = requestJson;
    }

    public String getRequestData() {
        return requestData;
    }

    public void setRequestData(String requestData) {
        this.requestData = requestData;
    }
}
