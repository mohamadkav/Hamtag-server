package net.hamtag.server.api.request.types.ads.android;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import net.hamtag.server.api.request.types.BaseDeviceRequest;
import net.hamtag.server.api.response.HamtagResponse;
import net.hamtag.server.datatypes.ad.AdContent;
import net.hamtag.server.datatypes.ad.AdContentMgr;

public class GetAdContentRequest extends BaseDeviceRequest{
	String id;
	private enum Error{
		ID_NOT_PROVIDED,
		CONTENT_NOT_IN_DB
	}
	public GetAdContentRequest(String id,String token,String number){
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
		AdContent ac=AdContentMgr.getInstance().get(AdContent.class, Long.parseLong(id));
		if(ac==null)
			return new HamtagResponse(Error.CONTENT_NOT_IN_DB).getResponse(Response.Status.BAD_REQUEST);
		byte[] file = ac.getContent();
	    ResponseBuilder content = javax.ws.rs.core.Response.ok((Object) file);
	    return content.build();
	}
}
