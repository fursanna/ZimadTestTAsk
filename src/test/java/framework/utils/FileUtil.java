package framework.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FileUtil {
    public static String readFile(String filepath) {
        StringBuffer buffer = null;
        try {
            File file = new File(filepath);
            FileInputStream inputStream = new FileInputStream(file);

            int i;
            buffer = new StringBuffer("");
            while ((i = inputStream.read()) != -1) {
                buffer.append((char) i);
            }
            inputStream.close();
        } catch (IOException e) {
            AllureLogger.logWarning("Can't read file " + filepath);
            e.printStackTrace();
        }
        assert buffer != null;
        return buffer.toString();
    }
}