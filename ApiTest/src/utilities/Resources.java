package utilities;

import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class Resources {

	public String postURL(){
		String url = "/maps/api/place/add/json";
		return url;
	}
	public String deleteURL(){
		String url="/maps/api/place/delete/json";
		return url;
	}
	public static JsonPath rawToJson(Response r)
	{
		String s=r.asString();
		JsonPath j=new JsonPath(s);
		return j;
	}
	public static XmlPath rawToXml(Response r)
	{
		String s=r.asString();
		XmlPath x=new XmlPath(s);
		return x;
	}
}
