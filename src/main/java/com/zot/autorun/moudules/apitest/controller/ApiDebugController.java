package com.zot.autorun.moudules.apitest.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zot.autorun.moudules.apitest.dto.RequestApiBody;
import com.zot.autorun.moudules.apitest.dto.ResponseBody;
import com.zot.autorun.moudules.apitest.dto.cases.CasesRequestBody;
import com.zot.autorun.moudules.apitest.service.ApiDebugService;
import com.zot.autorun.moudules.apitest.service.CaseDebugService;
import com.zot.autorun.moudules.apitest.service.impl.CaseDebugServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@io.swagger.annotations.Api(description = "API接口调试")
@org.springframework.web.bind.annotation.RestController
@RequestMapping("/debug")
public class ApiDebugController {

    @Autowired
    private ResponseBody responseBody;
    @Autowired
    private ApiDebugService apiDebugService;
    @Autowired
    private CaseDebugService caseDebugService;
    @Autowired
    private CaseDebugServiceImpl caseDebugServiceImpl;

    @ApiOperation(value = "API接口调试", notes = "接口测试")
    @RequestMapping(value = "/apiDebug", method = RequestMethod.POST)
    public ResponseBody apiDebug(@RequestBody RequestApiBody api){
        System.out.println("请求格式：" +api.toString());

        responseBody.setCode("0");
        responseBody.setMessage("请求成功");
        return responseBody;
    }

    @ApiOperation(value = "RestAPI接口调试", notes = "restful接口调试")
    @RequestMapping(value = "/apiTest", method = RequestMethod.POST)
    public ResponseBody apitest(@RequestBody RequestApiBody requestApiBody){

        return apiDebugService.apiPost(requestApiBody);
    }

    @ApiOperation(value = "测试用例执行", notes = "执行测试用例")
    @RequestMapping(value = "/caseTest", method = RequestMethod.POST)
    public String casetest(@RequestBody CasesRequestBody casesRequestBody){

        JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(casesRequestBody));

        caseDebugService.setJson(jsonObject);
        Thread thread = new Thread( caseDebugServiceImpl);
        // 多线程异步执行用例，前端不需要等待用例执行完成，不需要等待接口返回
        thread.start();

        return "Case is runing! please watting!";
    }
}
