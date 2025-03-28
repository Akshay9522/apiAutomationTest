package base;

import config.ConfigReader;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static io.restassured.RestAssured.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BaseTest {

	protected static String baseURL = ConfigReader.getProperty("baseUrl");
	
	protected static RequestSpecification reqSpec = new RequestSpecBuilder().setBaseUri(baseURL)
			                        .setContentType(ContentType.JSON)
			                        .build();
	protected static ResponseSpecification reqResp = new ResponseSpecBuilder().expectContentType(ContentType.JSON)
	            .build();
	protected  String getJsonPath(Response response,String key)
	{
		  String resp=response.asString();
		JsonPath   js = new JsonPath(resp);
		return js.get(key).toString();
	}
	
	protected String objectToStringConv(Object obj) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(obj);
        return json;
	}

}
