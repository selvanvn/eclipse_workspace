package ApiTest;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import utilities.JiraSetup;
import utilities.PayLoad;
import utilities.Resources;

import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class JiraApiTest1 {
	Properties prop= new Properties();
	String issueId ;
	String commentId;
	
	@BeforeTest
public void intilaze() throws IOException{
		
		FileInputStream fs = new FileInputStream("D:\\workspace_eclipse\\ApiTest\\src\\utilities\\set.properties");
		prop.load(fs);
	}

	@Test(priority=0)
	public void addIssue(){
		RestAssured.baseURI=prop.getProperty("JIRAHOST");
		Response rs= given().header("Content-Type","application/json").header("Cookie","JSESSIONID=","JSESSIONID="+JiraSetup.getSessionId()).
		body(PayLoad.getJiraIssueBody()).
		when().post(prop.getProperty("JIRAADDISSUE")).
		then().assertThat().statusCode(201).
		extract().response();
		JsonPath js= utilities.Resources.rawToJson(rs);
		issueId = js.get("id");
		System.out.println("Issue ID " +issueId);		
	}
	@Test(priority=1)
	public void addComment()
	{	
		//System.out.println(issueId);
		RestAssured.baseURI=prop.getProperty("JIRAHOST");
		Response rs= given().header("Content-Type","application/json").header("Cookie","JSESSIONID="+JiraSetup.getSessionId()).
		body(PayLoad.getJiraAddCoommentBody()).
		when().post("/rest/api/2/issue/"+issueId+"/comment").
		then().assertThat().statusCode(201).
		extract().response();
		JsonPath js= utilities.Resources.rawToJson(rs);
		commentId = js.get("id");
		System.out.println("Comment ID " +commentId);
	}
	@Test(priority=2)
	public void updateComment()
	{
		RestAssured.baseURI=prop.getProperty("JIRAHOST");
		Response rs= given().header("Content-Type","application/json").header("Cookie","JSESSIONID="+JiraSetup.getSessionId()).
		body(PayLoad.getJiraUpdateCoommentBody()).
		when().put("/rest/api/2/issue/"+issueId+"/comment/"+commentId+"").
		then().assertThat().statusCode(200).
		extract().response();
		/*JsonPath js= utilities.Resources.rawToJson(rs);
		String commentId = js.get("id");
		System.out.println("Comment ID " +commentId);*/
	}
}
