package testScripts.mobile;

import org.json.simple.JSONObject;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import commonFunctionLibrary.MobileFunctionLibrary;
import helperClasses.AppiumAllureListener;
import pageObjects.mobile.GapApplicationObjects;

@Listeners({ AppiumAllureListener.class })
public class GapTestChromeBrowser extends MobileFunctionLibrary {

	GapApplicationObjects gapApplicationObjects = PageFactory.initElements(getDriver(), GapApplicationObjects.class);
	public JSONObject jsonDataObj;

	@BeforeClass
	public void setup() throws Exception {
		
		initialize_driver("","");
//		launchApplication("https://www.gap.com");	
		launchApplication();	
	}

	@Test	
	public void test1234() throws Exception{
		jsonDataObj= getTestData("testdata", "OrderDetails", 0);
		System.out.println((String)jsonDataObj.get("GuestEmail"));
		clickAction(gapApplicationObjects.button_accept);
		clickAction(gapApplicationObjects.button_noThanks);
		clickAction(gapApplicationObjects.tab_OldNavy);
		//		Assert.assertEquals(false, true);		

	}
}

