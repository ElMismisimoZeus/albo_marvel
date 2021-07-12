package mx.albo;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import mx.albo.controller.CharacterController;
import mx.albo.responses.CharacterResponse;

/*
 * The service "characters" exposes information about 
 * heroes with which ironman or captein america (depens of method invocation)
 * has interacted
 */
@Path("/characters")
public class Character {

	/**
     * Method handling HTTP GET request to iron method
     *
     * @return CharacterResponse that will be returned as a APPLICATION_JSON response.
     */
	 @GET
	 @Path("/ironman")
	 @Produces({MediaType.APPLICATION_JSON})
	 public CharacterResponse ironman() {
		 CharacterController objCC = new CharacterController();
		 CharacterResponse response = objCC.getColaborators("1009368");
		 return response;
	 }
	 
	 /**
	     *  Method handling HTTP GET request to captein america
	     *
	     * @return CharacterResponse that will be returned as a APPLICATION_JSON response.
	     */
	 @GET
	 @Path("/capamerica")
	 @Produces({MediaType.APPLICATION_JSON})
	 public CharacterResponse capamerica() {
		 CharacterController objCC = new CharacterController();
		 CharacterResponse response = objCC.getColaborators("1009220");
		 return response;
	 }
}
