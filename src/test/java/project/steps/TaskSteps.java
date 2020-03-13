package project.steps;

import framework.models.FullResponse;
import io.qameta.allure.Step;
import project.models.TaskModel;
import project.request.TodoistTask;

public class TaskSteps {
    @Step("Create New Task")
    public static FullResponse createNewTask(TaskModel taskmodel) {
        return TodoistTask.createNewTaskWithDefaultHeaders(taskmodel.getJsonString());
    }
}
