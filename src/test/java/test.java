import java.io.IOException;

import gclient.GmailAccount;

import org.testng.annotations.Test;
/*
 * send and receive basic validation 
 * Data provider
 * Jenkins cloud
 * methods with basic attachments
 * 
 */


import com.google.api.services.gmail.model.Message;

public class test {
//	@Test
//    public static void Test_User1_User2() throws InterruptedException  {
//		GmailAccount user1 = new GmailAccount("sohan.karun1@gmail.com");
//		user1.sendEmail("sohan.karun2@gmail.com", "hyrsd","Hello from Sohan.karun1");
//		
//	}
//	
//	@Test
//    public static void Test1() throws InterruptedException {
//		Thread.sleep(5000);
//		GmailAccount user2 = new GmailAccount("sohan.karun2@gmail.com");
//		user2.sendEmail("sohan.karun1@gmail.com", "eyrsd","Hello from Sohan.karun2");
//	}
	
	@Test
    public static void Test3() throws InterruptedException, IOException {

			GmailAccount user1 = new GmailAccount("sohan.karun1@gmail.com");
			String id = user1.sendEmail("sohan.karun2@gmail.com", "nhyrsd","Hello from Sohan.karun3").getId();
			
		GmailAccount user2 = new GmailAccount("sohan.karun2@gmail.com");
		Message m = user1.getEmail(id);
		Thread.sleep(5000);
		System.out.println(m.getRaw());
		System.out.println(m.toPrettyString().toString());
		
		
		
		
		//user2.sendEmail("sohan.karun1@gmail.com", "eyrsd","Hello from Sohan.karun2");
	}
	
		
//		MimeMessage m1 = SendEmail.createEmail("sohan.karun2@gmail.com","sohan.karun1@gmail.com", "heysd","Hello from Sohan.karun2");
//		user1.sendEmail(m1);
		
		
		
		
		//se.createEmail("sohan.karun2@gmail.com",user, "heysd","Hello from Sohan.karun2")
    	// Building new authorized API client service.
//        Gmail service = getGmailService();
//        String user = "me";
//        SendEmail se = new SendEmail();
//        SendEmail.sendMessage(service, "me",  se.createEmail("sohan.karun2@gmail.com", "me", "hey","Hello from Sohan.karun2"));
//        
//        UserAccount ua1 = new UserAccount("emailid", secret key)
//        UserAccount ua2 = new UserAccount("emailid", secret key)
//        Email em = new Email();
//        ua1.sendemail(email)
       
       
        
 
}