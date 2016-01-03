package net.hamtag.server;

import net.hamtag.server.datatypes.ad.AdShown;
import net.hamtag.server.datatypes.device.Device;
import net.hamtag.server.datatypes.device.DeviceMgr;

public class Main {
	public static void main(String[] args) {
		for(AdShown as:DeviceMgr.getInstance().get(Device.class, 1).getAdShown()){
			System.out.println(as.getShowDate());
		}
	}
}
