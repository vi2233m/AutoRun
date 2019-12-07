package com.zot.autorun.moudules.apitest.service;

import com.zot.autorun.moudules.apitest.dto.RequestApiBody;

import java.util.Map;

public interface ApiDebug {

//    ApiDebugImpl(Map<String, String> params);
    /**
     * api请求模板
     * @param requestApiBody  请求实体
     * @return
     */
    String responseJson(RequestApiBody requestApiBody);
}
