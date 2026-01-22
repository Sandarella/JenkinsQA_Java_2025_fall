package school.redrover.common;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public abstract class APIBaseTest {

    protected static String jenkinsUrl;
    protected static String userName;
    protected static String apiToken;
    protected static String tokenUuid;

    @BeforeClass
    public static void setUpApi() {
        jenkinsUrl = ProjectUtils.getUrl();
        userName = ProjectUtils.getUserName();

        JenkinsUtils.ApiToken token = JenkinsUtils.generateApiToken("api-test-token-" + System.currentTimeMillis());
        apiToken = token.value;
        tokenUuid = token.uuid;
    }

    @AfterClass
    public static void tearDownApi() {
        if (!ProjectUtils.isRunCI()) {
            JenkinsUtils.deleteApiTokenByUuid(jenkinsUrl, tokenUuid, apiToken);
        }
    }
}
