package net.hamtag.server.api.request.types.corporations.panel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ws.rs.core.Response;

import org.springframework.security.core.context.SecurityContextHolder;

import net.hamtag.server.api.request.types.BaseWebPanelRequest;
import net.hamtag.server.api.response.HamtagResponse;
import net.hamtag.server.api.response.types.corporation.CorporationInfoDTO;
import net.hamtag.server.datatypes.corporation.Corporation;
import net.hamtag.server.datatypes.user.UserMgr;

public class GetAllCorporationInfoRequest extends BaseWebPanelRequest{
	public Response handle(){
		Set<String>authorizedRoles=new HashSet<>();
		authorizedRoles.add("ROLE_ADMIN");
		Response response=auth(authorizedRoles);
		if(response!=null)
			return response;
		List<CorporationInfoDTO>dtos=new ArrayList<>();
		for(Corporation corporation:UserMgr.getByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).getVisibleCorporations()){
			CorporationInfoDTO dto=new CorporationInfoDTO();
			dto.setId(corporation.getId());
			dto.setName(corporation.getName());
			dtos.add(dto);
		}
		return new HamtagResponse(dtos).getResponse(null);
	}
}
