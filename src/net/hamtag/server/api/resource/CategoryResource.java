package net.hamtag.server.api.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import net.hamtag.server.api.request.types.categories.panel.GetAllCategoriesRequest;

@Path("/categories/")
public class CategoryResource {
	@POST
	@Path("/all/")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getAllCategories(){
		return new GetAllCategoriesRequest().handle();
	}
}
