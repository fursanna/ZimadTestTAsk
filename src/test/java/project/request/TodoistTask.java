package project.request;

import framework.http.Requests;
import framework.models.FullResponse;
import org.apache.http.Header;
import org.apache.http.HttpHeaders;
import org.apache.http.message.BasicHeader;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static framework.utils.ConfigProvider.*;

public class TodoistTask {

    public static List<Header> getDefaultHeaders() {
        List<Header> defaultHeaders = new ArrayList<>();
        defaultHeaders.add(new BasicHeader(HttpHeaders.AUTHORIZATION, getPersonalToken()));
        defaultHeaders.add(new BasicHeader(HttpHeaders.CONTENT_TYPE, "application/json"));
        defaultHeaders.add(new BasicHeader("X-Request-Id", String.valueOf(UUID.randomUUID())));
        return defaultHeaders;
    }

    public static FullResponse createNewTaskWithDefaultHeaders(String jsonBody) {
        return createNewTask(jsonBody, getDefaultHeaders());
    }

    public static FullResponse createNewTask(String jsonBody, List<Header> headers) {
        return Requests.sendPostRequestWithJsonBody(getCreateNewTaskUrl(), headers, jsonBody);
    }

    public static void deleteTaskById(Long taskId) {
        List<Header> headers = new ArrayList<>();
        headers.add(new BasicHeader(HttpHeaders.AUTHORIZATION, getPersonalToken()));
        Requests.sendDeleteRequest(String.format(getDeleteTaskUrl(), taskId), headers);
    }
}
