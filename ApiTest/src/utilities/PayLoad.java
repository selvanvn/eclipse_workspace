package utilities;

public class PayLoad {
//All body is contained in this Class
	public String postBody(){
		String b = "{" + " \"location\": {" + " \"lat\": -33.8669710," + " \"lng\": 151.1958750" + " },"
				+ "\"accuracy\": 50," + "\"name\": \"Google Shoes!\"," + " \"phone_number\": \"(02) 9374 4000\","
				+ "\"address\": \"48 Pirrama Road, Pyrmont, NSW 2009, Australia\"," + " \"types\": [\"shoe_store\"],"
				+ " \"website\": \"http://www.google.com.au/\"," + " \"language\": \"en-AU\"" + "}";
		return b;
	}
	public static String postJiraSessionBody()
	{
		String b= "{ \"username\": \"selvanvn\", \"password\": \"Selva@1331\" }";
		return b;
	}
	public static String getJiraIssueBody(){
		String b="{" +
	"\"fields\": {"+
       " \"project\":" +
        "{"+
          "\"key\": \"RES\""+
        "},"+
        "\"summary\": \"From API Testing\","+
        "\"description\": \"Descrption of the isuue\","+
        "\"issuetype\": {"+
          "\"name\": \"Bug\""+
        "}"+
	"}"+
"}";
		return b;		
	}
	public static String getJiraAddCoommentBody(){
		String b ="{"+
			      "\"body\": \"Added comment\","+
			      "\"visibility\": {"+
			        "\"type\": \"role\","+
			        "\"value\": \"Administrators\"" +
			      "}"+
			    "}";
		return b;
	}

public static String getJiraUpdateCoommentBody(){
	String b ="{"+
		      "\"body\": \"Updated comment from code\","+
		      "\"visibility\": {"+
		        "\"type\": \"role\","+
		        "\"value\": \"Administrators\"" +
		      "}"+
		    "}";
	return b;
}
}
