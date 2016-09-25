package qbmail;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Stack;

import com.google.api.services.gmail.model.Message;

import common.Log;
import gclient.featureclass.QbMailUtils;
import gclient.pageclass.GmailAccount;

import org.json.JSONArray;
import org.testng.annotations.Test;

/* SEND and receive email thru google API
 * DONE -- send and receive basic validation 
 * Done Data provider
 * Done Jenkins cloud
 * code optimization
 * 		subject 
 * 		verification
 *      three emails 
 * 		attachment methods
 * code cleanup 
 * methods with basic attachments
 * 	More test cases
 * 	PPTs
 */

public class TestQBMail extends BaseTest {	
	
	@Test(dataProvider="userdata-provider-basictest",dataProviderClass=DataProviderClass.class, groups={"send-receive-basics"})
    public static void basicSendandReceiveTest(String from, String to, String subject, String body) throws InterruptedException, IOException {
		if(QbMailUtils.basicSendandVerify(from, to, subject,body)) {
			Log.testPassed(from+","+to+","+subject+","+body);
		}
		else {
			Log.testFailed(from+","+to+","+subject+","+body);
		}
	}
	
	@Test(dataProvider="userdata-provider-basictest2",dataProviderClass=DataProviderClass.class, groups={"send-receive-basics2"})
    public static void basicSendandReceiveTest2(String from, String to,String cc, String subject, String body) throws InterruptedException, IOException {
		if(QbMailUtils.basicSendandVerify(from, to,cc, subject,body)) {
			Log.testPassed(from+","+to+","+cc+","+subject+","+body);
		}
		else {
			Log.testFailed(from+","+to+","+cc+","+subject+","+body);
		}
	}

	
//	@Test(dataProvider="userdata-provider-multiemail",dataProviderClass=DataProviderClass.class, groups={"send-receive-basics"})
//    public static void multiemailSendandReceiveTest(String from, String to, String subject, String body) throws InterruptedException, IOException {
//		if(QbMailUtils.basicSendandVerify(from, to, subject,body)) {
//			Log.testPassed();
//		}
//		else {
//			Log.testFailed();
//		}
//	}
	
	
//	@Test(dataProvider="userdata-provider-basictest",dataProviderClass=DataProviderClass.class, groups={"send-only-from-user1"})
//	public static void Test_User1_User2(String from, String to, String subject, String body) throws InterruptedException  {
//		GmailAccount user1 = new GmailAccount(from);
//		user1.sendEmail(to, subject,body);
//			
//	}
//	
//	@Test(dataProvider="userdata-provider-basictest",dataProviderClass=DataProviderClass.class, groups={"send-only-from-user2"})
//	public static void Test_User2_User1(String to, String from, String subject, String body) throws InterruptedException  {
//		GmailAccount user1 = new GmailAccount(from);
//		user1.sendEmail(to, subject,body);
//	}
}