package swing.grid.app.util;

import java.io.File;

public class FileUtil {

    public static File getFile(String fileName) {
        ClassLoader classLoader = FileUtil.class.getClassLoader();
        return new File(classLoader.getResource(fileName).getFile());
    }

}