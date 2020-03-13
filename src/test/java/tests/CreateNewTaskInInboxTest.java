package tests;

import framework.enums.ResponseCode;
import framework.models.FullResponse;
import framework.utils.Store;
import io.qameta.allure.Step;
import org.junit.After;
import org.junit.Test;
import project.models.TaskModel;
import project.request.TodoistTask;
import project.steps.AssertionSteps;
import project.steps.TaskSteps;

public class CreateNewTaskInInboxTest {
    @Test
    public void testCreateNewTaskInInboxWithRequiredParams() {
        TaskModel requestTaskModel = new TaskModel();
        requestTaskModel.setRandomContent();

        FullResponse fullResponse = TaskSteps.createNewTask(requestTaskModel);
        TaskModel responseTaskModel = new TaskModel(fullResponse.getBodyJson());
        Store.put(this.getClass().getName(), responseTaskModel);

        AssertionSteps.assertResponse(fullResponse, ResponseCode.OK);
        AssertionSteps.assertValues(requestTaskModel, responseTaskModel);
    }

    @After
    @Step("Delete created task")
    public void testTearDown() {
        TodoistTask.deleteTaskById(Store.getTaskModel(this.getClass().getName()).getTaskId());
    }
}
