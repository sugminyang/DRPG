package kr.ac.snu;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class PubTatorTest {

	public static void main(String[] args) {

	}

	
	public List<String> getPubTatorJsonParser(String paperJson) throws ParseException	{
		List<String> colored = new ArrayList<String>();
		JSONParser parser = new JSONParser();
		Object obj = parser.parse( paperJson );
		JSONArray jsonArray = (JSONArray) obj;
		
		for (int i = 0; i < jsonArray.size();i++) {
		    JSONObject data = (JSONObject)jsonArray.get(i);
		    String text = (String)data.get("text"); 
		    JSONArray pubtator = (JSONArray)data.get("denotations");
		    
		    for (int j = 0; j < pubtator.size();j++) {
		    	JSONObject entity = (JSONObject)pubtator.get(j);
		    	
		    	JSONObject span = (JSONObject)entity.get("span");
		    	Long begin = (Long)span.get("begin");
		    	Long end = (Long)span.get("end");
		    	String keyword = text.substring(Integer.parseInt(begin+""), Integer.parseInt(end+""));
		    	
		    	if(!colored.contains(keyword))	{
		    		colored.add(keyword);
		    	}
		    }
		}
		
		return colored;
	}
}
