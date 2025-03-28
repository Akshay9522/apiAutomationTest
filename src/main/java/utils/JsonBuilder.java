package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONObject;
import org.json.JSONTokener;

public class JsonBuilder {

	static JSONObject readJson(String path) throws Exception {
		File file = new File(path);
		FileInputStream fis = new FileInputStream(file);
		JSONTokener js = new JSONTokener(fis);
		JSONObject jsonobj = new JSONObject(js);
		return jsonobj;
	}
	
	static void updateJson(String name, String language, String address, String path) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("name", name); 
	    jsonObject.put("language", language);
	    jsonObject.put("address", address); 
	    try (FileWriter file = new FileWriter(path)) {
	        file.write(jsonObject.toString(4));
	        System.out.println("JSON file updated successfully!");
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
}
