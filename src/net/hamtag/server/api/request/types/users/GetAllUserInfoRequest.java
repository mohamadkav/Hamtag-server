package net.hamtag.server.api.request.types.users;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ws.rs.core.Response;

import net.hamtag.server.api.request.types.BaseWebPanelRequest;
import net.hamtag.server.api.response.HamtagResponse;
import net.hamtag.server.api.response.types.user.UserInfoDTO;
import net.hamtag.server.datatypes.ad.AdMgr;
import net.hamtag.server.datatypes.news.NewsMgr;
import net.hamtag.server.datatypes.user.User;
import net.hamtag.server.datatypes.user.UserMgr;

public class GetAllUserInfoRequest extends BaseWebPanelRequest{

	@Override
	public Response handle() {
		Set<String>authorizedRoles=new HashSet<>();
		authorizedRoles.add("ROLE_ADMIN");
		Response response=auth(authorizedRoles);
		if(response!=null)
			return response;
		List<UserInfoDTO>dtos=new ArrayList<>();
		for(User user:UserMgr.list()){
			UserInfoDTO dto=new UserInfoDTO();
			dto.setAdsByUser(AdMgr.getCountForUser(user));
			dto.setUserName(user.getUsername());
			dto.setNewsByUser(NewsMgr.getCountForUser(user));
			dtos.add(dto);
		}
		return new HamtagResponse(dtos).getResponse(null);
	}
	
}
