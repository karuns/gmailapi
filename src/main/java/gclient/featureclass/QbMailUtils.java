package gclient.featureclass;

import gclient.pageclass.GmailAccount;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Stack;

import org.json.JSONArray;

import com.google.api.services.gmail.model.Message;

import common.Log;

public class QbMailUtils {
	
	public static boolean basicSendandVerify(String from, String to, String cc, String subject, String body) throws InterruptedException, IOException {
		return basicSendandVerifyInernal(from, to, cc, subject, body);
	}
	
	public static boolean basicSendandVerify(String from, String to, String subject, String body) throws InterruptedException, IOException {
		return basicSendandVerifyInernal(from, to, "", subject, body);
	}
	
	
	private static boolean basicSendandVerifyInernal(String from, String to, String cc, String subject, String body) throws InterruptedException, IOException {
		ArrayList<String> recipientList = new ArrayList<String>();
		String strDelimiter = ",";
		boolean returnval = true;
		subject = getCurrenttimeStamp()+": "+subject; //adding time stamp to uniquely identify subject
		
		// creating from object
		GmailAccount user1 = new GmailAccount(from);
		
		if(cc.isEmpty()) {
			user1.sendEmail(to, subject,body);
			recipientList.add(to);
			
		}
		else {
			user1.sendEmail(to,cc,subject,body);
			recipientList.add(to);
			recipientList.add(cc);
		}
		Thread.sleep(5000);
		
		// Creating to object and going thru each account and verifying
		Stack<GmailAccount> toList = new Stack<GmailAccount>();
		for (int i=0; i<recipientList.size(); i++) {
			toList.push((new GmailAccount(recipientList.get(i))));
		}
		
		//Verify Emails ar all receiver's end
		while(!toList.isEmpty()) {
			Log.info("verifying ("+toList.peek()+","+from+","+subject+","+body+")");
			returnval = verifyEmail(toList.pop(), from, subject, body) && returnval;
		}
		return returnval;
	}
	
	public static String getCurrenttimeStamp() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
		Date date = new Date();
		return dateFormat.format(date).toString(); //20140806_155948
	}
	
	public static boolean verifyEmail(GmailAccount user, String from, String subject, String body) throws IOException{
		Message m = user.getEmailMatchingQuery("subject:"+subject);
		boolean returnVal = true;
		//Log.pass(m.toPrettyString());
		
		if(m!=null) {	
			String temp;
			temp = getPayLoadData(m,"From");
			if (!temp.contentEquals(from)) {
				Log.error("message field (From) doesn't match. Expected is ("+from+"). However, actual is ("+temp+") ");
				returnVal = false;
			}
			
			temp = getPayLoadData(m,"Subject");
			if (!temp.contentEquals(subject)) {
				Log.error("message field (Subject) doesn't match. Expected is ("+subject+"). However, actual is ("+temp+") ");
				returnVal = false;
			}
			
			temp = m.getSnippet();
			if (!temp.contentEquals(body)) {
				Log.error("message field (Body) doesn't match. Expected is ("+body+"). However, actual is ("+temp+") ");
				returnVal = false;
			}
		}
		else {
			Log.info("message is null");
			returnVal = false;
		}
		return returnVal;
	}
	
	static String getPayLoadData(Message m , String key) {
		JSONArray ja = new JSONArray(m.getPayload().get("headers").toString());
		boolean checked = false;
		String value = "key does not exist";
		for(int i = 0; i<ja.length() && !checked; i++) {
			if(ja.getJSONObject(i).getString("name").contentEquals(key)) {
				value = ja.getJSONObject(i).getString("value");
				checked= true;
			}
		}
		return value;
	}
}
