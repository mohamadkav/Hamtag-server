package net.hamtag.server.api.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import net.hamtag.server.api.request.types.news.panel.AddNewsRequest;
import net.hamtag.server.api.request.types.news.panel.GetNewsRequest;

@Path("/newspanel/")
public class NewsResource {
	@POST
	@Path("/get")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getNews(@QueryParam("max-results") String maxResults,@QueryParam("last-publish-time") String lastPublishTime){
		return new GetNewsRequest(maxResults,lastPublishTime).handle();
	}
	@POST
	@Path("/add")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addNews(String requestJson){
		return new AddNewsRequest(requestJson).handle();
	}
}
