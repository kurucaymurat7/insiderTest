package API.Tests;

import API.Pojo.Category;
import API.Pojo.RequestBody;
import API.Pojo.Tag;
import core.Asserts.Asserts;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Arrays;

public class CrudTests extends BaseTest {
    private int petID = 1;
    private String params = "/pet";
    private String statusUpdate = "sahipsiz";

    @Test(priority = 0)
    public void sendPOST() {
        RequestBody requestBody = new RequestBody(
                petID,
                new Category(1, "sokak hayvanı"),
                "doggie",
                Arrays.asList("doggie photos"),
                Arrays.asList(new Tag(1, "@doggie")),
                "sahipli"
        );

        // Send POST Request with Body
        Response response = request.body(requestBody).post(params);

        // Validate Response
        Asserts.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 1)
    public void sendPUT() {
        RequestBody requestBody = new RequestBody(
                petID,
                new Category(1, "sokak hayvanı"),
                "doggie",
                Arrays.asList("doggie photos"),
                Arrays.asList(new Tag(1, "@doggie")),
                statusUpdate // updating status of the doggie
        );

        Response response = request.body(requestBody).put(params);

        Asserts.assertEquals(response.getStatusCode(), 200);
        Asserts.assertEquals(response.jsonPath().getInt("id"), petID);
        Asserts.assertEquals(response.jsonPath().getString("status"), statusUpdate);
    }

    @Test(priority = 2)
    public void sendGET() {
        Response response = request.get(params + "/" + petID);

        response.prettyPrint();

        Asserts.assertEquals(response.getStatusCode(), 200);
        Asserts.assertEquals(response.jsonPath().getInt("id"), petID);
        Asserts.assertEquals(response.jsonPath().getString("status"), statusUpdate);
    }

    @Test(priority = 3)
    public void sendDELETE() {
        Response response = request.delete(params + "/" + petID);

        response.prettyPrint();

        Asserts.assertEquals(response.getStatusCode(), 200);
        Asserts.assertEquals(response.jsonPath().getInt("code"), 200);
    }
}
