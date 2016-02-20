package net.hamtag.server.api.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import net.hamtag.server.api.request.types.contentproviders.GetContentProvidersByCategoryRequest;
import net.hamtag.server.api.request.types.device.android.GetAllProviderInfoRequest;

@Path("/content-providers/")
public class ContentProviderResourceAndroid {
	@POST
	@Path("/sync/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProviderInfo(@QueryParam("token") String token,
			@QueryParam("phone-number") String phoneNumber){
		return new GetAllProviderInfoRequest(phoneNumber, token).handle() ;
	}
	@POST
	@Path("/category/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProvidersByCategory(@QueryParam("token") String token,
			@QueryParam("phone-number") String phoneNumber,@QueryParam("category-id") String categoryId){
		return new GetContentProvidersByCategoryRequest(phoneNumber, token, categoryId).handle();
	}
}
