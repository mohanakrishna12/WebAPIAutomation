package com.equifax.test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.equifax.util.APIConfiguartion;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ApiAutomationTest extends APIConfiguartion {

	@Test(description = "Create an employee data and retrieve the response from the server")
	public void testPostResponseAPI() {
		RestAssured.baseURI = HOST;
		Response response = null;
		try {
			for (int i = 0; i < RETRY_COUNT; i++) {

				response = given().header(CONTENT_TYPE, CONTENT_TYPE_VALUE).body(getPostPayload()).when()
						.post(postResource()).then().assertThat().body(STATUS, equalTo(SUCCESS)).and()
						.body(MESSAGE_KEY, equalTo(ADD_MESSAGE_VALUE)).extract().response();
				if (response.getStatusCode() == SUCCESS_STATUS_CODE)
					break;
				else
					wait(15);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		String responseString = response.asString();
		JsonPath js = new JsonPath(responseString);

		idValue = js.getInt(KEY_ID);
		setIdValue(idValue);
	}

	@Test(dependsOnMethods = "testPostResponseAPI", description = "Get all employees records from the server")
	public void testGetAllEmployeesData() {
		Response response = null;
		RestAssured.baseURI = HOST;
		try {
			for (int i = 0; i < RETRY_COUNT; i++) {
				response = RestAssured.given().when().get(getResource());
				if (response.getStatusCode() == SUCCESS_STATUS_CODE)
					break;
				else
					wait(15);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertEquals(SUCCESS_STATUS_CODE, response.getStatusCode());
	}

	@Test(dependsOnMethods = "testPostResponseAPI", description = "Get specific employee record from the server")
	public void testGetSpecificEmployeeData() {
		RestAssured.baseURI = HOST;
		Response response = null;
		int empid = getIdValue();

		try {
			for (int i = 0; i < RETRY_COUNT; i++) {
				response = RestAssured.given().when().get(getResourceID(empid));
				if (response.getStatusCode() == SUCCESS_STATUS_CODE)
					break;
				else
					wait(15);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertEquals(SUCCESS_STATUS_CODE, response.getStatusCode());
	}

	@Test(dependsOnMethods = "testGetSpecificEmployeeData", description = "delete specific employee record from the server")
	public void testDeleteEmpRecord() {
		RestAssured.baseURI = HOST;
		Response response = null;
		int empid = getIdValue();
		try {
			for (int i = 0; i < RETRY_COUNT; i++) {
				response = given().header(CONTENT_TYPE, CONTENT_TYPE_VALUE).when().delete(deleteResource(empid));
				if (response.getStatusCode() == SUCCESS_STATUS_CODE)
					break;
				else
					wait(15);
			}
			String responseString = response.asString();
			JsonPath js = new JsonPath(responseString);
			idValue = js.getInt(KEY_ID);
			response.then().assertThat().body(STATUS, equalTo(SUCCESS));
		} catch (Exception e) {
		}
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, SUCCESS_STATUS_CODE);
		String jsonString = response.asString();
		Assert.assertEquals(jsonString.contains(DELETE_MESSAGE_VALUE), true);
	}

}
