package school.redrover.common;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public abstract class APIBaseTest {

    protected String jenkinsUrl;
    protected String userName;
    protected String apiToken;
    protected String tokenUuid;

    @BeforeClass
    protected void setUpApi() {
        ProjectUtils.log("Generate API token for tests");
        jenkinsUrl = ProjectUtils.getUrl();
        userName = ProjectUtils.getUserName();

        JenkinsUtils.ApiToken token = JenkinsUtils.generateApiToken("api-test-token-" + System.currentTimeMillis());
        apiToken = token.value;
        tokenUuid = token.uuid;
    }

    @BeforeMethod
    protected void beforeMethod() {
        ProjectUtils.log("Clear data");
        JenkinsUtils.clearData();
    }

    @AfterClass
    protected void tearDownApi() {
        ProjectUtils.log("Delete API token");
        JenkinsUtils.deleteApiTokenByUuid(jenkinsUrl, tokenUuid, apiToken);
    }
}
