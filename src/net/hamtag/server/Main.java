package net.hamtag.server;

import net.hamtag.server.api.request.types.ConfirmDeviceRequest;

public class Main {
	public static void main(String[] args) {
		System.out.println(new ConfirmDeviceRequest("09128145827", "4884").handle());
	}
}
