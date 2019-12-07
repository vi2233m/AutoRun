package com.zot.autorun.moudules.apitest.utils;

public class SuccessSku {
	
	private String warehouse;
	private String skuName;
	private String skuNum;
	private String status;
	private String failStatus;
	
	public SuccessSku(String warehouse, String skuName, String skuNum, String status, String failStatus) {
		this.warehouse = warehouse;
		this.skuName = skuName;
		this.skuNum = skuNum;
		this.status = status;
		this.failStatus = failStatus;
	}
		
	public String getFailStatus() {
		return failStatus;
	}
	public void setFailStatus(String failStatus) {
		this.failStatus = failStatus;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getWarehouse() {
		return warehouse;
	}
	public void setWarehouse(String warehouse) {
		this.warehouse = warehouse;
	}
	public String getSkuName() {
		return skuName;
	}
	public void setSkuName(String skuName) {
		this.skuName = skuName;
	}
	public String getSkuNum() {
		return skuNum;
	}
	public void setSkuNum(String skuNum) {
		this.skuNum = skuNum;
	}
	
	

}
