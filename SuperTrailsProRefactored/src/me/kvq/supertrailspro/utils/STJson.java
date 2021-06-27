package me.kvq.supertrailspro.utils;

import java.util.List;
import java.util.function.Consumer;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

@SuppressWarnings("unchecked")
public class STJson {
	
	private JSONObject obj;
	
	public STJson(String json) throws ParseException {
		JSONParser parser = new JSONParser();
		obj = (JSONObject) parser.parse(json);
	}
	
	public STJson(JSONObject obj) {
		this.obj = obj;
	}
	
	public STJson() {
		this.obj = new JSONObject();
	}
	
	public int readInt(String key) {
		int result = 0;
		Long l = (Long) obj.get(key);
		if (l!=null) result = l.intValue();
		return result;
	}
	
	public STJson readInt(String key,Consumer<Integer> consumer) {
		consumer.accept(readInt(key));
		return this;
	}
	
	public String readString(String key) {
		String pattern = (String) obj.get(key);
		return pattern;
	}
	
	public STJson readString(String key,Consumer<String> consumer) {
		String pattern = readString(key);
		if (pattern!=null) consumer.accept(pattern);
		return this;
	}
	
	public STJson getSubJson(String key) {
		JSONObject sub = (JSONObject) obj.get(key);
		if (sub!=null) return new STJson(sub);
		return null;
	}
	
	public STJson getSubJson(String key,Consumer<STJson> consumer) { 
		STJson sub = getSubJson(key);
		if (sub!=null)consumer.accept(sub);
		return this;
	}
	

	public void setSubJson(String key,STJson sub) {
		obj.put(key, sub.obj);
	}
	
	public void setInt(String key,int value) {
		obj.put(key,value);
	}
	public void setString(String key,String value) {
		obj.put(key, value);
	}
	
	public void setList(String key, List<?> array) {
		JSONArray jsarray = new JSONArray();
		jsarray.addAll(array);
		obj.put(key, jsarray);
	}
	
	public List<?> getList(String key) {
		JSONArray jsarray = (JSONArray) obj.get(key);
		return jsarray;	
	}
	
	public STJson getList(String key, Consumer<List<?>> consumer) {
		List<?> list = getList(key);
		if (list!=null) consumer.accept(list);
		return this;
	}
	
	public String toString() {
		return obj.toJSONString();
	}
	
}
