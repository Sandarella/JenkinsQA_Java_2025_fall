package school.redrover.api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.APIBaseTest;

import java.util.HashMap;
import java.util.Map;

public class APIJenkinsTest extends APIBaseTest {

    @Test
    public void testCreatePiplineAndDisable() {
        Map<String, String> projectName = new HashMap<>();
        projectName.put("name", "Pipline");

        String bodyPiplineXML = """
                 <flow-definition plugin="workflow-job@1559.va_a_533730b_ea_d">
                                          <keepDependencies>false</keepDependencies>
                                          <properties/>
                                          <triggers/>
                                          <disabled>false</disabled>
                                          </flow-definition>
                """;

        RestAssured.given()
                .auth().preemptive().basic(userName, apiToken)
                .baseUri(jenkinsUrl)
                .contentType(ContentType.XML)
                .queryParams(projectName)
                .body(bodyPiplineXML)
                .when()
                .post("/createItem")
                .then()
                .log().all()
                .statusCode(200);

        Response response = RestAssured.given()
                .log().all()
                .auth().preemptive().basic(userName, apiToken)
                .baseUri(jenkinsUrl)
                .when()
                .post("job/%s/disable".formatted(projectName.get("name")))
                .then()
                .log().all()
                .extract().response();

        String location = response.getHeader("Location");

        Response getResponse = RestAssured.given()
                .auth().preemptive().basic(userName, apiToken)
                .baseUri(jenkinsUrl)
                .when()
                .get(location)
                .then()
                .log().all()
                .statusCode(200)
                .extract().response();

        String responseBody = getResponse.getBody().asString();

        Assert.assertEquals(response.getStatusCode(), 302);
        Assert.assertEquals(getResponse.getStatusCode(), 200);
        Assert.assertTrue(responseBody.contains("This project is currently disabled"));
    }

    @Test
    public void testCreateAndDeleteFolder() {
        String folderName = "ApiTestFolder";

        String folderConfigXml = """
                <com.cloudbees.hudson.plugins.folder.Folder>
                    <description>Created via CloudBees Folder Plugin API</description>
                </com.cloudbees.hudson.plugins.folder.Folder>
                """;

        RestAssured.given()
                .auth().preemptive().basic(userName, apiToken)
                .baseUri(jenkinsUrl)
                .contentType(ContentType.XML)
                .queryParam("name", folderName)
                .body(folderConfigXml)
                .when()
                .post("/createItem")
                .then()
                .statusCode(200);

        Response getResponse = RestAssured.given()
                .auth().preemptive().basic(userName, apiToken)
                .baseUri(jenkinsUrl)
                .when()
                .get("job/%s/api/json".formatted(folderName))
                .then()
                .extract().response();

        Assert.assertEquals(getResponse.statusCode(), 200);
        Assert.assertEquals(getResponse.jsonPath().getString("name"), folderName);

        RestAssured.given()
                .auth().preemptive().basic(userName, apiToken)
                .baseUri(jenkinsUrl)
                .when()
                .post("job/%s/doDelete".formatted(folderName))
                .then()
                .statusCode(302);

        Response deletedResponse = RestAssured.given()
                .auth().preemptive().basic(userName, apiToken)
                .baseUri(jenkinsUrl)
                .when()
                .get("job/%s/api/json".formatted(folderName))
                .then()
                .extract().response();

        Assert.assertEquals(deletedResponse.statusCode(), 404,
                "Expected 404 after deletion, but got " + deletedResponse.statusCode());
    }

}
