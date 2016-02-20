package net.hamtag.server.api.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import net.hamtag.server.api.request.types.contentproviders.panel.AddNewProviderRequest;
import net.hamtag.server.api.request.types.contentproviders.panel.GetAllProviderInfoRequest;

@Path("/content-providers-panel/")
public class ContentProviderResource {
	@POST
	@Path("/all/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProviderInfo(@QueryParam("token") String token,
			@QueryParam("phone-number") String phoneNumber){
		return new GetAllProviderInfoRequest().handle();
	}
/*	Example Json:
	{
	    "providername" : "hamshahri",
	    "categories" : [
	        "FILM","SPORTS"]
	    
	}*/
	@POST
	@Path("/add/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addNewProvider(String requestJson){
		return new AddNewProviderRequest(requestJson).handle();
	}
}
