package mx.albo.client;

import java.io.IOException;

import org.apache.commons.codec.digest.DigestUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import us.monoid.web.Resty;

/*
 * The Class MarvelClient manage the marvel third party services request
 */
public class MarvelClient {
	
	/*
	 * The publicKey and privateKey was obtained from marvel to use  API
	 */
	private final String publicKey = "b2c5af13eb8c3d82dd48be35a0a3e331";
    private final String privateKey = "d5e95131923ee1afc4c30cc65c5df503a858c82c";
	
    
    /**
     * 
     * @param resource, string that contains specific value of method from marvel service
     * @return
     * @throws IOException
     * @throws ParseException
     */
	public String makeRequest(String resource)throws IOException, ParseException {
		String response="";
		
		long timeStamp = System.currentTimeMillis();
        int limit = 100;
		String stringToHash = timeStamp + privateKey + publicKey;
        String hash = DigestUtils.md5Hex(stringToHash);
        
		String url = String.format("http://gateway.marvel.com/v1/public/%s?ts=%d&apikey=%s&hash=%s&limit=%d&offset=500",resource, timeStamp, publicKey, hash, limit);
		response = new Resty().text(url).toString();
		
		return response;
	}
	
	/**
	 * The main method has been added to manage unit tests
	 * 
	 * @param args
	 * @throws IOException
	 * @throws ParseException
	 */
	public static void main(String[] args) throws IOException, ParseException {
		
		MarvelClient objMC = new  MarvelClient();
		
		String output = objMC.makeRequest("/characters/1010914");
        
        JSONParser parser = new JSONParser();  
        JSONObject json = (JSONObject) parser.parse(output); 
        
        JSONObject jsonData = (JSONObject) json.get("data");
        JSONArray jsonResults = (JSONArray) jsonData.get("results");
        
     
        System.out.println(jsonResults.size());
        
        for(Object o: jsonResults){
            if ( o instanceof JSONObject ) {
              System.out.println("nombre: "+ ((JSONObject)o).get("name") + "  id:"+ ((JSONObject)o).get("id"));
            }
        }
        
 

	}

}
