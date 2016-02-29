package net.hamtag.server.api.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import net.hamtag.server.api.request.types.device.android.ConfirmDeviceRequest;
import net.hamtag.server.api.request.types.device.android.ConfirmForgotPasswordRequest;
import net.hamtag.server.api.request.types.device.android.ForgotPasswordRequest;
import net.hamtag.server.api.request.types.device.android.GetDeviceInfoRequest;
import net.hamtag.server.api.request.types.device.android.LoginDeviceRequest;
import net.hamtag.server.api.request.types.device.android.NewDeviceRequest;
import net.hamtag.server.api.request.types.device.android.NewDeviceRequestWithEmail;
import net.hamtag.server.api.request.types.device.android.NewVersionRequest;
import net.hamtag.server.api.request.types.device.android.SetOrUpdateGCMTokenRequest;
import net.hamtag.server.api.request.types.device.android.UpdateCredentialsRequest;

@Path("/devices/")
public class DeviceResourceAndroid {

	@POST
	@Path("/new/{number}/{password}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response requestMessage(@PathParam("number")String number,@PathParam("password")String password){
		return new NewDeviceRequest(number,password,true).handle();
	}
	
	@POST
	@Path("/newemail/{email}/{password}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response requestEmail(@PathParam("email")String email,@PathParam("password")String password){
		return new NewDeviceRequestWithEmail(email, password).handle();
	}
	
	@POST
	@Path("/confirm/{number}/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response confirmNumber(@PathParam("number")String number,@PathParam("token")String token){
		return new ConfirmDeviceRequest(number, token).handle();
	}
	
	@POST
	@Path("/forgot/{number}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response forgotPassword(@PathParam("number")String number){
		return new ForgotPasswordRequest(number).handle();
	}
	
	@POST
	@Path("/forgot/confirm/{number}/{token}/{password}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response confirmForgotPassword(@PathParam("number")String number,@PathParam("token")String token,@PathParam("password")String password){
		return new ConfirmForgotPasswordRequest(number,token,password).handle();
	}
	
	@POST
	@Path("/login/{number}/{password}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response loginWithCredentials(@PathParam("number")String number,@PathParam("password")String password){
		return new LoginDeviceRequest(number, password).handle() ;
	}
	// http://185.105.239.61:8080/Hamtag/resource/devices/newversion/
	@GET
	@Path("/newversion/")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getNewVersionInfo(){
		return new NewVersionRequest().handle() ;
	}
	
	@POST
	@Path("/update/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateCredentials(@QueryParam("new-name") String name,@QueryParam("token") String token,
			@QueryParam("phone-number") String phoneNumber){
		return new UpdateCredentialsRequest(name, phoneNumber, token).handle() ;
	}
	
	@POST
	@Path("/sync/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getDeviceInfo(@QueryParam("token") String token,
			@QueryParam("phone-number") String phoneNumber){
		return new GetDeviceInfoRequest(phoneNumber, token).handle() ;
	}
	
	@POST
	@Path("/gcm")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateGCMToken(@QueryParam("gcm-token") String gcmToken,@QueryParam("token") String token,
			@QueryParam("phone-number") String phoneNumber){
		return new SetOrUpdateGCMTokenRequest(gcmToken, token, phoneNumber).handle();
	}
	
/*	//Example: http://localhost:8080/Hamtag/resource/devices/categories/update/?categories=SPORTS,FILM&phone=0912&token=123
	@POST
	@Path("/categories/update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateCategories(@QueryParam("categories")String categories,@QueryParam("phone")String phoneNumber,@QueryParam("token")String token){
		return new UpdateDeviceCategoriesRequest(categories, phoneNumber, token).handle() ;
	}*/
}
