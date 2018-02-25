package utilities;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class JiraSetup {
	

	public static String getSessionId(){
	RestAssured.baseURI="http://localhost:8100";
	Response res = given().header("Content-Type","application/json").
	body(PayLoad.postJiraSessionBody()).
	when().post("/rest/auth/1/session").
	then().assertThat().statusCode(200).
	extract().response();
	JsonPath js= utilities.Resources.rawToJson(res);
	String sval = js.get("session.value");
	return sval;
	}
}
