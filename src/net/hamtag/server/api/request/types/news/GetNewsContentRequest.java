package net.hamtag.server.api.request.types.news;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import net.hamtag.server.api.request.types.BaseDeviceRequest;
import net.hamtag.server.api.response.HamtagResponse;
import net.hamtag.server.datatypes.news.NewsContent;
import net.hamtag.server.datatypes.news.NewsContentMgr;

public class GetNewsContentRequest extends BaseDeviceRequest{
	String id;
	private enum Error{
		ID_NOT_PROVIDED,
		CONTENT_NOT_IN_DB
	}
	public GetNewsContentRequest(String id,String token,String number){
		this.id=id;
		setPhoneNumber(number);
		setToken(token);
	}
	@Override
	public Response handle(){
		Response response=auth();
		if(response!=null)
			return response;
		if(id==null||id.trim().isEmpty())
			return new HamtagResponse(Error.ID_NOT_PROVIDED).getResponse(Response.Status.BAD_REQUEST);
		NewsContent nc=NewsContentMgr.getInstance().get(NewsContent.class, Long.parseLong(id));
		if(nc==null)
			return new HamtagResponse(Error.CONTENT_NOT_IN_DB).getResponse(Response.Status.BAD_REQUEST);
		byte[] file = nc.getContent();
	    ResponseBuilder content = javax.ws.rs.core.Response.ok((Object) file);
	    return content.build();
	}
}
