package net.hamtag.server.api.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import net.hamtag.server.api.request.types.NewDeviceRequest;

@Path("/devices/")
public class DeviceResource {

	@GET
	@Path("/new/{number}")
	@Produces(MediaType.APPLICATION_JSON)
	public String requestMessage(@PathParam("number")String number){
		return NewDeviceRequest.addNewDevice(number);
	}
}
