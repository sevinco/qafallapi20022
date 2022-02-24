package post_http_request_method;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.Todo;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
public class Post03 extends JsonPlaceHolderBaseUrl {
    /*
       When
             I send POST Request to the Url https://jsonplaceholder.typicode.com/todos
             with the request body {
                                   "userId": 55,
                                   "title": "Tidy your room",
                                   "completed": false
                                  }
       Then
           Status code is 201
           And response body is like {
                                       "userId": 55,
                                       "title": "Tidy your room",
                                       "completed": false,
                                       "id": 201
                                     }
                                     username: admin
                                     password: 1234
    */
    @Test
    public void post03(){
        //Set the base url
        spec.pathParam("first", "todos");
        //Set the expected data
        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put("userId", 55);
        expectedData.put("title","Tidy your room");
        expectedData.put("completed", false);
        //Send the Post request and get the response
        //When we use auth, we can use diffrent type of auths in API. we use basic auth which requires username and password
        Response response = given().spec(spec).auth().basic("admin", "1234").
                contentType(ContentType.JSON).
                body(expectedData).
                when().post("/{first}");
        //validation
        response.then().statusCode(201);
        response.prettyPrint();
        //1. way of validation
        Map<String, Object> actualData = response.as(HashMap.class);
        System.out.println("expected data: "+expectedData);
        System.out.println("actual data: "+actualData);
        assertEquals(expectedData.get("userId"),actualData.get("userId") );
        assertEquals(expectedData.get("title"),actualData.get("title") );
        assertEquals(expectedData.get("completed"),actualData.get("completed") );
        //2. way using JsonPath
        JsonPath json = response.jsonPath();
        assertEquals(expectedData.get("userId"),json.getInt("userId") );
        assertEquals(expectedData.get("title"), json.getString("title"));
        assertEquals(expectedData.get("completed"), json.getBoolean("completed"));
        //3. way of validation
        response.then().body("userId", equalTo(expectedData.get("userId"))).
                body("title", equalTo(expectedData.get("title"))).
                body("completed", equalTo(expectedData.get("completed")));
        //4. validation
        Todo todo = response.as(Todo.class);
        assertEquals(expectedData.get("userId"),todo.getUserId() );
        assertEquals(expectedData.get("title"),todo.getTitle() );
        assertEquals(expectedData.get("completed"),todo.isCompleted() );
    }
}
