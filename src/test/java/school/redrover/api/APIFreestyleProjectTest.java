package school.redrover.api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.APIBaseTest;

import java.util.HashMap;
import java.util.Map;

public class APIFreestyleProjectTest extends APIBaseTest {

    private static final String PROJECT_NAME = "FreestyleProject";
    private static final String NEW_PROJECT_NAME = "NewFreestyleProject";

    private static final String bodyXML = """
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

    @Test
    public void testCreateWithValidName() {
        Map<String, String> projectName = new HashMap<>();
        projectName.put("name", PROJECT_NAME);

        RestAssured.given()
                .log().all()
                .auth().preemptive().basic(userName, apiToken)
                .baseUri(jenkinsUrl)
                .contentType(ContentType.XML)
                .queryParams(projectName)
                .body(bodyXML)
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
    public void testRenameProject() {
        Map<String, String> projectName = new HashMap<>();
        projectName.put("name", PROJECT_NAME);

        RestAssured.given()
                .log().all()
                .auth().preemptive().basic(userName, apiToken)
                .baseUri(jenkinsUrl)
                .contentType(ContentType.XML)
                .queryParams(projectName)
                .body(bodyXML)
                .when()
                .post("/createItem")
                .then()
                .log().all()
                .statusCode(200);

        RestAssured.given()
                .log().all()
                .auth().preemptive().basic(userName, apiToken)
                .baseUri(jenkinsUrl)
                .contentType("application/x-www-form-urlencoded")
                .queryParam("newName", NEW_PROJECT_NAME)
                .when()
                .post("job/%s/confirmRename".formatted(projectName.get("name")))
                .then()
                .log().all()
                .statusCode(302);

        Response response = RestAssured.given()
                .log().all()
                .auth().preemptive().basic(userName, apiToken)
                .baseUri(jenkinsUrl)
                .when()
                .get("job/%s/api/json".formatted(NEW_PROJECT_NAME))
                .then()
                .log().all()
                .statusCode(200)
                .extract().response();

        String actualProjectName = response.jsonPath().getString("name");
        Assert.assertEquals(actualProjectName, NEW_PROJECT_NAME);
    }
}
