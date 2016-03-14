package net.hamtag.server.api.resource;

import java.io.InputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import net.hamtag.server.api.request.types.news.panel.AddNewsRequest;
import net.hamtag.server.api.request.types.news.panel.AllNewsCountRequest;
import net.hamtag.server.api.request.types.news.panel.GetNewsCountShownForWeekRequest;
import net.hamtag.server.api.request.types.news.panel.GetNewsRequest;
import net.hamtag.server.api.request.types.news.panel.UploadFileForNews;

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
	@POST
	@Path("/countall")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getAllNewsCount(){
		return new AllNewsCountRequest().handle();
	}
	@POST
	@Path("/count-week-shown")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getShownCountForWeek(){
		return new GetNewsCountShownForWeekRequest().handle();
	}
	
	@POST
	@Path("/upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadFile(@FormDataParam("file") InputStream uploadedInputStream,
		@FormDataParam("file") FormDataContentDisposition fileDetail) {
		return new UploadFileForNews(uploadedInputStream, fileDetail).handle();
	}
}
