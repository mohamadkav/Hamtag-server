package net.hamtag.server.api.resource;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import net.hamtag.server.api.request.types.device.ConfirmDeviceRequest;
import net.hamtag.server.api.request.types.device.LoginDeviceRequest;
import net.hamtag.server.api.request.types.device.NewDeviceRequest;

@Path("/devices/")
public class DeviceResource {

	@POST
	@Path("/new/{number}/{password}")
	@Produces(MediaType.APPLICATION_JSON)
	public String requestMessage(@PathParam("number")String number,@PathParam("password")String password){
		return new NewDeviceRequest(number,password,true).handle().toString();
	}
	
	@POST
	@Path("/confirm/{number}/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public String confirmNumber(@PathParam("number")String number,@PathParam("token")String token){
		return new ConfirmDeviceRequest(number, token).handle().toString();
	}
	
	@POST
	@Path("/login/{number}/{password}")
	public String loginWithCredentials(@PathParam("number")String number,@PathParam("password")String password){
		return new LoginDeviceRequest(number, password).handle().toString();
	}
}
