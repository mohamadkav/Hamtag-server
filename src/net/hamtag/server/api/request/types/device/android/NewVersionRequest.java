package net.hamtag.server.api.request.types.device.android;

import javax.ws.rs.core.Response;

import net.hamtag.server.api.response.HamtagResponse;
import net.hamtag.server.api.response.types.device.NewVersionDTO;
import net.hamtag.server.utils.Config;

public class NewVersionRequest {
	public NewVersionRequest(){}
	public Response handle(){
		NewVersionDTO dto=new NewVersionDTO();
		dto.setLink(Config.NEW_VERSION_LINK);
		dto.setText(Config.NEW_VERSION_TEXT);
		dto.setVersion(Config.VERSION);
		return new HamtagResponse(dto).getResponse(null);
	}
}
