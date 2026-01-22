package school.redrover;

import io.restassured.RestAssured;
import org.testng.annotations.Test;
import school.redrover.common.APIBaseTest;

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
}
