package net.hamtag.server.api.request.types;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.ws.rs.core.Response;

import org.springframework.security.core.context.SecurityContextHolder;

import net.hamtag.server.api.response.HamtagResponse;
import net.hamtag.server.datatypes.user.User;
import net.hamtag.server.datatypes.user.UserMgr;
import net.hamtag.server.datatypes.user.UserRole;

public abstract class BaseWebPanelRequest extends BaseRequest{
	private enum Error{
		AUTH_FAILED
	}
	public Response auth(Set<String>roles){
		try{
			User user=UserMgr.getByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
			List<String>rolesString=new ArrayList<>();
			for(UserRole ur:user.getUserRoles())
				if(!rolesString.contains(ur.getRole()))
					rolesString.add(ur.getRole());
			if(!rolesString.containsAll(roles))
				throw new NullPointerException();
			return null;
		}catch(NullPointerException e){
			return new HamtagResponse(Error.AUTH_FAILED).getResponse(Response.Status.FORBIDDEN);
		}
	}
}
