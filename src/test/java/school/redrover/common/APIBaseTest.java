package school.redrover.common;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public abstract class APIBaseTest {

    protected static String jenkinsUrl;
    protected static String userName;
    protected static String apiToken;
    protected static String tokenUuid;

    @BeforeClass
    protected void setUpApi() {
        ProjectUtils.log("Generate API token for tests");
        jenkinsUrl = ProjectUtils.getUrl();
        userName = ProjectUtils.getUserName();

        JenkinsUtils.ApiToken token = JenkinsUtils.generateApiToken("api-test-token-" + System.currentTimeMillis());
        apiToken = token.value;
        tokenUuid = token.uuid;
    }

    @AfterClass
    protected void tearDownApi() {
        if (!ProjectUtils.isRunCI()) {
            try {
                ProjectUtils.log("Delete API token");
                JenkinsUtils.deleteApiTokenByUuid(jenkinsUrl, tokenUuid, apiToken);
            } catch (Exception e) {
                ProjectUtils.log("Failed to delete API token: " + e.getMessage());
            }
        }
    }
}
