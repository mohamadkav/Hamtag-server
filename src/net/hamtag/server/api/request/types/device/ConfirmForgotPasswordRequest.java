package net.hamtag.server.api.request.types.device;

import java.util.Date;

import javax.ws.rs.core.Response;

import net.hamtag.server.api.response.HamtagResponse;
import net.hamtag.server.api.response.types.device.TokenDTO;
import net.hamtag.server.datatypes.device.Device;
import net.hamtag.server.datatypes.device.DeviceMgr;
import net.hamtag.server.datatypes.device.TempDevice;
import net.hamtag.server.datatypes.device.TempDeviceMgr;
import net.hamtag.server.utils.GenerateTokenUtil;

public class ConfirmForgotPasswordRequest {
	private enum Error {
		TOKEN_INVALID, NUMBER_NOT_IN_TEMP_DEVICES, NUMBER_NOT_ENROLLED_YET
	}

	String number, token, password;

	public ConfirmForgotPasswordRequest(String number, String token, String password) {
		this.number = number;
		this.token = token;
		this.password = password;
	}

	public Response handle() {
		TempDevice tempDevice = TempDeviceMgr.getByNumber(number);
		if (tempDevice == null)
			return new HamtagResponse(Error.NUMBER_NOT_IN_TEMP_DEVICES).getResponse(Response.Status.UNAUTHORIZED);
		if (tempDevice.getToken().equals(token) && tempDevice.getValidUntill().getTime() >= new Date().getTime()) {
			Device device = DeviceMgr.getDeviceByPhoneNumber(number);
			if (device == null)
				return new HamtagResponse(Error.NUMBER_NOT_ENROLLED_YET).getResponse(Response.Status.UNAUTHORIZED);
			device.setPassword(password);
			device.setToken(GenerateTokenUtil.generateNewToken());
			DeviceMgr.update(device);
			TokenDTO dto = new TokenDTO();
			dto.setToken(device.getToken());
			return new HamtagResponse(dto).getResponse(null);
		}
		return new HamtagResponse(Error.TOKEN_INVALID).getResponse(Response.Status.UNAUTHORIZED);
	}
}
