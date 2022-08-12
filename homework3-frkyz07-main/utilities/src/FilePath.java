package utilities.src;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public abstract class FilePath {

    // we are creating one properties object to g,ve our properties
        public static Properties properties;
        static {
            // giving a file path as a variable
            String filePath = "C:\\Users\\kullanıcı\\IdeaProjects\\homework3-frkyz07_1\\data.properties";
            try{
                // we are giving our file to the method via a  variable
                FileInputStream fis = new FileInputStream(filePath);
                // creating a new properties object
                properties = new Properties();
                // we are giving that object the file path that we give already to the method
                properties.load(fis);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // w are creating a method to get our properties from our file
        public static String getProperty(String key){
            return properties.getProperty(key);
        }


}
