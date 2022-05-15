package testScripts.api;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import commonFunctionLibrary.APIFunctionLibrary;
import helperClasses.APIAllureListener;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

@Listeners({ APIAllureListener.class })
public class ApiTest extends APIFunctionLibrary{
	public JSONObject jsonDataObj;
	
	@BeforeClass
	public void setup() throws Exception {		
		initialize_Uri();
	}
	
	@Test
	public void user_validates_Get_Rest_Service() throws Exception {	
		jsonDataObj= getTestData("testdata", "OrderDetails", 0);
		System.out.println((String)jsonDataObj.get("GuestEmail"));
		Response response =apiGet(uri);
		String responseBody = response.getBody().asString();
		System.out.println(responseBody);
		Assert.assertEquals(200,response.getStatusCode() );	
	}

	@Test
	public void user_validates_Post_Rest_Service() throws Exception {	
		jsonDataObj= getTestData("testdata", "OrderDetails", 0);
		System.out.println((String)jsonDataObj.get("GuestEmail"));
		RestAssured.baseURI=uri;
		RequestSpecification httpRequest = RestAssured.given();
		httpRequest.given().contentType("application/json; charset=UTF-8");
		//		httpRequest.body("[{ \"userId\": \""+id+"\" , \"title\": \""+email+"\" , \"completed\": \""+applicantID+"\" }]");
		httpRequest.body("[{ \"userId\": 11111 , \"title\": \"clean room\" , \"completed\": false }]");
		Response response = httpRequest.post();
		String responseBody = response.getBody().asString();
		System.out.println("responseBody"+responseBody);
		Assert.assertEquals(response.getStatusCode(),201 );		
	}

	@Test
	public void user_validates_Put_Rest_Service() throws Exception {	
		jsonDataObj= getTestData("testdata", "OrderDetails", 0);
		System.out.println((String)jsonDataObj.get("GuestEmail"));

		RestAssured.baseURI=uri+"/5";
		RequestSpecification httpRequest = RestAssured.given();
		httpRequest.given().contentType("application/json; charset=UTF-8");
		httpRequest.body("[{ \"userId\": 1 , \"id\": 5 , \"title\": \"put task\" , \"completed\": false }]");
		Response response = httpRequest.put();
		String responseBody = response.getBody().asString();
		System.out.println("responseBody"+responseBody);
		Assert.assertEquals(200,response.getStatusCode());		

	}

	@Test
	public void user_validates_Delete_Rest_Service() throws Exception {	
		jsonDataObj= getTestData("testdata", "OrderDetails", 0);
		System.out.println((String)jsonDataObj.get("GuestEmail"));

		RestAssured.baseURI=uri+"/1";
		RequestSpecification httpRequest = RestAssured.given();
		httpRequest.given().contentType("application/json; charset=UTF-8");
		Response response = httpRequest.delete();
		String responseBody = response.getBody().asString();
		System.out.println("responseBody"+responseBody);		
		Assert.assertEquals(201,response.getStatusCode());		

	}


}
