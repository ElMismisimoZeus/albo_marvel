package mx.albo;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import mx.albo.controller.ColaboratorController;
import mx.albo.responses.ColaboratorsResponse;

/*
 * The service "colaborators" exposes information about writers, editors and colorists
 * related to ironman or captein america depens of method invocation
 */
@Path("/colaborators")
public class Colaborator {

	/**
     * Method handling HTTP GET request to iron method
     *
     * @return ColaboratorsResponse that will be returned as a APPLICATION_JSON response.
     */
	 @GET
	 @Path("/ironman")
	 @Produces({MediaType.APPLICATION_JSON})
	 public ColaboratorsResponse ironman() {
		 ColaboratorController objCC = new ColaboratorController();
		 ColaboratorsResponse response = objCC.getColaborators("1009368");
		 return response;
	 }
	 
	 
	 /**
	     *  Method handling HTTP GET request to captein america
	     *
	     * @return ColaboratorsResponse that will be returned as a APPLICATION_JSON response.
	     */
	 @GET
	 @Path("/capamerica")
	 @Produces({MediaType.APPLICATION_JSON})
	 public ColaboratorsResponse capamerica() {
		 ColaboratorController objCC = new ColaboratorController();
		 ColaboratorsResponse response = objCC.getColaborators("1009220");
		 return response;
	 }
}
