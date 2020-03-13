package project.models;

import com.fasterxml.jackson.databind.JsonNode;
import framework.utils.DateTimeProvider;
import framework.utils.JsonBuilder;
import project.enums.DateTimeFormats;

import java.util.Date;
import java.util.Objects;

public class TaskModel {
    private Long taskId;
    private String content;
    private Long projectId;
    private Integer priority;
    private String dueDate;
    private String dueString;
    private String dueLang;
    private JsonBuilder jsonBuilder = new JsonBuilder();

    public TaskModel() {}

    public TaskModel(String jsonString) {
        JsonNode responseBodyJson = JsonBuilder.convertStringToJson(jsonString);
        if (responseBodyJson.get("id") != null) { setTaskId(Long.valueOf(responseBodyJson.get("id").toString())); }
        if (responseBodyJson.get("project_id") != null) { setProjectId(Long.valueOf(responseBodyJson.get("project_id").toString())); }
        if (responseBodyJson.get("priority") != null) { setPriority(Integer.valueOf(responseBodyJson.get("priority").toString())); }
        if (responseBodyJson.get("content") != null) { setContent(String.valueOf(responseBodyJson.get("content")).replace("\"", "")); }
    }

    public TaskModel getDefaultModel() {
        this.setRandomContent();
        return this;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setRandomContent() {
        setContent("Test Content " + new Date());
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(int offset) {
        this.dueDate = DateTimeProvider.getDateTimeByFormatAndOffset(DateTimeFormats.YYYY_MM_DD, offset);
    }

    public String getDueString() {
        return dueString;
    }

    public void setDueString(String dueString) {
        this.dueString = dueString;
    }

    public String getDueLang() {
        return dueLang;
    }

    public void setDueLang(String dueLang) {
        this.dueLang = dueLang;
    }

    public JsonNode getJsonObject() {
        if (this.getContent() != null) { jsonBuilder.putStringInRoot("content", this.getContent()); }
        if (this.getProjectId() != null) { jsonBuilder.putLongInRoot("project_id", this.getProjectId()); }
        if (this.getPriority() != null) { jsonBuilder.putIntInRoot("priority", this.getPriority()); }
        if (this.getDueDate() != null) { jsonBuilder.putStringInRoot("due_date", this.getDueDate()); }
        if (this.getDueString() != null) { jsonBuilder.putStringInRoot("due_string", this.getDueString()); }
        if (this.getDueLang() != null) { jsonBuilder.putStringInRoot("due_lang", this.getDueLang()); }
        return jsonBuilder.getJsonObj();
    }

    public String getJsonString() {
        this.getJsonObject();
        return jsonBuilder.getJsonString();
    }

    @Override
    public String toString() {
        return getJsonString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskModel taskModel = (TaskModel) o;
        return Objects.equals(content, taskModel.content) &&
                projectId == null || Objects.equals(projectId, taskModel.projectId) &&
                priority == null || Objects.equals(priority, taskModel.priority);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskId, content, projectId, priority, dueDate, dueString, dueLang);
    }
}
