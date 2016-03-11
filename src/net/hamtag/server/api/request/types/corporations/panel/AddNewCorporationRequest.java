package net.hamtag.server.api.request.types.corporations.panel;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Response;

import net.hamtag.server.api.request.types.BaseWebPanelRequest;
import net.hamtag.server.api.response.HamtagResponse;
import net.hamtag.server.datatypes.corporation.Corporation;
import net.hamtag.server.datatypes.corporation.CorporationMgr;

public class AddNewCorporationRequest extends BaseWebPanelRequest{
	String corporationName;
	public AddNewCorporationRequest(String corporationName){
		this.corporationName=corporationName;
	}
	public Response handle(){
		Set<String>authorizedRoles=new HashSet<>();
		authorizedRoles.add("ROLE_ADMIN");
		Response response=auth(authorizedRoles);
		if(response!=null)
			return response;
		Corporation corporation=new Corporation();
		corporation.setName(corporationName);
		CorporationMgr.add(corporation);
		return new HamtagResponse().getResponse(null);
	}
}
