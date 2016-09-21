import org.testng.annotations.DataProvider;
public class DataProviderClass {
	@DataProvider(name = "data-provider-sample1")
	public static Object[][] FirstDataProvider() {
		return new Object[][] {
			{"sohan.karun1@gmail.com","sohan.karun2@gmail.com", "Hi1","Hello from Sohan.karun1"},
			{"sohan.karun1@gmail.com","sohan.karun2@gmail.com", "Hi2","Hello from Sohan.karun1"},
			{"sohan.karun1@gmail.com","sohan.karun2@gmail.com", "Hi3","Hello from Sohan.karun1"}
		};
	}
}
