package com.zot.autorun.moudules.apitest.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.Map;

public interface CaseDebugService {

    void setJson(JSONObject jsonObject);

    /**
     * 执行用例接口，多线程执行
     * @param jsonObject  controller传进来的用例执行参数
     * @return
     */
    void executTest(JSONObject jsonObject);

    /**
     *  执行用例单个接口
     * @param caseInfo 用例信息
     * @param postParams 在脚本中已经通过jsonpath 关联的参数
     * @return
     */
    String executeCase(JSONArray caseInfo, Map<String, Object> postParams);
}
