package framework.utils;

import io.qameta.allure.Allure;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

public class AllureLogger {
    public static void logWarning(String message) {
        Allure.addAttachment("Warning", String.format("%s: %s", new Date(), message));
    }

    public static void logJsonValidationResult(String message) {
        Allure.addAttachment("Schema Validation result", message);
    }

    public static void addHttpRequestLogs(HttpEntityEnclosingRequestBase httpRequest) {
        String body = null;
        try {
            body = httpRequest.getEntity() == null ? "Empty" : EntityUtils.toString(httpRequest.getEntity(), "UTF-8");
        } catch (IOException e) {
            Allure.addAttachment("Warning: ", "Can't read request body");
            e.printStackTrace();
        }
        String message = String.format("Url: %s\n\nHeaders: \n%s\n\nBody: \n%s",
                httpRequest.getURI(),
                Arrays.toString(httpRequest.getAllHeaders()),
                body);
        Allure.addAttachment("Request " + httpRequest.getURI(), message);
    }

    public static void addHttpDeleteLogs(HttpDelete httpDeleteRequest) {
        String message = String.format("Url: %s\n\nHeaders: \n%s\n",
                httpDeleteRequest.getURI(),
                Arrays.toString(httpDeleteRequest.getAllHeaders()));
        Allure.addAttachment("Request " + httpDeleteRequest.getURI(), message);
    }

    public static void addHttpResponseLogs(HttpResponse response, String body) {
        String message = String.format("Status Code: %s\n\nHeaders: \n%s\n\nBody: \n%s",
                response.getStatusLine().getStatusCode(),
                Arrays.toString(response.getAllHeaders()),
                body);
        Allure.addAttachment("Response", message);
    }
}
