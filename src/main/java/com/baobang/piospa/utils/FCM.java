package com.baobang.piospa.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author BaoBang
 * @Created Jul 27, 2018
 * 
 */
public class FCM {
	private final static String  AUTH_FCM_KEY = "AAAAS_I_Vgo:APA91bELyHqT9Y5h5H-mubt4NUiR4xxUMlS46mDcgird_8CVR4cvsMks83Ku4ztTYNGzURJcjJf6eBZ30I4EgBFmRUAolAa3AC9Q_Cauzz6xgRiK82-dD_Tay9Uv6oRffG6PN90rX7zx";
	private final static String FCM_URL = "https://fcm.googleapis.com/fcm/send";

	/**
	 * Method to send push notification to Android FireBased Cloud messaging Server.
	 * 
	 * @param tokenId
	 *            Generated and provided from Android Client Developer
	 * @param server_key
	 *            Key which is Generated in FCM Server
	 * @param message
	 *            which contains actual information.
	 * 
	 */

	public static void send_FCM_Notification(String topic, String message) {

		try {

			// Create URL instance.
			URL url = new URL(FCM_URL);
			// create connection.
			HttpURLConnection conn;
			conn = (HttpURLConnection) url.openConnection();
			conn.setUseCaches(false);
			conn.setDoInput(true);
			conn.setDoOutput(true);
			// set method as POST or GET
			conn.setRequestMethod("POST");
			// pass FCM server key
			conn.setRequestProperty("Authorization", "key=" + AUTH_FCM_KEY);
			// Specify Message Format
			conn.setRequestProperty("Content-Type", "application/json");
			// Create JSON Object & pass value
			
			JSONObject data = new JSONObject();
			data.put("message", message);
			JSONObject json = new JSONObject();
		
			json.put("to", "/topics/" + topic);
			json.put("data", data);
			
			OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
			wr.write(json.toString());
			wr.flush();
			int status = 0;
			if (null != conn) {
				status = conn.getResponseCode();
			}

			if (status != 0) {
				if (status == 200) {
					// SUCCESS message
					BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
					System.out.println("Android Notification Response : " + reader.readLine());
					reader.close();
				} else if (status == 401) {
					// client side error
					System.out.println("Notification Response : TokenId : " + 401 + " Error occurred :");
				} else if (status == 501) {
					// server side error
					System.out.println("Notification Response : [ errorCode=ServerError ] TokenId : " + 501);
				} else if (status == 503) {
					// server side error
					System.out.println("Notification Response : FCM Service is Unavailable  TokenId : " + 503);
				}
			}
			wr.close();
			conn.disconnect();
		} catch (MalformedURLException mlfexception) {
			// Prototcal Error
			System.out.println("Error occurred while sending push Notification!.." + mlfexception.getMessage());
		} catch (IOException mlfexception) {
			// URL problem
			System.out.println(
					"Reading URL, Error occurred while sending push Notification!.." + mlfexception.getMessage());
		} catch (JSONException jsonexception) {
			// Message format error
			System.out.println(
					"Message Format, Error occurred while sending push Notification!.." + jsonexception.getMessage());
		} catch (Exception exception) {
			// General Error or exception.
			System.out.println("Error occurred while sending push Notification!.." + exception.getMessage());

		}
	}
}
