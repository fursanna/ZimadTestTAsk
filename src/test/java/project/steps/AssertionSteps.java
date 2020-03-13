package project.steps;

import com.fasterxml.jackson.databind.JsonNode;
import framework.enums.ResponseCode;
import framework.models.FullResponse;
import framework.utils.ConfigProvider;
import framework.utils.FileUtil;
import framework.utils.JsonBuilder;
import framework.utils.ValidatorJson;
import io.qameta.allure.Step;
import org.junit.Assert;
import project.models.TaskModel;

public class AssertionSteps {
    @Step("Assert Response")
    public static void assertResponse(FullResponse fullResponse, ResponseCode responseCode) {
        JsonNode responseBodyJson = JsonBuilder.convertStringToJson(fullResponse.getBodyJson());
        Assert.assertTrue(ValidatorJson.validate(responseBodyJson, JsonBuilder.convertStringToJson(FileUtil.readFile(ConfigProvider.getSchemaPathAddNewTask()))));
        Assert.assertEquals(fullResponse.getStatusCode(), responseCode.getCode());
    }

    @Step("Assert Values")
    public static void assertValues(TaskModel requestTaskModel, TaskModel responseTaskModel) {
        Assert.assertEquals(requestTaskModel, responseTaskModel);
    }
}
