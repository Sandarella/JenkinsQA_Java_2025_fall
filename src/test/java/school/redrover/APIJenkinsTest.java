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

}
