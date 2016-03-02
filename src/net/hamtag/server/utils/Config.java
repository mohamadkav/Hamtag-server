package net.hamtag.server.utils;

public class Config {
	//DO NOT EVER FUCKING CREATE ANOTHER PATH WHICH MATCHES REGEX /resource/.+ 
	//DO NOT EVER FUCKING CREATE A USER WITH THE PHONE NUMBER OR MOBILE NUMBER USERNAME
	public static final int MESSAGE_VALIDATION_TIME= 300000;
	public static final int DEFAULT_MAX_RESULTS=200;
	public static final String DATABASE_RANDOM_QUERY="1=1 order by random()";
	public static final int FROM_WHEN_TO_ANALYSE_USER_DATA_BY_DAYS=7;
	public static final double VERSION=1.0;
	public static final String NEW_VERSION_TEXT="Test";
	public static final String NEW_VERSION_LINK="http://www.hamtag.net/";
	public static final String fromEmail="hamtag.bot@gmail.com";
	public static final String fromPassword="@n0th3rHamtag";
	public static final String emailSubject="کد فعالسازی";
	public static final int DAYS_THAT_DEVICE_CONSIDERS_ACTIVE=2;
	public static final int MAXIMUM_CHARGE=2000;
}
