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
	@Test(dataProvider="data-provider-sample1",dataProviderClass=DataProviderClass.class, groups={"send-receive-basics"})
    public static void Send_and_receive(String from, String to, String subject, String body) throws InterruptedException, IOException {

		GmailAccount user1 = new GmailAccount(from);
		String id = user1.sendEmail(to, subject,body).getId();
			
		GmailAccount user2 = new GmailAccount(to);
		Message m = user1.getEmail(id);
		Thread.sleep(2000);
		
		System.out.println("PASS -"+m.getRaw());
		System.out.println("PASS -"+m.toPrettyString().toString());
	}
	
	@Test(dataProvider="data-provider-sample1",dataProviderClass=DataProviderClass.class, groups={"send-only-from-user1"})
	public static void Test_User1_User2(String from, String to, String subject, String body) throws InterruptedException  {
		GmailAccount user1 = new GmailAccount(from);
		user1.sendEmail(to, subject,body);
			
	}
	
	@Test(dataProvider="data-provider-sample1",dataProviderClass=DataProviderClass.class, groups={"send-only-from-user2"})
	public static void Test_User2_User1(String to, String from, String subject, String body) throws InterruptedException  {
		GmailAccount user1 = new GmailAccount(from);
		user1.sendEmail(to, subject,body);
	}
}