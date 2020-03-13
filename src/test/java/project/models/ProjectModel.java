package project.models;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ProjectModel {
    private Long projectId;
    private String projectName;
    private Map<String, TaskModel> tasks = new HashMap<>();

    public ProjectModel() {}

    public ProjectModel(Long projectId, String projectName) {
        this.projectId = projectId;
        this.projectName = projectName;
    }

    public ProjectModel getDefaultProject() {
        this.setRandomProjectName();
        return this;
    }

    public void setRandomProjectName() {
        setProjectName("Project Name " + new Date());
    }

    public void putTask(String key, TaskModel taskModel) {
        tasks.put(key, taskModel);
    }

    public Long getProjectId() {
        return projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public Map<String, TaskModel> getTasks() {
        return tasks;
    }

    public TaskModel getTask(String key) {
        return tasks.get(key);
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
}
