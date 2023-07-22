package utilities;

import java.util.Properties;
import java.io.File;
import java.io.FileInputStream;
public class ConfigReader {
	

		Properties prop;
		
		public ConfigReader()
		{
			File src = new File("C:/Users/mural/eclipse-workspace/Restassured/src/test/resources/Config.properties");

			try {
				FileInputStream fis = new FileInputStream(src);
				prop = new Properties();
				prop.load(fis);
			} catch (Exception e) {
				System.out.println("Exception is " + e.getMessage());
			}
		}
}
