package school.redrover.common.logging;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.restassured.RestAssured;
import io.restassured.config.LogConfig;
import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

import java.util.List;

public class RestAssuredLogFilter implements Filter {
    static {
        LogConfig logConfig = LogConfig.logConfig()
                .blacklistHeaders(List.of("Authorization", "Jenkins-Crumb"));
        RestAssured.config = RestAssured.config().logConfig(logConfig);
    }

    @Override
    public Response filter(FilterableRequestSpecification filterableRequestSpecification,
                           FilterableResponseSpecification filterableResponseSpecification,
                           FilterContext filterContext) {
        Response response = filterContext.next(filterableRequestSpecification, filterableResponseSpecification);
        maskCrumbInLogs(response);
        return response;
    }

    public void maskCrumbInLogs(Response response) {
        String responseBody = response.getBody().asString();

        if (response.getContentType() != null && response.getContentType().contains("application/json")) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                JsonNode rootNode = mapper.readTree(responseBody);

                if (rootNode.has("crumb") && rootNode instanceof ObjectNode) {
                    ((ObjectNode) rootNode).put("crumb", "******");
                }

                String pretty = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(rootNode);
                System.out.println("Response with masked crumb: " + pretty);

            } catch (Exception e) {
                System.out.println("Failed to parse response as JSON: " + responseBody);
            }
        } else {
            System.out.println("Response: " + responseBody);
        }
    }
}
