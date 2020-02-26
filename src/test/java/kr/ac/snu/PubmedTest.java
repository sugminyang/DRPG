package kr.ac.snu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class PubmedTest {

	public static void main(String[] args) throws ParseException {
		String pmid = "2727283";
		String testPmid = "https://www.ebi.ac.uk/europepmc/webservices/rest/search?query=" + pmid + "8&resultType=core&pageSize=1&format=json";
		
		System.out.println(pmid);
//		System.out.println(getPubmedAbstract(testPmid));
		}
	
	public static String getPubmedAbstract(String request_url) throws ParseException {
		URL url = null;
		BufferedReader br = null;
		String jsonStr = "";
		String label = "";
		try {
			url = new URL(request_url);
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
	        con.setRequestMethod("GET");
	        
	        int responseCode = con.getResponseCode();
	        
	        if(responseCode==200) { // 정상 호출
	            br = new BufferedReader(new InputStreamReader(con.getInputStream()));
	        } else {  // 에러 발생
	            br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
	        }
	        
	        String inputLine;
	        StringBuffer response = new StringBuffer();
	        while ((inputLine = br.readLine()) != null) {
	            response.append(inputLine);
	        }
	        br.close();
	        
	        jsonStr = response.toString();
	        label = jsonStr;
//	        label = jsonParser(jsonStr);
	        
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return label;
	}
	
	public static String jsonParser(String paperJson) throws ParseException	{
		JSONParser parser = new JSONParser();
		Object obj = parser.parse( paperJson );
		JSONObject jsonObj = (JSONObject) obj;
		String meshTerm = "";
		
		JSONObject resultList = (JSONObject)jsonObj.get("results");
		JSONArray result = (JSONArray)resultList.get("bindings");
		
		for (int i = 0; i < result.size();i++) {
		    JSONObject data = (JSONObject)result.get(i);
		    JSONObject label = (JSONObject)data.get("label");
		    meshTerm = (String)label.get("value");
		}
		
		return meshTerm;
	}

}
