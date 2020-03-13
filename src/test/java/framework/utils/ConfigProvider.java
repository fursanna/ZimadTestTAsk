package framework.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigProvider {
    private static final String PROPERTIES_FILE_NAME = "config.properties";
    private static Properties PROPERTIES = null;

    private static void loadProperties() {
        if (PROPERTIES == null) {
            PROPERTIES = new Properties();
            InputStream inputStream = ConfigProvider.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE_NAME);

            try {
                PROPERTIES.load(inputStream);
            } catch (IOException e) {
                AllureLogger.logWarning(String.format("Can't read %s file", PROPERTIES_FILE_NAME));
                e.printStackTrace();
            }
        }
    }

    private static String getValueFromProperties(String propName) {
        loadProperties();
        return PROPERTIES.getProperty(propName);
    }

    public static String getHost() {
        return getValueFromProperties("api_end_point.host");
    }

    public static String getCreateNewTaskUrl() {
        return getHost() + getValueFromProperties("api_end_point.create_new_task_url");
    }

    public static String getCreateNewProjectUrl() {
        return getHost() + getValueFromProperties("api_end_point.create_new_project_url");
    }

    public static String getDeleteProjectUrl() {
        return getHost() + getValueFromProperties("api_end_point.delete_project_url");
    }

    public static String getDeleteTaskUrl() {
        return getHost() + getValueFromProperties("api_end_point.delete_task_url");
    }

    public static String getPersonalToken() {
        return "Bearer " + getValueFromProperties("user_data.personal_token");
    }

    public static String getSchemaPathAddNewTask() {
        return getValueFromProperties("schema_path.add_new_task_schema");
    }

}
