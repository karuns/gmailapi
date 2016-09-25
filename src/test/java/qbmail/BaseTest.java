package qbmail;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import common.Log;

public abstract class BaseTest {
	
	protected static Log log;
	
	@BeforeSuite(alwaysRun=true)
	public void setUp() {
		// log= new Log();
	}
			
	@AfterSuite(alwaysRun=true)
	public void tearDown(){
		
	}
}
