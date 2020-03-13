package tests;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import framework.enums.ResponseCode;
import framework.models.FullResponse;
import framework.utils.Store;
import io.qameta.allure.Step;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import project.enums.PriorityProvider;
import project.models.ProjectModel;
import project.models.TaskModel;
import project.request.TodoistProject;
import project.request.TodoistTask;
import project.steps.AssertionSteps;
import project.steps.ProjectSteps;
import project.steps.TaskSteps;

@RunWith(DataProviderRunner.class)
public class CreateNewTaskInProjectTest {
    private final String testModelStoreKey = this.getClass().getName() + "TaskModel";

    @BeforeClass
    @Step("Test Data preparation")
    public static void suiteSetUp() {
        Store.put(CreateNewTaskInProjectTest.class.getName(), ProjectSteps.createDefaultProject());
    }

    @Test
    @UseDataProvider("dataProviderAdd")
    public void testCreateNewTaskInProjectWithOptionalParams(TaskModel requestTaskModel) {
        FullResponse fullResponse = TaskSteps.createNewTask(requestTaskModel);
        TaskModel responseTaskModel = new TaskModel(fullResponse.getBodyJson());
        Store.put(testModelStoreKey, responseTaskModel);

        AssertionSteps.assertResponse(fullResponse, ResponseCode.OK);
        AssertionSteps.assertValues(requestTaskModel, responseTaskModel);
    }

    @After
    @Step("Delete created task")
    public void testTearDown() {
        TodoistTask.deleteTaskById(Store.getTaskModel(testModelStoreKey).getTaskId());
    }

    @AfterClass
    @Step("Delete created project")
    public static void suiteTearDown() {
        TodoistProject.deleteProjectById(Store.getProjectModel(CreateNewTaskInProjectTest.class.getName()).getProjectId());
    }

    @DataProvider
    public static Object[][] dataProviderAdd() {
        ProjectModel projectModel = Store.getProjectModel(CreateNewTaskInProjectTest.class.getName());

        TaskModel requestTaskModel = new TaskModel().getDefaultModel();
        requestTaskModel.setProjectId(projectModel.getProjectId());

        TaskModel requestTaskModelWithPriority = new TaskModel().getDefaultModel();
        requestTaskModelWithPriority.setProjectId(projectModel.getProjectId());
        requestTaskModelWithPriority.setPriority(PriorityProvider.PRIORITY_1.getValue());

        TaskModel requestTaskModelWithDueDate = new TaskModel().getDefaultModel();
        requestTaskModelWithDueDate.setProjectId(projectModel.getProjectId());
        requestTaskModelWithDueDate.setDueDate(7);

        return new Object[][]{
                {requestTaskModel},
                {requestTaskModelWithPriority},
                {requestTaskModelWithDueDate}
        };
    }
}
