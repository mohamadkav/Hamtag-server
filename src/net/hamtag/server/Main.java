package net.hamtag.server;

import net.hamtag.server.datatypes.corporation.Corporation;
import net.hamtag.server.datatypes.corporation.CorporationMgr;

public class Main {
	public static void main(String[] args) {
		Corporation c=new Corporation();
		c.setName("هلدینگ اصغر");
		CorporationMgr.add(c);
	}
}
