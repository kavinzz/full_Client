package config;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Properties;

public class ConfigPropMananger {
	
	private static ConfigPropMananger configManager;
	
	private String serverIP = "127.0.0.1";
	private int port = 16003;
	private int user = 0;
	
	private String version = "";
	
	public ConfigPropMananger(){
		
	}
	
	public static ConfigPropMananger getInstance(){
		if(configManager == null) configManager = new ConfigPropMananger();
		return configManager;
	}
	
	public void saveNamesConfig(String wathers,String doors){
		
		try {
			OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream("D:\\CTS\\source\\names.properties"),"UTF-8");
			Properties props = new Properties();
			props.setProperty("watcherNames", wathers);
			props.setProperty("doorNames", doors);
			props.store(out, "update");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String[] LoadNamesConfig() {
	
		try {
			Properties props = new Properties();
			InputStreamReader in = new InputStreamReader(new FileInputStream("D:\\CTS\\source\\names.properties"),"UTF-8");
			props.load(in);
			String yi = props.getProperty("一分监狱");
			String er = props.getProperty("二分监狱");
			String san = props.getProperty("三分监狱");
			String si = props.getProperty("四分监狱");
			String wu = props.getProperty("五分监狱");
			String liu = props.getProperty("中心医院");
			props.clear();
			props = null;
			in.close();
			in = null;
			
			return new String[]{yi,er,san,si,wu,liu};
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return null;
	}
	
	public boolean LoadServerConfig(){
		boolean isLoad = false;
		try {
			
			Properties props = new Properties();
			InputStream in = new FileInputStream("D:\\CTS\\source\\config.properties");
			props.load(in);
			serverIP = props.getProperty("ip");
			port = Integer.parseInt(props.getProperty("port"));
			user = Integer.parseInt(props.getProperty("user"));
			
			version = props.getProperty("version");
			props.clear();
			props = null;
			in.close();
			in = null;
			isLoad = true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isLoad;
	}
	
	public String getVersion(){
		return version;
	}
	
	public String getServerIP() {
		return serverIP;
	}
	
	public int getPort(){
		return port;
	}
	
	public int getUser(){
		return user;
	}

}
