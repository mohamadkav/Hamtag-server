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

import net.hamtag.server.api.request.types.ads.panel.AdShownReportRequest;
import net.hamtag.server.api.request.types.ads.panel.AddAdRequest;
import net.hamtag.server.api.request.types.ads.panel.AllAdsCountRequest;
import net.hamtag.server.api.request.types.ads.panel.GetAdsRequest;
import net.hamtag.server.api.request.types.ads.panel.GetAdCountShownForWeekRequest;
import net.hamtag.server.api.request.types.ads.panel.GetUserNumberForFilterRequest;
import net.hamtag.server.api.request.types.ads.panel.UploadFileForAds;

@Path("/adpanel/")
public class AdResource {
	@POST
	@Path("/get")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getAds(@QueryParam("max-results") String maxResults,@QueryParam("last-publish-time") String lastPublishTime){
		return new GetAdsRequest(maxResults,lastPublishTime).handle();
	}
	@POST
	@Path("/add")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addAd(String requestJson){
		return new AddAdRequest(requestJson).handle();
	}
	@POST
	@Path("/count")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getUserNumberForAdSending(String requestJson){
		return new GetUserNumberForFilterRequest(requestJson).handle();
	}
	@POST
	@Path("/report")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getShownAdCount(String requestJson){
		return new AdShownReportRequest(requestJson).handle();
	}
	@POST
	@Path("/countall")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getAllAdsCount(){
		return new AllAdsCountRequest().handle();
	}
	@POST
	@Path("/count-week-shown")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getShownCountForWeek(){
		return new GetAdCountShownForWeekRequest().handle();
	}
	@POST
	@Path("/upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadFile(@FormDataParam("file") InputStream uploadedInputStream,
		@FormDataParam("file") FormDataContentDisposition fileDetail) {
		return new UploadFileForAds(uploadedInputStream, fileDetail).handle();
	}
}
