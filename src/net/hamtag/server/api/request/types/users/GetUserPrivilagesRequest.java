package net.hamtag.server.api.request.types.users;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ws.rs.core.Response;

import org.springframework.security.core.context.SecurityContextHolder;

import net.hamtag.server.api.request.types.BaseWebPanelRequest;
import net.hamtag.server.api.response.HamtagResponse;
import net.hamtag.server.datatypes.user.UserMgr;
import net.hamtag.server.datatypes.user.UserRole;

public class GetUserPrivilagesRequest extends BaseWebPanelRequest {
	public GetUserPrivilagesRequest() {
	}

	Set<String> neededRoles = new HashSet<>();

	public Response handle() {
		neededRoles.add("ROLE_USER");

		Response response = auth(neededRoles);
		if (response != null)
			return response;
		List<String>roles=new ArrayList<>();
		for(UserRole ur:UserMgr.getByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).getUserRoles()){
			roles.add(ur.getRole());
		}
		return new HamtagResponse(roles).getResponse(null);
	}
}
