package net.hamtag.server.api.resource;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import net.hamtag.server.api.request.types.ads.GetAdContentRequest;
import net.hamtag.server.api.request.types.ads.GetAdsByCategoryForDeviceRequest;

@Path("/ads/")
public class AdResource {
	
	@POST
	@Path("/get")
	@Produces(MediaType.APPLICATION_JSON)
	public String getAdsByCategory(@QueryParam("number") String number,@QueryParam("categories") String categories,
			@QueryParam("last-update-time") String lastUpdateTime){
		return new GetAdsByCategoryForDeviceRequest(number, categories, lastUpdateTime).getHandler().handle().toString();
	}
	
	@POST
	@Path("/content/{id}")
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	public javax.ws.rs.core.Response getAdContents(@PathParam("id") String id){
		return new GetAdContentRequest(id).handle();
	}
}
