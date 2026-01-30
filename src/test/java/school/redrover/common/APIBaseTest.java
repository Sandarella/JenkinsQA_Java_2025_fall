package school.redrover.common;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;

public abstract class APIBaseTest {

    protected String jenkinsUrl;
    protected String userName;
    protected String apiToken;
    protected String tokenUuid;

    @BeforeClass
    protected void setUpApi() {
        Log.info("Generate API token for tests");
        jenkinsUrl = ProjectUtils.getUrl();
        userName = ProjectUtils.getUserName();

        JenkinsUtils.ApiToken token = JenkinsUtils.generateApiToken("api-test-token-" + System.currentTimeMillis());
        apiToken = token.value;
        tokenUuid = token.uuid;
    }

    @BeforeMethod
    protected void beforeMethod(Method method) {
        Log.info("Clear data");
        Log.info("Run %s.%s", this.getClass().getName(), method.getName());
        JenkinsUtils.clearData();
    }

    @AfterClass
    protected void tearDownApi() {
        Log.info("Delete API token");
        JenkinsUtils.deleteApiTokenByUuid(jenkinsUrl, tokenUuid, apiToken);
    }
}
