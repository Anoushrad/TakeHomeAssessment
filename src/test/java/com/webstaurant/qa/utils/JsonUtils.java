package com.webstaurant.qa.utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.LinkedHashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JsonUtils {
	
	public static void main(String[] args) {
		JsonUtils obj = new JsonUtils();
		obj.loadJsonFileDataToMap(Constants.ENV_FILE_PATH, "prod");
	}
	private Map<String, Object> envMap;
	public void loadJsonFileDataToMap(String filePath, String filterBy) {
		try {
		 Map<String, Map<String, Object>> envDataMaps;

			JsonObject jsonObject = JsonParser.parseReader(new FileReader(filePath)).getAsJsonObject();
			Gson gson = new Gson();
			envDataMaps = gson.fromJson(jsonObject, Map.class);
			envMap = new LinkedHashMap<>();

			for (Map.Entry<String, Map<String, Object>> entry : envDataMaps.entrySet()) {
	 
				String key = entry.getKey();

				if( key.equals(filterBy)) {
					envMap = entry.getValue();
				}
			}
			System.out.println(envMap);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			String erroMessage = "Error while loading JSON file: " + e.getMessage();
			System.out.println(erroMessage);
		}
	}

	public String getEnvMap(String key) {
		return envMap.get(key).toString();
	}
}
