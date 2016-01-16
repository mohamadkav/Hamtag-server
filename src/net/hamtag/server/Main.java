package net.hamtag.server;

import net.hamtag.server.api.request.types.ads.GetAdsByCategoryForDeviceRequest;
import net.hamtag.server.datatypes.device.Device;
import net.hamtag.server.datatypes.device.DeviceMgr;
import net.hamtag.server.utils.GenerateTokenUtil;

public class Main {
	public static void main(String[] args) {
		//TODO: change location parameter for ad to float
	//	System.err.println(new GetAdsByCategoryForDeviceRequest("2", "SPORTS","0").getHandler().handle().toString());
		System.err.println(GenerateTokenUtil.generateNewToken());
		Device d=DeviceMgr.getDeviceByPhoneNumber("0912");
		d.setPhoneNumber("0913!!!");
		DeviceMgr.update(d);
	}
}
