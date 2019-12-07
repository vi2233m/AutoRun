package com.zot.autorun.moudules.apitest.dto.cases;

import java.util.Map;

public class ApiInfoForCase {
    /**
     * 一条用例里面所包含的接口编号
     */
    private int apiNo;
    private Map<String, String> preParams;
    private Map<String, String> postParams;

    @Override
    public String toString() {
        return "ApiInfoForCase{" +
                "apiNo=" + apiNo +
                ", preParams=" + preParams +
                ", postParams=" + postParams +
                '}';
    }

    public int getApiNo() {
        return apiNo;
    }

    public void setApiNo(int apiNo) {
        this.apiNo = apiNo;
    }

    public Map<String, String> getPreParams() {
        return preParams;
    }

    public void setPreParams(Map<String, String> preParams) {
        this.preParams = preParams;
    }

    public Map<String, String> getPostParams() {
        return postParams;
    }

    public void setPostParams(Map<String, String> postParams) {
        this.postParams = postParams;
    }
}
