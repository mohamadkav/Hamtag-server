package net.hamtag.server.api.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import net.hamtag.server.api.request.types.corporations.panel.GetAllCorporationInfoRequest;

@Path("/corporations/")
public class CorpoationResource {
	@POST
	@Path("/get/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllVisibleCorporations(){
		return new GetAllCorporationInfoRequest().handle();
	}
}
