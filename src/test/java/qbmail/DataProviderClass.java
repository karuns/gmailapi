package qbmail;
import org.testng.annotations.DataProvider;
public class DataProviderClass {
	
	static String u1 = "sohan.karun1@gmail.com";
	static String u2 = "sohan.karun2@gmail.com";
	static String u3 = "sohan.karun3@gmail.com";
	
	
	@DataProvider(name = "userdata-provider-basictest")
	public static Object[][] firstDataProvider() {
		return new Object[][] {
			{u1,u2,"Hi1","Hello from "+u1},
			{u1,u2,"Hi2","Hello from "+u1},
			{u1,u2,"Hi3","Hello from "+u1},
			{u1,u2,"Hi1","Hello from "+u1},
			{u1,u2,"Hi2","Hello from "+u1},
			{u1,u2,"Hi3","Hello from "+u1}
		};
	}
	
	@DataProvider(name = "userdata-provider-basictest2")
	public static Object[][] secondDataProvider() {
		return new Object[][] {
			{u1,u2,u3,"Hi1","Hello from "+u1},
			{u1,u2,u3,"Hi2","Hello from "+u1},
			{u1,u2,u3,"Hi3","Hello from "+u1}
		};
	}
	
	@DataProvider(name = "userdata-provider-multiemail")
	public static Object[][] thirdDataProvider() {
		return new Object[][] {
			{u1,u2+","+u3,"Hi1","Hello from "+u1},
			{u1,u2+","+u3,"Hi2","Hello from "+u1},
			{u1,u2+","+u3,"Hi3","Hello from "+u1}
		};
	}
	
//	@DataProvider(name = "data-provider-sample1")
//	public static Object[][] FirstDataProvider() {
//		return new Object[][] {
//			{"sohan.karun1@gmail.com","sohan.karun2@gmail.com", "Hi1","Hello from Sohan.karun1"},
//			{"sohan.karun1@gmail.com","sohan.karun2@gmail.com", "Hi2","Hello from Sohan.karun1"},
//			{"sohan.karun1@gmail.com","sohan.karun2@gmail.com", "Hi3","Hello from Sohan.karun1"}
//		};
//	}
}
