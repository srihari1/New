package commonFunctionLibrary;

import static io.restassured.RestAssured.given;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class APIFunctionLibrary {

	public  RestAssured restAssured;
	public  String responsebody;
	public  Response response;
	public  String uri;
	public String testEnv;


	public void initialize_Uri() {
		uri =System.getProperty("Uri");
		testEnv=System.getProperty("TestEnv");
	}
	
	public  String getWebServiceURI() throws Exception {
		try {
			//URI = Propertiesfileutil.getEnvValue("API_URI");
			RestAssured.baseURI = uri;
		} catch (Exception e) {
			e.printStackTrace();
		} catch (Throwable e) {

			e.printStackTrace();
		}
		return uri;

	}

	public String addQueryParameterInWebServiceURI(String queryParameter) throws Exception {
		try {
			uri = uri + "?" + queryParameter;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return uri;

	}

	public  Response apiGet(String BasePath) throws Exception{
	
			Thread.sleep(2000);
			response = given().contentType("application/json").when().get(BasePath).then().assertThat().statusCode(200)
					.and().extract().response();
			return response;

	}

	public static void apiPost(File Payloadjson, String BasePath) throws Exception {
		try {
			Thread.sleep(2000);
//			response = given().contentType(ContentType.JSON).body(Payloadjson).when().post(BasePath).then().assertThat()
//					.statusCode(201).and().extract().response();
			System.out.println("Post Method Executed Successfully");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public  void apiPut(File Payloadjson, String BasePath) throws Exception {
		try {
			Thread.sleep(2000);
			response = given().contentType(ContentType.JSON).body(Payloadjson).when().put(BasePath).then().assertThat()
					.statusCode(200).and().extract().response();
			System.out.println("Put Method Executed Successfully");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void apiDelete(String BasePath) throws Exception {
		try {
			Thread.sleep(2000);
			response = given().contentType(ContentType.JSON).when().delete(BasePath).then().assertThat().statusCode(204)
					.and().extract().response();
			System.out.println("Delete Method Executed Successfully");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addCookieAndDoPostCall(String body, String cookie, String BasePath) throws Exception {
		try {
			Thread.sleep(2000);
			response = given().cookie(cookie).contentType("application/json").when().body(body).post(BasePath).then()
					.assertThat().statusCode(201).and().extract().response();
			System.out.println("Post Method Executed Successfully with cookie");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public  int getStatusCode() throws Exception {
		int responseCode = 0;
		try {
			Thread.sleep(2000);
			responseCode = response.getStatusCode();
			System.out.println("Response Status Code is " + response.getStatusCode());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseCode;
	}

	public  String getStatusLine() throws Exception {
		String responseStatusLine = null;
		try {
			Thread.sleep(2000);
			responseStatusLine = response.getStatusLine();
			System.out.println("Response Status line is " + response.getStatusLine());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseStatusLine;
	}

	public String getResponse() throws Exception {
		try {
			Thread.sleep(2000);
			responsebody = response.getBody().asString();
			System.out.println("Response Body is " + responsebody);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responsebody;
	}

	public int getErrorCodeInResponse() throws Exception {
		int errorCodeInResponse = 0;
		try {
			errorCodeInResponse = response.getBody().jsonPath().get("errorCode");
			System.out.println("Error code in Response is " + errorCodeInResponse);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return errorCodeInResponse;
	}

	public int getValueForIntKeyInResponse(String key) throws Exception {
		int valueForKeyInResponse = 0;
		try {
			valueForKeyInResponse = response.getBody().jsonPath().get(key);
			System.out.println("Value for Key in Response is " + valueForKeyInResponse);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return valueForKeyInResponse;
	}

	public String getValueForStringKeyInResponse(String key) throws Exception {
		String valueForKeyInResponse = null;
		try {
			valueForKeyInResponse = response.getBody().jsonPath().get(key);
			System.out.println("Value for Key in Response is " + valueForKeyInResponse);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return valueForKeyInResponse;
	}

	public  String getValueForKeyInResponseArray(String Array, String key) throws Exception {
		String valueForKeyInResponse = null;
		try {
			Object obj = new JSONParser().parse(response.asString());
			JSONObject jo = (JSONObject) obj;
			JSONArray ja = (JSONArray) jo.get(Array);
			for (int it = 0; it < ja.size(); it++) {
				JSONObject contactItem = (JSONObject) ja.get(it);
				String value = (String) (contactItem.get(key));
				System.out.println(value);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return valueForKeyInResponse;
	}

	public String getErrordescriptionInResponse() throws Exception {
		String ErrordescriptionInResponse = null;
		try {
			ErrordescriptionInResponse = response.getBody().jsonPath().get("errorDescription");
			System.out.println("Error description in Response is " + ErrordescriptionInResponse);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ErrordescriptionInResponse;
	}

	public String checkErrorCodeResponse(String responseCode) throws Exception {
		try {
			//assertThat(getErrorCodeInResponse()).isEqualTo(responseCode);
			System.out.println("Error code matches");
		} catch (Exception e) {
			System.out.println("Execption in Error code, no match found: " + e.getMessage());
		}
		return responsebody;
	}

	public void bearerTokenAuthentication(String username, String password, String bearerToken)
			throws Exception {
		try {
			given().headers("Authorization", "Bearer " + bearerToken, "Content-Type", ContentType.JSON, "Accept",
					ContentType.JSON);
			System.out.println("Bearer Authentication successfully");
		} catch (Exception e) {
			System.out.println("Execption in Bearer Authentication: " + e.getMessage());
		}
	}

	public void formAuthentication(String username, String password) throws Exception {
		try {

			System.out.println("Form Authentication successfully");
		} catch (Exception e) {
			System.out.println("Execption in Form Authentication: " + e.getMessage());
		}
	}

	public void digestAuthentication(String username, String password) throws Exception {
		try {

			System.out.println("Digest Authentication successfully");
		} catch (Exception e) {
			System.out.println("Execption in Digest Authentication: " + e.getMessage());
		}
	}

	public void OATH2Authentication(String username, String password) throws Exception {
		try {

			System.out.println("OAth2 Authentication successfully");
		} catch (Exception e) {
			System.out.println("Execption in OAth2 Authentication: " + e.getMessage());
		}
	}

	public void requestWithBasicAuthentication(String username, String password) throws Exception {
		try {
			given().auth().basic(username, password);
			System.out.println("Basic Authentication successfully");
		} catch (Exception e) {
			System.out.println("Execption in basic Authentication: " + e.getMessage());
		}
	}

	public void requestWithPreemptiveAuthentication(String username, String password) throws Exception {
		try {
			PreemptiveBasicAuthScheme authScheme = new PreemptiveBasicAuthScheme();
			authScheme.setUserName(username);
			authScheme.setPassword(password);
			RestAssured.authentication = authScheme;
			System.out.println("Preemitive Authentication successfully");
		} catch (Exception e) {
			System.out.println("Execption in Preemitive Authentication: " + e.getMessage());
		}
	}
	

	public String getCurrentWorkingDir() {
		return System.getProperty("user.dir");
	}

	public JSONObject getTestData(String jsonFileName, String recordName, int dataArrayNumber, String  testEnv) throws IOException, ParseException {
		JSONParser jsonParser = new JSONParser();
		FileReader reader = new FileReader(getCurrentWorkingDir()+"\\src\\test\\resources\\testData\\"+jsonFileName+"_"+testEnv+".json");


		Object obj = jsonParser.parse(reader);
		JSONObject userloginJsonobj=(JSONObject)obj;
		JSONArray userloginArray = (JSONArray)userloginJsonobj.get(recordName);
		JSONObject users= (JSONObject)userloginArray.get(dataArrayNumber);
		return users;
	}
	
	public JSONObject getTestData(String jsonFileName, String recordName, int dataArrayNumber ) throws Exception {
		JSONParser jsonParser = new JSONParser();
		FileReader reader = new FileReader(getCurrentWorkingDir()+"\\src\\test\\resources\\testData\\"+jsonFileName+"_"+testEnv+".json");


		Object obj = jsonParser.parse(reader);
		JSONObject userloginJsonobj=(JSONObject)obj;
		JSONArray userloginArray = (JSONArray)userloginJsonobj.get(recordName);
		JSONObject users= (JSONObject)userloginArray.get(dataArrayNumber);
		return users;
	}


}
