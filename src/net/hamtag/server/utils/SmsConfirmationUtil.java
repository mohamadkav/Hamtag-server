package net.hamtag.server.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ThreadLocalRandom;

public class SmsConfirmationUtil {
	public static String generateToken(){
		int first=ThreadLocalRandom.current().nextInt(1,9);
		int sec=ThreadLocalRandom.current().nextInt(1,9);
		int third=ThreadLocalRandom.current().nextInt(1,9);
		int fourth=ThreadLocalRandom.current().nextInt(1,9);
		return first+""+sec+""+third+""+fourth+"";
	}
	public static boolean sendMessage(String number,String token) {
		try{
			String url = "http://37.130.202.188/class/sms/webservice/send_url.php?from=100020400&to="+number+"&msg="+token+"&uname=hamtag&pass=mgh1114423mgh";
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();

			// optional default is GET
			con.setRequestMethod("GET");

			//add request header
			con.setRequestProperty("User-Agent", "Mozilla/5.0");

			int responseCode = con.getResponseCode();
			System.out.println("\nSending 'GET' request to URL : " + url);
			System.out.println("Response Code : " + responseCode);

			BufferedReader in = new BufferedReader(
			        new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			//print result
			if(!Character.isDigit(response.toString().charAt(0))){
				System.out.println(response.toString());
				return false;
			}
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
}
