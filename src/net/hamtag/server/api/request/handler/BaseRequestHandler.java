package net.hamtag.server.api.request.handler;

import net.hamtag.server.api.response.Response;


public abstract class BaseRequestHandler {
	public abstract Response handle();
	protected boolean validateString(String string) {
		if (string == null || string.trim().equals(""))
			return false;
		return true;
	} 
}