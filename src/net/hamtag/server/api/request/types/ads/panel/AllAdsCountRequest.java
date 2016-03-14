package net.hamtag.server.api.request.types.ads.panel;

import javax.ws.rs.core.Response;

import net.hamtag.server.api.request.types.BaseWebPanelRequest;
import net.hamtag.server.api.response.HamtagResponse;
import net.hamtag.server.datatypes.ad.AdMgr;

public class AllAdsCountRequest extends BaseWebPanelRequest{

	@Override
	public Response handle() {
		return new HamtagResponse(AdMgr.getAllAdsCount()).getResponse(null);
	}
	
}
