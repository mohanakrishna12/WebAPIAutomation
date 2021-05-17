package com.equifax.util;

 
public class APIConfiguartion extends BaseClass{
	public static String HOST = null;
	public static int RETRY_COUNT =0;
	public static int SUCCESS_STATUS_CODE =0;
	public static String CONTENT_TYPE = null;
	public static String CONTENT_TYPE_VALUE = null;
	public static String STATUS = null;
	public static String SUCCESS = null;
	public static String MESSAGE_KEY = null;
	public static String ADD_MESSAGE_VALUE = null;
	public static String DELETE_MESSAGE_VALUE = null;
	public static String KEY_ID = null;
	public static  int idValue;
	
	public APIConfiguartion() {
		HOST =loadProperties().getProperty("host");
		CONTENT_TYPE =loadProperties().getProperty("host");
		CONTENT_TYPE_VALUE =loadProperties().getProperty("host");
		RETRY_COUNT=Integer.parseInt(loadProperties().getProperty("RETRY_COUNT"));
		SUCCESS_STATUS_CODE=Integer.parseInt(loadProperties().getProperty("SUCCESS_STATUS_CODE"));
		STATUS =loadProperties().getProperty("STATUS");
		SUCCESS =loadProperties().getProperty("SUCCESS");
		MESSAGE_KEY =loadProperties().getProperty("MESSAGE_KEY");
		ADD_MESSAGE_VALUE =loadProperties().getProperty("ADD_MESSAGE_VALUE");
		KEY_ID =loadProperties().getProperty("KEY_ID"); 
		DELETE_MESSAGE_VALUE =loadProperties().getProperty("DELETE_MESSAGE_VALUE");
	}
	public int getIdValue() {
		return idValue;
	}
	public void setIdValue(int idValue) {
		this.idValue = idValue;
	}
	public static String getResource()
	{
		String res="/employees";
		return res;
	}
	public static String getResourceID(int empId)
	{
		String res="/employee/"+empId;
		return res;
	}
	public static String postResource() {
		String res="/create";
		return res;
		
	}
	
	public static String deleteResource(int empid) {
		return "/delete/" + empid;
	}
	public static String getPostPayload() {
		return "\"name\":\"smkrishna\",\"salary\":\"8322\",\"age\":\"23\"";
	 
	}
}
