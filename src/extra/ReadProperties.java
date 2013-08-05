package extra;

import java.io.FileInputStream;
import java.util.Properties;

public class ReadProperties {
	
	public ReadProperties(){
	}
	
	public static void Read(String path){

		FileInputStream fis = null;
		try{
			Properties prop = new Properties();
			//prop.load(new FileInputStream("config.properties"));
			//fis = new FileInputStream("C:/Users/Quang Do/Selenium/ProjectWF/config.properties");
			fis = new FileInputStream(path);
			prop.load(fis);
			System.out.println("Browser Type is: " + prop.getProperty("BrowserType"));
		}
		catch(Exception e){
			System.out.println(e);
		}
		finally{
			try{
				fis.close();
			}
			catch(Exception e){}
		}
	}
	
}
