package school.redrover;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class APIJenkinsTest {

    private String jenkinsUrl;
    private String userName;
    private String apiToken;

    @BeforeClass
    public void setUp() {
        Properties props = new Properties();

        try (InputStream is = getClass().getClassLoader().getResourceAsStream(".properties")) {
            if (is != null) {
                props.load(is);
            }
        } catch (IOException e) {
            System.err.println("Failed to load .properties (may be running in CI): " + e.getMessage());
        }


        String host = System.getenv("JENKINS_HOST");
        String port = System.getenv("JENKINS_PORT");
        if (host == null) host = props.getProperty("jenkins.host", "localhost");
        if (port == null) port = props.getProperty("jenkins.port", "8080");
        jenkinsUrl = "http://" + host + ":" + port;


        userName = System.getenv("JENKINS_USERNAME");
        if (userName == null) {
            userName = props.getProperty("jenkins.username");
        }

        apiToken = System.getenv("JENKINS_API_TOKEN");
        if (apiToken == null) {
            apiToken = props.getProperty("jenkins.api.token");
        }


        if (userName == null || apiToken == null) {
            throw new RuntimeException("Missing jenkins.username or jenkins.api.token in .properties or env vars");
        }
    }

    @Test
    public void jenkinsTest() {
        RestAssured.given()
                .log().all()// ← добавил что бы смотреть, что отправляю и что получаю(в конце лог)
                .when()
                .auth()
                .preemptive()
                .basic(userName, apiToken)
                .when()
                .get(jenkinsUrl + "/api/json")
                .then()
                .log().all()
                .statusCode(200);
    }
}
