package mx.albo.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import mx.albo.client.MarvelClient;
import mx.albo.responses.CharacterItem;
import mx.albo.responses.CharacterResponse;

public class CharacterController {
	
	public CharacterResponse getColaborators(String chraracterId) {
		
		List<CharacterItem> characters;
		CharacterItem newCharacter;
		List<String> comics;
		
		CharacterResponse objCR = new CharacterResponse();
		MarvelClient objMC = new  MarvelClient();
		String output;
		
		characters = objCR.getCharacters();
		
		boolean existCharacter=false;
		
		
		try {
			output = objMC.makeRequest("characters/"+chraracterId+"/comics");
			JSONParser parser = new JSONParser();  
	        JSONObject json = (JSONObject) parser.parse(output); 
	        
	        JSONObject jsonData = (JSONObject) json.get("data");
	        JSONArray jsonResults = (JSONArray) jsonData.get("results");
	        
	        
	     
	        
	        for(Object o: jsonResults){
	            if ( o instanceof JSONObject ) {
	            	
	            	
	            	
	            	JSONObject jsonCreators = (JSONObject)((JSONObject)o).get("characters") ;
	            	JSONArray jsonItems = (JSONArray) jsonCreators.get("items");
	            	
	            	for(Object o2: jsonItems){
	    	            if ( o2 instanceof JSONObject ) {
	    	            	
	    	            	// looking for an element, if doesn't exist add new one
	    	            	for(CharacterItem item : characters) {
	    	            		if(((JSONObject)o2).get("name").equals(item.getCharacter())) {
	    	            			existCharacter = true;
	    	            			comics = item.getComics();
	    	            			comics.add(((JSONObject)o).get("title").toString());
	    	            			item.setComics(comics);
		    	            	}
	    	            		
	    	            	}
	    	            	
	    	            	if(!existCharacter){
	    	            		newCharacter = new CharacterItem(((JSONObject)o2).get("name").toString());
	    	            		comics = newCharacter.getComics();
	    	            		comics.add(((JSONObject)o).get("title").toString());
	    	            		newCharacter.setComics(comics);
	    	            		characters.add(newCharacter);
	    	            	}
	    	            	
	    	            	
	    	            	
	    	            }
	    	        }
	            	
	            }
	        }
			
			
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
		objCR.setCharacters(characters);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();  
		objCR.setLast_sync(dtf.format(now));
		
	    return objCR;
	}

}
