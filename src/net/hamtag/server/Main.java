package net.hamtag.server;

import java.io.File;
import java.io.FileInputStream;

import net.hamtag.server.datatypes.ad.Ad;
import net.hamtag.server.datatypes.ad.AdContent;
import net.hamtag.server.datatypes.ad.AdContentMgr;
import net.hamtag.server.datatypes.ad.AdMgr;

public class Main {
	public static void main(String[] args) {
		// TODO: change location parameter for ad to float
		// System.err.println(new GetAdsByCategoryForDeviceRequest("2",
		// "SPORTS","0").getHandler().handle().toString());
		// System.err.println(GenerateTokenUtil.generateNewToken());
		// Device d=DeviceMgr.getDeviceByPhoneNumber("0912");
		// d.setPhoneNumber("0913!!!");
		// DeviceMgr.update(d);
		Ad a = AdMgr.getInstance().get(Ad.class, 1);
		AdContent ac = new AdContent();
		ac.setAd(a);
		ac.setType("THUMBNAIL");
		File file = new File("/home/mohammad/Desktop/1.jpg");
		byte[] bFile = new byte[(int) file.length()];
		try {
			FileInputStream fileInputStream = new FileInputStream(file);
			// convert file into array of bytes
			fileInputStream.read(bFile);
			fileInputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		ac.setContent(bFile);
		AdContentMgr.add(ac);
	}
}
