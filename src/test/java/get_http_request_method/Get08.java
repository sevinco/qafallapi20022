package get_http_request_method;

import base_urls.JSonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
public class Get08 extends JSonPlaceHolderBaseUrl {
    /*
    Given
           https://jsonplaceholder.typicode.com/todos/2
    When I send a Get Request
    Then the actual data should be as following;
       {
       "userId": 1,
       ""id: 2,
       "title": "quis ut nam facilis et officia qui",
       "completed": false
   }
    */
    @Test
    public void get08(){
        //1. Set the base url
        spec.pathParams("first", "todos", "second",2);
        //2. Set the expected data
        Map<String,Object> expectedData = new HashMap<>();
        expectedData.put("userId", 1);
        expectedData.put("id", 2);
        expectedData.put("title", "quis ut nam facilis et officia qui");
        expectedData.put("completed", false);
        //3. Send the Get request and get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        //response.prettyPrint();
        Map<String, Object> actualData = response.as(HashMap.class);
        assertEquals(expectedData.get("userId"), actualData.get("userId"));
        assertEquals(expectedData.get("id"), actualData.get("id"));
        assertEquals(expectedData.get("title"), actualData.get("title"));
        assertEquals(expectedData.get("completed"), actualData.get("completed"));
    }
}