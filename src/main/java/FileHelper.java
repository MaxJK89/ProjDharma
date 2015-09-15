import java.io.File;

/**
 * Created by angboty on 9/15/2015.
 */
public class FileHelper {

    public static String FILE_DIRECTORY = "C:\\PROJECTDHARMA_TEMP_FILES\\";
    public static File fileDir;
    public static boolean doesDirectoryExist;

    public FileHelper() {
        fileDir = new File(FILE_DIRECTORY);

        // check to see if the file directory exists
        if (!fileDir.exists()) {
            // it doesn't so make the dir
            doesDirectoryExist = false;
            try {
                fileDir.mkdir();
                doesDirectoryExist = true;
            } catch (SecurityException ex) {
                ex.printStackTrace();
            }
        }
    }



}
