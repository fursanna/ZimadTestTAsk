package framework.http;

import framework.models.FullResponse;
import framework.utils.AllureLogger;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.List;

public class Requests {

    public static FullResponse sendPostRequestWithJsonBody(String uri, List<Header> headers, String jsonData) {
        HttpPost httpPost = new HttpPost(uri);
        headers.forEach(httpPost::addHeader);
        StringEntity entity = new StringEntity(jsonData, "UTF-8");
        httpPost.setEntity(entity);
        return sendRequest(httpPost);
    }

    public static FullResponse sendDeleteRequest(String uri, List<Header> headers) {
        HttpDelete httpDelete = new HttpDelete(uri);
        headers.forEach(httpDelete::addHeader);
        return sendRequest(httpDelete);
    }

    private static FullResponse sendRequest(HttpEntityEnclosingRequestBase httpRequest) {
        AllureLogger.addHttpRequestLogs(httpRequest);
        HttpResponse response = null;
        String body = null;
        try {
            response = HttpClients.createDefault().execute(httpRequest);
            body = EntityUtils.toString(response.getEntity(), "UTF-8");
            AllureLogger.addHttpResponseLogs(response, body);
        } catch (IOException e) {
            AllureLogger.logWarning("Can't read request body");
            e.printStackTrace();
        }
        return new FullResponse(body, response);
    }

    private static FullResponse sendRequest(HttpDelete httpDelete) {
        AllureLogger.addHttpDeleteLogs(httpDelete);
        HttpResponse response = null;
        try {
            response = HttpClients.createDefault().execute(httpDelete);
            String body = response.getEntity() == null ? "Empty" : EntityUtils.toString(response.getEntity(), "UTF-8");
            AllureLogger.addHttpResponseLogs(response, body);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new FullResponse(response);
    }
}
