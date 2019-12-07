package com.zot.autorun.moudules.apitest.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.testng.TestNG;
import org.testng.xml.*;

import java.util.ArrayList;
import java.util.List;


@Api(description = "用户接口")
@org.springframework.web.bind.annotation.RestController
@RequestMapping("/demo")
public class RestController {

    @ApiOperation(value = "新增库存" ,  notes="造库存接口")
    @RequestMapping(value="/createInv",method=RequestMethod.GET)
    public String createInv(){

        // TODO: 2019/5/30 了解testng 执行用例的结构方法
        // TODO: 2019/5/30 对指定接口传入指定参数（实体）,运行测试用例返回运行结果
        // TODO: 2019/5/30 测试用例异步运行，运行结束后查看结果 （多线程异步执行已实现）
        // TODO: 2019/5/30 随机组合需要执行的测试用例 （只要组合的用例发送过去，就能启一个线程后台异步执行，已实现）
        // TODO: 2019/5/30 集成allure 测试报告
		// TODO: 2019/6/12 关联参数传递实现 （已实现）
		// TODO: 2019/7/24 关联参数用${} 取值实现 (已实现)
		// TODO: 2019/7/24 加个判断实现请求json 中 带有 "${...}" 的参数才去替换，否则不替换 (已实现)
		// TODO: 2019/7/25 参数化已实现  (已实现)

        //方法2,多个testcase
    	XmlSuite suite = new XmlSuite();
    	suite.setName("Suite");
    	XmlTest test = new XmlTest(suite);
    	test.setName("Test");

    	List<XmlClass> classes = new ArrayList<XmlClass>();
    	classes.add(new XmlClass("com.rest.CreateInventoryData.CreateInventoryTest"));
    	test.setXmlClasses(classes) ;

    	List<XmlSuite> suites = new ArrayList<XmlSuite>();
    	suites.add(suite);
    	TestNG tng = new TestNG();
    	tng.setXmlSuites(suites);
    	tng.run();

        return "调用成功";
    }
}
