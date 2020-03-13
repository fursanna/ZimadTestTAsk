package framework.utils;

import project.models.ProjectModel;
import project.models.TaskModel;

import java.util.HashMap;
import java.util.Map;

public class Store {
    private static Map<String, Object> store = new HashMap<>();

    public static void put(String testKey, Object data) {
        store.put(testKey, data);
    }

    public static Object get(String testKey) {
        return store.get(testKey);
    }

    public static ProjectModel getProjectModel(String testKey) {
        return (ProjectModel) store.get(testKey);
    }

    public static TaskModel getTaskModel(String testKey) {
        return (TaskModel) store.get(testKey);
    }

    public static Map<String, Object> getMap(String testKey) {
        return (Map<String, Object>) store.get(testKey);
    }
}
