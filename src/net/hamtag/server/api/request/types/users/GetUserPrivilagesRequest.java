package net.hamtag.server.api.request.types.users;

import java.util.ArrayList;
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

	// Set<String> neededRoles = new HashSet<>();
	private enum Error {
		PROBLEM_IN_USER_PRIVILAGES
	}

	public Response handle() {

		// UNAUTHENTICATED REQUEST!!!!

		/*
		 * neededRoles.add("ROLE_USER");
		 * 
		 * Response response = auth(neededRoles); if (response != null) return
		 * response;
		 */
		try {
			Set<UserRole> userRoles = UserMgr
					.getByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).getUserRoles();
			List<String> roles = new ArrayList<>();
			for (UserRole ur : userRoles) {
				if (!ur.getRole().equals("ROLE_USER"))
					roles.add(ur.getRole());
			}
			if (roles.size() > 1 || roles.isEmpty())
				throw new NullPointerException();
			return new HamtagResponse(roles.get(0)).getResponse(null);
		} catch (NullPointerException e) {
			return new HamtagResponse(Error.PROBLEM_IN_USER_PRIVILAGES).getResponse(Response.Status.EXPECTATION_FAILED);
		}

	}
}
