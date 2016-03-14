package net.hamtag.server.api.request.types.news.panel;

import javax.ws.rs.core.Response;

import net.hamtag.server.api.request.types.BaseWebPanelRequest;
import net.hamtag.server.api.response.HamtagResponse;
import net.hamtag.server.datatypes.news.NewsMgr;

public class AllNewsCountRequest extends BaseWebPanelRequest{

	@Override
	public Response handle() {
		return new HamtagResponse(NewsMgr.getAllNewsCount()).getResponse(null);
	}
	
}
