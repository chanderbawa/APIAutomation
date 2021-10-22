package utils;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import java.util.ArrayList;
import org.testng.Assert;
public class TestUtil {
    //Verify the http response status returned. Check Status Code is 200?
    public void checkStatusIs200(Response res) {
        Assert.assertEquals(res.getStatusCode(), 200, "Status Check Failed!");
    }
   
  //Verify the http response status returned. Check Status Code 
    public void checkStatusCode(Response res,int statusCode) {
        Assert.assertEquals(res.getStatusCode(), statusCode, "Status Check Failed!");
    }
    
    //Get Clients
    public <T> T getClients(JsonPath jp) {
        return jp.get();
    }
    
//    public <T> HashMap<K,V> getClients(JsonPath jp) {
//        return jp.get();
//    }
    
}
