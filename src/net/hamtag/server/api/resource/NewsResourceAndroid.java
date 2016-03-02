package net.hamtag.server.api.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import net.hamtag.server.api.request.types.news.android.GetAllCategoriesRequest;
import net.hamtag.server.api.request.types.news.android.GetNewsByCategoryForDeviceRequest;
import net.hamtag.server.api.request.types.news.android.GetNewsByProviderRequest;
import net.hamtag.server.api.request.types.news.android.GetNewsContentRequest;
import net.hamtag.server.api.request.types.news.android.NewsLikeRequest;
import net.hamtag.server.api.request.types.news.android.NewsShownRequest;

@Path("/news/")
public class NewsResourceAndroid {
	// http://localhost:8080/Hamtag/resource/news/get/?max-results=1&token=57lxF9vF14ImIQqRzagEujfCW3mFP6&phone-number=09128145827&last-update-time=0
	@POST
	@Path("/get")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getNewsByCategory(@QueryParam("max-results") String maxResults,@QueryParam("token") String token,
			@QueryParam("phone-number") String phoneNumber,
			@QueryParam("last-update-time") String lastUpdateTime){
		return new GetNewsByCategoryForDeviceRequest(maxResults, lastUpdateTime,token,phoneNumber).handle();
	}
	@POST
	@Path("/getbyprovider")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getNewsByProvider(@QueryParam("max-results") String maxResults,@QueryParam("token") String token,
			@QueryParam("phone-number") String phoneNumber,
			@QueryParam("before-time") String beforeTime,@QueryParam("provider-id") String providerId){
		return new GetNewsByProviderRequest(providerId, beforeTime, maxResults, phoneNumber, token).handle();
	}
	//http://localhost:8080/Hamtag/resource/news/like/?news-id=1&token=123&phone-number=0912
	@POST
	@Path("/like")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response like(@QueryParam("news-id") String adId,@QueryParam("token") String token,@QueryParam("phone-number") String phoneNumber){
		return new NewsLikeRequest(adId, token, phoneNumber).handle();
	}
	
	//Example: http://localhost:8080/Hamtag/resource/news/shown/?news-id=2&phone-number=0912&token=123&shown-date=1453632425648&shown-seconds=15
	@POST
	@Path("/shown")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addToShown(@QueryParam("news-id") String newsId,@QueryParam("phone-number") String phoneNumber,@QueryParam("token") String token,@QueryParam("shown-date") String shownDate
			,@QueryParam("shown-seconds") String shownSeconds){
		return new NewsShownRequest(newsId, token, phoneNumber,shownDate,shownSeconds).handle();
	}
	
	//EG: http://localhost:8080/Hamtag/resource/news/content/?id=2&token=RifJzGdMoRXKhD3HRbNzH24uUOym9B&phone=09128145827
	@POST
	@Path("/content")
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getNewsContents(@QueryParam("id") String id,@QueryParam("token") String token,
			@QueryParam("phone-number") String phoneNumber){
		return new GetNewsContentRequest(id,token,phoneNumber).handle();
	}
	// http://localhost:8080/Hamtag/resource/news/categories/?token=123&phone-number=0912
	@POST
	@Path("/categories")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getAllCategories(@QueryParam("token") String token,@QueryParam("phone-number") String phoneNumber){
		return new GetAllCategoriesRequest(phoneNumber, token).handle();
	}
}
