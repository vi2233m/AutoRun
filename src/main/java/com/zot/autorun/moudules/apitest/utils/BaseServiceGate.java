package com.zot.autorun.moudules.apitest.utils;

//import com.rest.CreateInventoryData.CreateInventoryTest;
//import com.rest.helper.util.WarehouseChoose;
//import com.rest.sign.SignUtil13;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.log4j.Logger;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class BaseServiceGate {
	
	
//	public WarehouseChoose getWarehouse(String warehouse) {
//		return new WarehouseChoose(warehouse);
//	}
//
//	public ExcelUtil getExcelUtil() {
//		return new ExcelUtil("","InventoryData");
//	}
//
//	public String responseJson(String requestJson, String warehouse) {
//
//		Logger log = Logger.getLogger(CreateInventoryTest.class);
//		RestAssured.baseURI =this.getWarehouse(warehouse).getSg_url(); //根据warehouse自动获取各区sg 地址
//		  Response r = given()
//				  .contentType("application/json;charset=UTF-8")
//				  .header("X-Ca-User-Sign", SignUtil13.getSignForUserSign(requestJson))
//				  .header("X-Ca-Client-Sign", SignUtil13.getSignForClientSign(requestJson))
//				  .body(requestJson)
//				  .when().post();
//		  log.info("service-gate url :"+ this.getWarehouse(warehouse).getSg_url());
//		  log.info("*******************************request**********************************");
//		  log.info(requestJson);  //request
//		  log.info("*******************************response*********************************");
//		  log.info(r.asString()); //responseBody
//
//		  return r.then().body("code", equalTo("0")).extract().response().asString();
//	}

}
