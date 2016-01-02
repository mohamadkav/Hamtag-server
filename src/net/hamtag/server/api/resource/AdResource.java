package net.hamtag.server.api.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import net.hamtag.server.api.request.types.AdInfoRequest;

@Path("/ads/")
public class AdResource {
	@GET
	@Path("/info/{ad-name}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getAdInfo(@PathParam("ad-name") String adName){
		AdInfoRequest req=new AdInfoRequest(adName);
		return req.getHandler().handle().toString();
	}
}
