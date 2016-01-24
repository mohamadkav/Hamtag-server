package net.hamtag.server.api.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import net.hamtag.server.api.request.types.news.GetNewsByCategoryForDeviceRequest;
import net.hamtag.server.api.request.types.news.GetNewsContentRequest;
import net.hamtag.server.api.request.types.news.NewsLikeRequest;

@Path("/news/")
public class NewsResource {
	// http://localhost:8080/Hamtag/resource/news/get/?max-results=1&token=57lxF9vF14ImIQqRzagEujfCW3mFP6&phone-number=09128145827&last-update-time=0
	@POST
	@Path("/get")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getNewsByCategory(@QueryParam("max-results") String maxResults,@QueryParam("token") String token,
			@QueryParam("phone-number") String phoneNumber,
			@QueryParam("last-update-time") String lastUpdateTime){
		return new GetNewsByCategoryForDeviceRequest(maxResults, lastUpdateTime,token,phoneNumber).getHandler().handle();
	}
	//http://localhost:8080/Hamtag/resource/news/like/?news-id=1&token=123&phone-number=0912
	@POST
	@Path("/like")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response like(@QueryParam("news-id") String adId,@QueryParam("token") String token,@QueryParam("phone-number") String phoneNumber){
		return new NewsLikeRequest(adId, token, phoneNumber).handle();
	}
	
	//EG: http://localhost:8080/Hamtag/resource/news/content/?id=2&token=RifJzGdMoRXKhD3HRbNzH24uUOym9B&phone=09128145827
	@POST
	@Path("/content")
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getNewsContents(@QueryParam("id") String id,@QueryParam("token") String token,
			@QueryParam("phone") String phoneNumber){
		return new GetNewsContentRequest(id,token,phoneNumber).handle();
	}
}
