package net.hamtag.server.api.request.types.device.panel;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Response;

import net.hamtag.server.api.request.types.BaseWebPanelRequest;
import net.hamtag.server.api.response.HamtagResponse;
import net.hamtag.server.api.response.types.device.DeviceSummaryDTO;
import net.hamtag.server.datatypes.ad.AdShownMgr;
import net.hamtag.server.datatypes.device.DeviceMgr;
import net.hamtag.server.datatypes.news.NewsShownMgr;

public class GetMainDeviceInfosRequest extends BaseWebPanelRequest {
	Set<String>neededRoles=new HashSet<>();
	public GetMainDeviceInfosRequest(){}
	public Response handle(){
		neededRoles.add("ROLE_ADMIN");
		
		Response response=auth(neededRoles);
		if(response!=null)
			return response;
		DeviceSummaryDTO dto=new DeviceSummaryDTO();
		dto.setAllUsers(DeviceMgr.getDevicesCount());
		dto.setActiveUsersByNews(NewsShownMgr.getActiveDeviceCountByNews());
		dto.setActiveUsersByAds(AdShownMgr.getActiveDeviceCountByNews());
		dto.setTotalDebt(DeviceMgr.getTotalDebt());
		return new HamtagResponse(dto).getResponse(null);
	}
}
