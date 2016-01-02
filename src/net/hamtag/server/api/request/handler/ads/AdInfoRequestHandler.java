package net.hamtag.server.api.request.handler.ads;

import net.hamtag.server.api.request.handler.BaseRequestHandler;
import net.hamtag.server.api.request.types.AdInfoRequest;
import net.hamtag.server.api.response.Response;
import net.hamtag.server.api.response.types.AdInfoDTO;

public class AdInfoRequestHandler extends BaseRequestHandler{
	AdInfoRequest request;
	public AdInfoRequestHandler(AdInfoRequest req){
		this.request=req;
	}
	@Override
	public Response handle() {
		AdInfoDTO dto=new AdInfoDTO();
		dto.setTestString(request.getTestString()+" You've got it");
		return new Response(dto);
	}

}
