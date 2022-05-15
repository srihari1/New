package testScripts.web;

import org.json.simple.JSONObject;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import commonFunctionLibrary.WebFunctionLibrary;
import helperClasses.WebAllureListener;
import pageObjects.web.ChargesPage;
import pageObjects.web.SetupPage;
import pageObjects.web.LoginPage;

@Listeners({ WebAllureListener.class })
public class ARC_Login extends WebFunctionLibrary {

	public ChargesPage chargesPageObjects = PageFactory.initElements(getDriver(), ChargesPage.class);
	public LoginPage loginPageObjects = PageFactory.initElements(getDriver(), LoginPage.class);
	public SetupPage checkoutPageObjects = PageFactory.initElements(getDriver(), SetupPage.class);
	public JSONObject jsonDataObj;
	static String mainWindow;
	static Logger logger = Logger.getLogger(ARC_Login.class.getName());

	@BeforeClass
	public void setup() throws Exception {
		DOMConfigurator.configure("log4j.xml");
		initialize_driver();
		launchApplication();
	}

	@Test(priority = 1, description = "Login Application")
	public void login() throws Exception {
		jsonDataObj = getTestData("testdata", "OrderDetails", 0);
		logger.info("Entering Username in login username field");
		EnterText(loginPageObjects.LoginEmail, "srikanthbok.in@mouritech.com");
		logger.info("Entering password in login password field");
		EnterText(loginPageObjects.LoginPassword, (String) jsonDataObj.get("Password"));
		logger.info("clicked on Signon Button");
		clickAction(loginPageObjects.SignOnButton);
		logger.info("Click on send push button");
		clickAction(loginPageObjects.OktaSendPush);
		logger.info("clicked on Proceed button in login screen");
		clickAction(loginPageObjects.proceed);
	}
}
