package net.hamtag.server.utils;

public class Config {
	//DO NOT EVER FUCKING CREATE ANOTHER PATH WHICH MATCHES REGEX /resource/.+ 
	public static final int MESSAGE_VALIDATION_TIME= 300000;
	public static final int DEFAULT_MAX_RESULTS=200;
	public static final String DATABASE_RANDOM_QUERY="1=1 order by random()";
	public static final int FROM_WHEN_TO_ANALYSE_USER_DATA_BY_DAYS=7;
	public static final double VERSION=1.0;
	public static final String NEW_VERSION_TEXT="Test";
	public static final String NEW_VERSION_LINK="http://www.hamtag.net/";
}
