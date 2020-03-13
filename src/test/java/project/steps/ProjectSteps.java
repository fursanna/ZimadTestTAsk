package project.steps;

import io.qameta.allure.Step;
import project.models.ProjectModel;
import project.request.TodoistProject;

public class ProjectSteps {
    @Step("Create New Project")
    public static ProjectModel createDefaultProject() {
        ProjectModel projectModelRequest = new ProjectModel().getDefaultProject();
        Long projectId = TodoistProject.createNewProject(projectModelRequest.getProjectName());

        return new ProjectModel(projectId, projectModelRequest.getProjectName());
    }
}
