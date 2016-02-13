package net.hamtag.server.api.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import net.hamtag.server.api.request.types.device.panel.GetMainDeviceInfosRequest;

@Path("/devicepanel/")
public class DeviceResource {
	@POST
	@Path("/summary/infos")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getDeviceInfos(){
		return new GetMainDeviceInfosRequest().handle();
	}
}
