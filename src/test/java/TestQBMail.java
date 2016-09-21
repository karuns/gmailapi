import java.io.IOException;
import com.google.api.services.gmail.model.Message;
import gclient.GmailAccount;
import org.testng.annotations.Test;

/* SEND and receive email thru google API
 * DONE -- send and receive basic validation 
 * Done Data provider
 * Done Jenkins cloud
 * methods with basic attachments
 * More test cases
 * PPTs
 * 
 */

public class TestQBMail {	
	@Test(enabled=true, dataProvider="data-provider-sample1",dataProviderClass=DataProviderClass.class)
    public static void Test3(String from, String to, String subject, String body) throws InterruptedException, IOException {

		GmailAccount user1 = new GmailAccount(from);
		String id = user1.sendEmail(to, subject,body).getId();
			
		GmailAccount user2 = new GmailAccount("sohan.karun2@gmail.com");
		Message m = user1.getEmail(id);
		Thread.sleep(2000);
		
		System.out.println("PASS -"+m.getRaw());
		System.out.println("PASS -"+m.toPrettyString().toString());
	}
	
	@Test(enabled=false)
	public static void Test_User1_User2() throws InterruptedException  {
		GmailAccount user1 = new GmailAccount("sohan.karun1@gmail.com");
		user1.sendEmail("sohan.karun2@gmail.com", "hyrsd","Hello from Sohan.karun1");
			
	}
	
	@Test(enabled=false)
	public static void Test1() throws InterruptedException {
		Thread.sleep(5000);
		GmailAccount user2 = new GmailAccount("sohan.karun2@gmail.com");
		user2.sendEmail("sohan.karun1@gmail.com", "eyrsd","Hello from Sohan.karun2");
	}
   
}