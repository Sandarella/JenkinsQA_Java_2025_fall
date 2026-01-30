package school.redrover;

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
    public void testCreateFreestyleProjectWithValidName() {
        Map<String, String> projectName = new HashMap<>();
        projectName.put("name", "FreestyleProjectName");

        String bodyFreestyleProjectXML = """
                <project>
                    <keepDependencies>false</keepDependencies>
                    <properties/>
                    <scm class="hudson.scm.NullSCM"/>
                    <canRoam>false</canRoam>
                    <disabled>false</disabled>
                    <blockBuildWhenDownstreamBuilding>false</blockBuildWhenDownstreamBuilding>
                    <blockBuildWhenUpstreamBuilding>false</blockBuildWhenUpstreamBuilding>
                    <triggers/>
                    <concurrentBuild>false</concurrentBuild>
                    <builders/>
                    <publishers/>
                    <buildWrappers/>
                </project>
                """;

        RestAssured.given()
                .log().all()
                .auth().preemptive().basic(userName, apiToken)
                .baseUri(jenkinsUrl)
                .contentType(ContentType.XML)
                .queryParams(projectName)
                .body(bodyFreestyleProjectXML)
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
                .get("job/%s/api/json".formatted(projectName.get("name")))
                .then()
                .log().all()
                .extract().response();

        String actualProjectName = response.jsonPath().getString("name");
        Assert.assertEquals(actualProjectName, projectName.get("name"));

        Integer statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
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
