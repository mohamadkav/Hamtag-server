package net.hamtag.server.api.request.types.contentproviders.panel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ws.rs.core.Response;

import net.hamtag.server.api.request.types.BaseWebPanelRequest;
import net.hamtag.server.api.response.HamtagResponse;
import net.hamtag.server.api.response.types.device.ProviderInfoDTO;
import net.hamtag.server.datatypes.contentprovider.ContentProvider;
import net.hamtag.server.datatypes.contentprovider.ContentProviderMgr;

public class GetAllProviderInfoRequest extends BaseWebPanelRequest{
	public GetAllProviderInfoRequest(){}
	public Response handle(){
		Set<String>authorizedRoles=new HashSet<>();
		authorizedRoles.add("ROLE_ADMIN");
		authorizedRoles.add("ROLE_WRITER");
		Response response=auth(authorizedRoles);
		if(response!=null)
			return response;
		List<ProviderInfoDTO>dtos=new ArrayList<>();
		for(ContentProvider cp:ContentProviderMgr.list()){
			ProviderInfoDTO dto=new ProviderInfoDTO();
			dto.setId(cp.getId());
			dto.setName(cp.getName());
			dtos.add(dto);
		}
		return new HamtagResponse(dtos).getResponse(null);
	}
}
