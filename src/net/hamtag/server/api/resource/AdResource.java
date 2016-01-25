package net.hamtag.server.api.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import net.hamtag.server.api.request.types.ads.GetAdContentRequest;
import net.hamtag.server.api.request.types.ads.GetAdsByTimeRequest;
import net.hamtag.server.api.request.types.ads.AdLikeRequest;
import net.hamtag.server.api.request.types.ads.AdShownRequest;

@Path("/ads/")
public class AdResource {
	/*
	 * EXAMPLE: http://localhost:8080/Hamtag/resource/ads/get/?max-results=1&token=RifJzGdMoRXKhD3HRbNzH24uUOym9B&phone-number=09128145827&last-update-time=0
	 * Response Example: {"success":true,"error":null,"original_response":[{"id":1,"price":"ziad","link":"www.com","phone":"9012","location":"31.23,11.33","address":"Inja","comments":"chize khoobie","corporation":"sherkat","thumbnailId":null,"contentIds":[1]}],"response_type":"normal_response"}
	 * 
	 * 
	 * Error types:
	 * 		AUTH_FAILED,
		TOKEN_NULL_OR_EMPTY,
		PHONE_NUMBER_NULL_OR_EMPTY,
		PHONE_NUMBER_INVALID@QueryParam("phone-number") String phoneNumber
	*/
	@POST
	@Path("/get")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getAdsByCategory(@QueryParam("max-results") String maxResults,@QueryParam("token") String token,
			@QueryParam("phone-number") String phoneNumber,
			@QueryParam("last-update-time") String lastUpdateTime){
		return new GetAdsByTimeRequest(maxResults, lastUpdateTime,token,phoneNumber).getHandler().handle();
	}
	
	@POST
	@Path("/like")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response like(@QueryParam("ad-id") String adId,@QueryParam("token") String token,@QueryParam("phone-number") String phoneNumber){
		return new AdLikeRequest(adId, token, phoneNumber).handle();
	}
	//Example: http://localhost:8080/Hamtag/resource/ads/shown/?ad-id=1&phone-number=0912&token=123&shown-date=1453632425648&shown-seconds=15
	@POST
	@Path("/shown")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addToShown(@QueryParam("ad-id") String adId,@QueryParam("phone-number") String phoneNumber,@QueryParam("token") String token,@QueryParam("shown-date") String shownDate
			,@QueryParam("shown-seconds") String shownSeconds){
		return new AdShownRequest(adId, token, phoneNumber,shownDate,shownSeconds).handle();
	}
	
	//EG: http://localhost:8080/Hamtag/resource/ads/content/?id=2&token=RifJzGdMoRXKhD3HRbNzH24uUOym9B&phone=09128145827
	@POST
	@Path("/content")
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getAdContents(@QueryParam("id") String id,@QueryParam("token") String token,
			@QueryParam("phone") String phoneNumber){
		return new GetAdContentRequest(id,token,phoneNumber).handle();
	}
}
