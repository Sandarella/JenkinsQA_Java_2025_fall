package school.redrover;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.APIBaseTest;

import java.util.HashMap;
import java.util.Map;

public class APIJenkinsTest extends APIBaseTest {

    @Test
    public void jenkinsTest() {
        RestAssured.given()
                .log().all()// ← добавил что бы смотреть, что отправляю и что получаю(в конце лог)
                .when()
                .auth()
                .preemptive()
                .basic(userName, apiToken)
                .when()
                .get(jenkinsUrl + "api/json")
                .then()
                .log().all()
                .statusCode(200);
    }

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
    public void testCreateUser() {
        Map<String, String> userData = new HashMap<>();
        userData.put("username", "UserUser");
        userData.put("password1", "passwordUser");
        userData.put("password2", "passwordUser");
        userData.put("fullname", "UserUserName");
        userData.put("email", "user@user.us");

        Response response = RestAssured.given()
                .log().all()
                .auth().preemptive().basic(userName, apiToken)
                .baseUri(jenkinsUrl)
                .contentType(ContentType.XML)
                .queryParams(userData)
                .when()
                .post("securityRealm/createAccountByAdmin")
                .then()
                .log().all()
                .extract().response();

        String location = response.getHeader("Location");

        Response getResponse = RestAssured.given()
                .log().all()
                .auth().preemptive().basic(userName, apiToken)
                .baseUri(jenkinsUrl)
                .when()
                .get("%s".formatted(location))
                .then()
                .log().all()
                .extract().response();

        String responseBody = getResponse.getBody().asString();

        Assert.assertEquals(response.getStatusCode(), 302);
        Assert.assertEquals(getResponse.getStatusCode(), 200);
        Assert.assertTrue(responseBody.contains(userData.get("username")));
    }
}
