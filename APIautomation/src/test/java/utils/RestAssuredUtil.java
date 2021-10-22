package utils;

import static io.restassured.RestAssured.given;

import java.io.File;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
public class RestAssuredUtil {
	public static String authToken;
    //Sets Base URI
    public static void setBaseURI() {
        //RestAssured.baseURI = "http://generator.swagger.io/";
       // RestAssured.baseURI = "https://restful-booker.herokuapp.com";
    	RestAssured.baseURI = "https://qa.bc.conns.com";
    	
    }
    //Sets base path
    public static void setBasePath(String basePathTerm) {
        RestAssured.basePath = basePathTerm;
    }
    //Reset Base URI (after test)
    public static void resetBaseURI() {
        RestAssured.baseURI = null;
    }
    //Reset base path
    public static void resetBasePath() {
        RestAssured.basePath = null;
    }
    //Sets ContentType
    public static void setContentType(ContentType Type) {
        given().contentType(Type);
    }
    //Returns response by given path
    public static Response getResponse(String path) {
        return given().get(path);
    }
    //Returns response
    public static Response getResponse() {
        return given().get();
    }
    //Returns JsonPath object
    public static JsonPath getJsonPath(Response res) {
        String json = res.asString();
        return new JsonPath(json);
    }
    
    public static Response postCallWithHeader(String uRI,File stringJSON){
    	Response response = RestAssured.given().body(stringJSON).contentType(ContentType.JSON).post(uRI);
		return response;
	}
    
    public static Response putCallWithAuthAndHeader(String uRI,String token,File stringJSON){
    	Response response = RestAssured.given()
    			           .cookie("token", token)
    			           .body(stringJSON)
    			           .contentType(ContentType.JSON).put(uRI);
		return response;
	}
    
    public static Response postCallWithAuthAndHeader(String uRI,String token,File stringJSON){
    	Response response = RestAssured.given()
    			           .cookie("token", token)
    			           .body(stringJSON)
    			           .contentType(ContentType.JSON).post(uRI);
		return response;
	}
    
    public static Response DeleteCallWithAuth(String uRI,String token){
    	Response response = RestAssured.given()
    			           .cookie("token", token)
    			           .contentType(ContentType.JSON).delete(uRI);
		return response;
	}
    
    public static String RetriveAccessToken(String endpoint,String Token)
    {
    String token=null;
    try
    {
    System.out.println("Trying to get the Access token" + endpoint);
    token = (String) given()
    .header("Content-type", "application/json")
    .header("Authorization", "Basic" + token)
    .post(endpoint)
    .then()
    .statusCode(200)
    .extract()
    .response()
    .jsonPath().getString("access_token");
    System.out.println(token);
    }
    catch(Exception ex)
    {
    System.out.println("Error occured while retrieving the access token and Exception =>" + ex.getMessage());
    }
    return token;
    }

    
    public static <T> Response POST(String endpoint,T model, String token)
    {
    	Response response=null;
    	String jsonrequest = null;
    	try
    	{
    		System.out.println("Posting request with endpoint" +  endpoint);
    		if(model!=null)
    		{
    			jsonrequest = SerelizeModel(model);
    		}
    		response = (Response) given()
    				.header("Content-type", "application/json")				
    				.header("Authorization", "Basic" + token)
    				.and()
    				.body(jsonrequest)
    				.post(endpoint)
    				.then()
    				.statusCode(200)
    				.extract()
    				.response();
    				System.out.println(response.asString())	;		
    		
    	}
    	catch(Exception ex)
    	{
    		System.out.println("Error occured while Posting request and Exception =>" +  ex.getMessage());
    	
    	}
    	return response; 
    }
    

    public static Response POSTWithoutBody(String endpoint, String token)
    {
    	Response response=null;
    	String jsonrequest = null;
		try
		{
			System.out.println("Posting request with endpoint" +  endpoint);
			
			response = (Response) given()
					.header("Content-type", "application/json")				
					.header("Authorization", "Basic" + token)	
					.and()
					.post(endpoint)
					.then()
					.statusCode(200)
					.extract()
					.response();
					System.out.println(response.asString())	;	
			
		}
		catch(Exception ex)
		{
			System.out.println("Error occured while Posting request and Exception =>" +  ex.getMessage());
		
		}
		return response;
    }
    public static Response POSTWithJSON(String endpoint, String token,String JSON)
    {
    	Response response=null;
    	String jsonrequest = null;
		try
		{
			System.out.println("Posting request with endpoint" +  endpoint);
			
			response = (Response) given()
					.header("Content-type", "application/json")				
					.header("Authorization", "Basic" + token)		
					.body(JSON)
					.post(endpoint)
					.then()
					.statusCode(200)
					.extract()
					.response();
					System.out.println(response.asString())	;	
			
		}
		catch(Exception ex)
		{
			System.out.println("Error occured while Posting request and Exception =>" +  ex.getMessage());
		
		}
		return response;
    }
    
    private static <T> String SerelizeModel(T usermodel)
    {	
    	  ObjectMapper mapper = new ObjectMapper();
          //Converting the Object to JSONString
          String jsonString = mapper.writeValueAsString(usermodel);
          System.out.println(jsonString);
          return jsonString;
    }
}

