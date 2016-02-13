package net.hamtag.server.api.request.types;

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
	public Response auth(Set<UserRole>roles){
		try{
			User user=UserMgr.getByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
			if(!user.getUserRoles().containsAll(roles))
				throw new NullPointerException();
			return null;
		}catch(NullPointerException e){
			return new HamtagResponse(Error.AUTH_FAILED).getResponse(Response.Status.FORBIDDEN);
		}
	}
}
