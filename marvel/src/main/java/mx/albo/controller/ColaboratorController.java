package mx.albo.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import mx.albo.client.MarvelClient;
import mx.albo.responses.ColaboratorsResponse;

public class ColaboratorController {
	public ColaboratorsResponse getColaborators(String chraracterId) {
		
		Set<String> editors;
		Set<String> writes;
		Set<String> colorists;
		
		ColaboratorsResponse objCR = new ColaboratorsResponse();
		MarvelClient objMC = new  MarvelClient();
		String output;
		
		editors = objCR.getEditors();
		writes = objCR.getWrites();
		colorists = objCR.getColorists();
		
		try {
			output = objMC.makeRequest("characters/"+chraracterId+"/comics");
			JSONParser parser = new JSONParser();  
	        JSONObject json = (JSONObject) parser.parse(output); 
	        
	        JSONObject jsonData = (JSONObject) json.get("data");
	        JSONArray jsonResults = (JSONArray) jsonData.get("results");
	        
	        
	     
	        
	        for(Object o: jsonResults){
	            if ( o instanceof JSONObject ) {
	            	
	            	JSONObject jsonCreators = (JSONObject)((JSONObject)o).get("creators") ;
	            	
	            	JSONArray jsonItems = (JSONArray) jsonCreators.get("items");
	            	
	            	for(Object o2: jsonItems){
	    	            if ( o2 instanceof JSONObject ) {
	    	            	if(((JSONObject)o2).get("role").equals("editor")) {
	    	            		editors.add(((JSONObject)o2).get("name").toString());
	    	            	}
	    	            	
	    	            	if(((JSONObject)o2).get("role").equals("writer")) {
	    	            		writes.add(((JSONObject)o2).get("name").toString());
	    	            	}
	    	            	
	    	            	if(((JSONObject)o2).get("role").equals("colorist")) {
	    	            		colorists.add(((JSONObject)o2).get("name").toString());
	    	            	}
	    	            	
	    	            }
	    	        }
	            	
	            }
	        }
			
			
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
		objCR.setEditors(editors);
		objCR.setColorists(colorists);
	    objCR.setWrites(writes);
	    
	    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();  
		objCR.setLast_sync(dtf.format(now));
		
	    return objCR;
	}
	

}
