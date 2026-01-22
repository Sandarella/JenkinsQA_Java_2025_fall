package school.redrover.common;

import org.testng.annotations.BeforeClass;

public abstract class APIBaseTest {

    protected static String jenkinsUrl;
    protected static String userName;
    protected static String apiToken;

    @BeforeClass
    public static void setUpApi() {
        jenkinsUrl = ProjectUtils.getUrl();
        userName = ProjectUtils.getUserName();
        apiToken = JenkinsUtils.generateApiToken("api-test-token-" + System.currentTimeMillis());
    }
}
