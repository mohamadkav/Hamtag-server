package net.hamtag.server.api.request.types.ads;

import java.io.File;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import net.hamtag.server.datatypes.ad.AdContent;
import net.hamtag.server.datatypes.ad.AdContentMgr;

public class GetAdContentRequest {
	String id;
	public GetAdContentRequest(String id){
		this.id=id;
	}
	public Response handle(){
		if(id==null||id.trim().isEmpty())
			return null;
		AdContent ac=AdContentMgr.getInstance().get(AdContent.class, Integer.parseInt(id));
		if(ac==null)
			return null;
	    File file = ac.getContent();
	    ResponseBuilder response = javax.ws.rs.core.Response.ok((Object) file);
	    return response.build();
	}
}
