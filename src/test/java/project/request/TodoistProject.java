package project.request;

import framework.http.Requests;
import framework.models.FullResponse;
import framework.utils.JsonBuilder;
import org.apache.http.Header;
import org.apache.http.HttpHeaders;
import org.apache.http.message.BasicHeader;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static framework.utils.ConfigProvider.*;

public class TodoistProject {
    public static FullResponse createNewProjectBase(String jsonBody) {
        List<Header> headers = new ArrayList<>();
        headers.add(new BasicHeader(HttpHeaders.AUTHORIZATION, getPersonalToken()));
        headers.add(new BasicHeader(HttpHeaders.CONTENT_TYPE, "application/json"));
        headers.add(new BasicHeader("X-Request-Id", String.valueOf(UUID.randomUUID())));
        return Requests.sendPostRequestWithJsonBody(getCreateNewProjectUrl(), headers, jsonBody);
    }

    public static Long createNewProject(String name) {
        JsonBuilder jsonBuilder = new JsonBuilder();
        jsonBuilder.putStringInRoot("name", name);
        FullResponse fullResponse = createNewProjectBase(jsonBuilder.getJsonString());
        String responseString = fullResponse.getBodyJson();
        return Long.parseLong(JsonBuilder.convertStringToJson(responseString).get("id").toString());
    }

    public static void deleteProjectById(Long projectId) {
        List<Header> headers = new ArrayList<>();
        headers.add(new BasicHeader(HttpHeaders.AUTHORIZATION, getPersonalToken()));
        Requests.sendDeleteRequest(String.format(getDeleteProjectUrl(), projectId), headers);
    }
}
