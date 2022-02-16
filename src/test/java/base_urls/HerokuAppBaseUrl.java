package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;
import org.junit.runner.Request;

public class HerokuAppBaseUrl {

//   This will be null for now until I set it
    protected RequestSpecification spec;

    @Before
    public void setup(){
        spec = new RequestSpecBuilder().setBaseUri("https://restful-booker.herokuapp.com/").build();

    }


}
