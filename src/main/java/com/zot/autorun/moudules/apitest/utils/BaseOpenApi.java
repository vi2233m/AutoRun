package com.zot.autorun.moudules.apitest.utils;

//import com.rest.CreateInventoryData.CreateInventoryTest;
//import com.rest.helper.util.WarehouseChoose;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.log4j.Logger;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


public class BaseOpenApi {
	
////	public void baseInit(String warehouse) {
////
////		WarehouseChoose wc = new WarehouseChoose(warehouse);
////		ExcelUtil er = new ExcelUtil("","InventoryData");
////	}
//
//	public WarehouseChoose getWarehouse(String warehouse) {
//		return new WarehouseChoose(warehouse);
//	}
//
//	public ExcelUtil getExcelUtil() {
//		return new ExcelUtil("","InventoryData");
//	}
//
//	public String responseJson(String requestJson) {
//		Logger log = Logger.getLogger(CreateInventoryTest.class);
//		RestAssured.baseURI =PropertiesParamRead.getOpenapi_url();
//		  Response r = given().contentType("application/json;charset=UTF-8").body(requestJson).when().post();
//		  log.info("openapi url :"+PropertiesParamRead.getOpenapi_url());
//		  log.info("*******************************request**********************************");
//		  log.info(requestJson);  //request
//		  log.info("*******************************response*********************************");
//		  log.info(r.asString()); //responseBody
//		  return r.then().body("code", equalTo("0")).extract().response().asString();
//	}
}
