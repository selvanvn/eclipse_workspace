package initial;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import utilities.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class BasicPostDelete {
	Properties prop = new Properties();
	PayLoad p = new PayLoad();
	Resources r = new Resources();
	@BeforeTest
	public void intilaze() throws IOException{
		
		FileInputStream fs = new FileInputStream("D:\\workspace_eclipse\\ApiTest\\src\\utilities\\set.properties");
		prop.load(fs);
	}
	@Test
	public void method1() {
		RestAssured.baseURI = prop.getProperty("HOST");
		Response rs= given().queryParam("key", prop.getProperty("KEY")).body(p.postBody()).
		when().post(r.postURL()).
				then().assertThat().statusCode(200).and()
				.contentType(ContentType.JSON).and().body("status", equalTo("OK")).
				extract().response();
		JsonPath jp =Resources.rawToJson(rs);
		String placeid= jp.get("place_id");
		System.out.println(placeid);		
		given().queryParam("key", prop.getProperty("KEY")).body("{"+
				  "\"place_id\": \""+placeid+"\""+
				"}").
		when().post(r.deleteURL()).
		then().assertThat().statusCode(200).and()
		.contentType(ContentType.JSON).and().body("status", equalTo("OK"));
		
	}
}
