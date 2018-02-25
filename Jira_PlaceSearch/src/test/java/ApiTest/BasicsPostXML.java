package ApiTest;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import utilities.Resources;

import static io.restassured.RestAssured.given;
//import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class BasicsPostXML {
	
	
	@Test
	public void postTest() throws IOException{
		
	RestAssured.baseURI= "https://maps.googleapis.com";
	String p=covertXMLtoString("D:\\API Testing\\PlaceSearchPost.xml");
	System.out.println(p);
	Response rs=given().
	queryParam("key","AIzaSyBTX_Zxsab8G5KnG49T6irm1t96bxYbals").
			body(p).
	when().
	post("/maps/api/place/add/xml").
	then().
	assertThat().
	statusCode(200).
	and().
	contentType(ContentType.XML).and().extract().response();
	
	XmlPath xp=Resources.rawToXml(rs);
	String t = xp.get("PlaceAddResponse.place_id");
	//System.out.println(xp.get("PlaceAddResponse.place_id"));
	System.out.println(t);

	}

	public static String covertXMLtoString(String path) throws IOException{
		return new String(Files.readAllBytes(Paths.get(path)));
	}
}
