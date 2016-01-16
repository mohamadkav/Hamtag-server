package net.hamtag.server.api.request.types.device;

import java.util.Date;
import java.util.List;

import org.hibernate.Transaction;

import net.hamtag.server.api.response.Response;
import net.hamtag.server.datatypes.device.Device;
import net.hamtag.server.datatypes.device.DeviceMgr;
import net.hamtag.server.datatypes.device.TempDevice;
import net.hamtag.server.datatypes.device.TempDeviceMgr;
import net.hamtag.server.utils.SmsConfirmationUtil;

public class ForgotPasswordRequest {
	private enum Error {
		NUMBER_ALREADY_HAS_VALID_TOKEN, NUMBER_NULL_OR_INVALID, NUMBER_NOT_ENROLLED, REQUEST_SENDING_ERROR;
	}

	private String number;

	public ForgotPasswordRequest(String number) {
		this.number = number;
	}

	public Response handle() {
		if (number == null) {
			return new Response(Error.NUMBER_NULL_OR_INVALID);
		}
		Device device = DeviceMgr.getDeviceByPhoneNumber(number);
		if (device == null)
			return new Response(Error.NUMBER_NOT_ENROLLED);
		// DELETE THE INVALID DATA FIRST!
		long date = new Date().getTime();
		List<TempDevice> allDevices = TempDeviceMgr.list();
		Transaction tx = TempDeviceMgr.getInstance().beginTransaction();
		for (TempDevice t : allDevices) {
			if (t.getValidUntill().getTime() < date) {
				Object o = TempDeviceMgr.getInstance().load(TempDevice.class, t.getId());
				TempDeviceMgr.getInstance().delete(o);
			}
		}
		tx.commit();
		allDevices = TempDeviceMgr.list();
		for (TempDevice t : allDevices) {
			if (t.getPhoneNumber().equals(number))
				return new Response(Error.NUMBER_ALREADY_HAS_VALID_TOKEN);
		}
		String token = SmsConfirmationUtil.generateToken();
		boolean success = SmsConfirmationUtil.sendMessage(number, token);
		if (!success)
			return new Response(Error.REQUEST_SENDING_ERROR);

		TempDevice tempDevice = new TempDevice();
		tempDevice.setPhoneNumber(number);
		tempDevice.setToken(token);
		date = new Date().getTime();
		// 3 Minutes Validation
		// TODO: CONFIG
		date += 180000;
		tempDevice.setValidUntill(new Date(date));
		TempDeviceMgr.add(tempDevice);
		return new Response();
	}
}
