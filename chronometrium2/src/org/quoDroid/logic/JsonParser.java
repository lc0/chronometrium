package org.quoDroid.logic;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class JsonParser{
	private JSONObject jObject;
	
	private User currentUser;
	
	public JsonParser(User user) {
		this.currentUser = user;
	}

		
	public ArrayList<ActivityP> getCategories() throws Exception {
		String fromWeb;		
		fromWeb = getApiContent();
		return parse(fromWeb);
	}
	
	private ArrayList<ActivityP> parse(String str) throws Exception {
		jObject = new JSONObject(str);
		ArrayList<ActivityP> actList = new ArrayList<ActivityP>();
		
		JSONArray arr = jObject.getJSONArray("categories");
		for (int i=0; i< arr.length(); i++)	{
			String name = arr.getJSONObject(i).getString("Name");
			String desc = arr.getJSONObject(i).getString("Description");
			double radius = arr.getJSONObject(i).getDouble("Radius");
			int id = arr.getJSONObject(i).getInt("ID");			
			double coordX = arr.getJSONObject(i).getDouble("PlaceX");			
			double coordY = arr.getJSONObject(i).getDouble("PlaceY");
			int uid = arr.getJSONObject(i).getInt("UserID");	
			//String parentId = arr.getJSONObject(i).getString("ParentID");
					
			actList.add(new ActivityP(coordX, coordY, radius, name, desc, id, uid));
			
		}
				
		return actList;
			
		
	}
	
	private String getApiContent() {
		URL yahoo;
		BufferedReader in;
		StringBuilder urlContent = new StringBuilder();
		String tmp;
		
		String urlStr = "http://chronometrium.apphb.com/Api/GetCategory?userID=" + currentUser.getUid();
		
		try {
			yahoo = new URL(urlStr);
			in = new BufferedReader(new InputStreamReader(yahoo.openStream()));
			while ((tmp = in.readLine()) != null) {
				urlContent.append(tmp);
			}
			in.close();
			
			return urlContent.toString();
		} catch (Exception e1){
			e1.printStackTrace();
		}
		return null;
	}
}