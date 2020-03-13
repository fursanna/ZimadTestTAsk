package framework.models;

import org.apache.http.HttpResponse;

public class FullResponse {
    private String bodyJson;
    private HttpResponse response;

    public FullResponse(String bodyJson, HttpResponse response) {
        this.bodyJson = bodyJson;
        this.response = response;
    }

    public FullResponse(HttpResponse response) {
        this.response = response;
    }

    public String getBodyJson() {
        return bodyJson;
    }

    public HttpResponse getResponse() {
        return response;
    }

    public int getStatusCode() {
        return response.getStatusLine().getStatusCode();
    }
}
