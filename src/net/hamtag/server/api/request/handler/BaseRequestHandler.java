package net.hamtag.server.api.request.handler;

import javax.ws.rs.core.Response;

public abstract class BaseRequestHandler {
	public abstract Response handle();
	protected boolean validateString(String string) {
		if (string == null || string.trim().equals(""))
			return false;
		return true;
	} 
}