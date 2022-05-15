package commonFunctionLibrary;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.TimeZone;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.clipboard.HasClipboard;

public class MobileFunctionLibrary{

	public static ThreadLocal<AppiumDriver> tdriver = new ThreadLocal<AppiumDriver>();


	public String platformName;
	public String platformVersion;	
	public String deviceName;
	public String udid;
	public String applicationType;
	public String url;
	public String testEnv;
	public String browserName;

	public AppiumDriver initialize_driver(String appPackage, String appActivity) throws MalformedURLException{
	
		System.out.println("PlatformName"+System.getProperty("PlatformName"));
		System.out.println("PlatformVersion"+System.getProperty("PlatformVersion"));
		System.out.println("DeviceName"+System.getProperty("DeviceName"));
		System.out.println("Udid"+System.getProperty("Udid"));
		System.out.println("ApplicationType"+System.getProperty("ApplicationType"));
		System.out.println("BrowserName"+System.getProperty("BrowserName"));
		System.out.println("Url"+System.getProperty("Url"));
		System.out.println("TestEnv"+System.getProperty("TestEnv"));

		platformName=System.getProperty("PlatformName");
		platformVersion=System.getProperty("PlatformVersion");
		deviceName=System.getProperty("DeviceName");
		udid=System.getProperty("Udid");
		applicationType=System.getProperty("ApplicationType");//WebView/Native
		browserName=System.getProperty("BrowserName");
		url=System.getProperty("Url");
		testEnv=System.getProperty("TestEnv");

		DesiredCapabilities cap = new DesiredCapabilities();		
		cap.setCapability("platformName", platformName);	
		cap.setCapability("platformVersion", platformVersion);
		cap.setCapability("deviceName", deviceName);
		cap.setCapability("udid", udid);

		if("Android".equalsIgnoreCase(platformName)){
			if("WebView".equalsIgnoreCase(applicationType)) {
				if("Chrome".equalsIgnoreCase(browserName)) {					

					cap.setCapability("appPackage", "com.android.chrome");
					cap.setCapability("appActivity", "com.google.android.apps.chrome.Main");  

//					cap.setCapability("platformName", "Android");	
//					cap.setCapability("platformVersion", "11");
//					cap.setCapability("deviceName", "Pixel 2 API 30");
//					cap.setCapability("udid", "emulator-5554");
//					cap.setCapability("appPackage", "com.android.chrome");
//					cap.setCapability("appActivity", "com.google.android.apps.chrome.Main");  
				}
			}else if("Native".equalsIgnoreCase(applicationType)) {
				cap.setCapability("appPackage", appPackage);
				cap.setCapability("appActivity", appActivity); 
			}
		}
		URL url = new URL("http://127.0.0.1:4723/wd/hub");

		AppiumDriver driver =new AppiumDriver(url, cap);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		System.out.println("Application Started");
		tdriver.set(driver);
		return getDriver();
	}
	public static synchronized AppiumDriver getDriver(){
		return tdriver.get();
	}

	public void launchApplication() {
		getDriver().get(url);	
	}


	public void clickAction(By objectProperty) {

		getDriver().findElement(objectProperty).click();
	}

	public WebElement getElement(By objectProperty) {

		return getDriver().findElement(objectProperty);
	}


	public AppiumDriver driver=null;
	Robot re = null;

	public void toBack(AppiumDriver driver) {
		TouchActions tc = new TouchActions(driver);
		tc.sendKeys(Keys.BACK_SPACE);
	}

	// LongPress
	public void longPress(AppiumDriver driver, String locatorvalue) {
		TouchActions action = new TouchActions(driver);
		action.longPress(driver.findElement(By.xpath(locatorvalue)));
		action.perform();
	}

	// ScrollDown
	public void scrollDown(AppiumDriver driver) {
		JavascriptExecutor js = driver;
		HashMap<String, String> scrollObject = new HashMap<String, String>();
		scrollObject.put("direction", "down");
		js.executeScript("mobile: scroll", scrollObject);
	}

	// ScrollUp
	public void scrollup(AppiumDriver driver) {
		JavascriptExecutor js = driver;
		HashMap<String, String> scrollObject = new HashMap<String, String>();
		scrollObject.put("direction", "up");
		js.executeScript("mobile: scroll", scrollObject);

	}

	//	public  boolean isElementPresent(AppiumDriver driver, Properties prop, List<String> columns) {
	//		try {
	//
	//			return driver.findElements(WebFunctionLibrary.getLocator(prop, columns.get(2), columns.get(3))).size() > 0;
	//		} catch (Exception e) {
	//			System.out.println("INFO : Element is not displayed to clickable");
	//			return false;
	//		}
	//
	//	}

	public void pasteTxt(AppiumDriver driver) {
		try {
			//			driver.hideKeyboard();
		} catch (Exception ign) {
		}

		String pasteTxt = ((HasClipboard) driver).getClipboardText();
		System.out.println("Paste content" + pasteTxt);
	}

	public  String pasteTxt() {
		String pasteValue = null;
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		DataFlavor flavor = DataFlavor.stringFlavor;
		if (clipboard.isDataFlavorAvailable(flavor)) {
			try {
				pasteValue = (String) clipboard.getData(flavor);
				System.out.println(pasteValue);
			} catch (UnsupportedFlavorException e) {
				System.out.println(e);
			} catch (IOException e) {
				System.out.println(e);
			}
		}
		return pasteValue;
	}

	public  By getByValue(WebElement element) {
		By by = null;
		// System.out.println("element=" + element);
		String byValue[] = element.toString().split("By.chained");
		String locator = byValue[1].replaceAll("[({})]*", "").split(":")[0].trim();
		String value = byValue[1].replaceAll("[({})]*", "").split(" ")[1].trim();

		switch (locator.toLowerCase()) {
		case "by.id":
			by = By.id(value);
			break;
		case "by.className":
			by = By.className(value);
			break;
		case "by.xpath":
			by = By.xpath(value);
			break;
		case "by.cssSelector":
			by = By.cssSelector(value);
			break;
		case "by.name":
			by = By.name(value);
			break;
		default:
			throw new IllegalStateException("locator : " + locator + " not found!!!");
		}
		return by;
	}

	public  void threadSleep(int timeInSeconds) {
		try {
			Thread.sleep(timeInSeconds * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public  void isElementLoaded(WebElement elementToBeLoaded, AppiumDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(elementToBeLoaded));
	}

	public  By locatortype(String type, String value) {

		By locName = null;
		if (type.equalsIgnoreCase("xPath")) {
			locName = By.xpath(value);
		} else if (type.equalsIgnoreCase("id")) {
			locName = By.id(value);
		} else if (type.equalsIgnoreCase("linkText")) {
			locName = By.linkText(value);
		} else if (type.equalsIgnoreCase("classname")) {
			locName = By.className(value);
		} else if (type.equalsIgnoreCase("name")) {
			locName = By.name(value);
		} else
			locName = By.partialLinkText(value);
		return locName;

	}

	public boolean isWebElementPresent(By locator, int maxWaitTime, AppiumDriver driver) {
		Boolean flag = false;
		int timeOut = 0;
		while (timeOut < maxWaitTime) {
			if (driver.findElements(locator).size() > 0) {
				flag = true;
				break;
			}

			timeOut = timeOut + 2;

		}

		return flag;

	}

	public  boolean isWebElementVisible(String path, String type, AppiumDriver driver) {
		Boolean flag = false;
		if (driver.findElements(locatortype(type, path)).size() > 0) {
			flag = true;
		}

		return flag;

	}

	public  boolean writeInInput(String path, String type, String data, AppiumDriver driver) {
		WebElement element = driver.findElement(locatortype(type, path));
		if (element.isDisplayed()) {
			// element.clear();
			element.sendKeys(data);
			//			driver.hideKeyboard();

			// System.out.println("Value Entered");
			return true;
		} else {
			return false;
		}
	}

	public  boolean hideKeyboard(AppiumDriver driver) {

		//		driver.hideKeyboard();
		return true;

	}

	//	public  boolean type(AppiumDriver driver, Properties prop, List<String> columns, String data) {
	//
	//		WebElement element = driver.findElement(WebFunctionLibrary.getLocator(prop, columns.get(2), columns.get(3)));
	//		if (element == null) {
	//			return false;
	//		} else {
	//			// element.clear();
	//			element.sendKeys(data);
	//			hideKeyboard(driver);
	//
	//			return true;
	//		}
	//
	//	}

	public  boolean setValue(String path, String type, String data, AppiumDriver driver) {
		WebElement element = driver.findElement(locatortype(type, path));
		element.sendKeys(data);
		System.out.println("Value Entered");
		return true;

	}

	public  boolean enter(AppiumDriver driver) throws IOException {
		Runtime.getRuntime().exec("adb shell input keyevent 66");
		return true;

	}

	public  boolean clearAppData(AppiumDriver driver) throws IOException {
		Runtime.getRuntime().exec("adb shell pm clear com.cloudfmgroup.cloudFMApp");
		return true;

	}

	//	public  boolean swipeIOS(AppiumDriver driver, Properties prop, List<String> columns,
	//			String data) {
	//		MobileElement element = driver.findElement(WebFunctionLibrary.getLocator(prop, columns.get(2), columns.get(3)));
	//		element.setValue(data);
	//
	//		System.out.println("Value Entered");
	//		return true;
	//
	//	}

	//	public  boolean enterInput(AppiumDriver driver, Properties prop, List<String> columns,
	//			String data) {
	//		WebElement element = driver.findElement(WebFunctionLibrary.getLocator(prop, columns.get(2), columns.get(3)));
	//
	//		((JavascriptExecutor) driver).executeScript("arguments[0].value = arguments[1]", element, data);
	//
	//		System.out.println("Value Entered");
	//		return true;
	//
	//	}

	public  boolean click(String path, String type, AppiumDriver driver)
			throws InterruptedException {
		Thread.sleep(1500);

		// WebElement element = (explicitWaitForElement(path, type));
		WebElement element = driver.findElement(locatortype(type, path));
		element.click();
		return true;
	}

	//	public WebElement getWebElement(AppiumDriver driver, Properties prop, List<String> columns) {
	//
	//		return driver.findElement(WebFunctionLibrary.getLocator(prop, columns.get(2), columns.get(3)));
	//	}

	public  WebElement getWebElement2(By locator, AppiumDriver driver) {

		return driver.findElement(locator);
	}

	public boolean tap(AppiumDriver driver, Properties prop, List<String> columns) {
		// MobileElement element =
		// driver.findElement(WebFunctionLibrary.getLocator(prop, columns.get(2),
		// columns.get(3)));
		// TouchAction action = new TouchAction(driver);
		// action.tap(element).perform();
		return true;
	}

	public boolean verifyAllVauesOfDropDown(String path, String type, String data) {

		boolean flag = false;
		WebElement element = explicitWaitForElement(path, type, null);
		List<WebElement> options = element.findElements(By.tagName("option"));
		String temp = data;
		String allElements[] = temp.split(",");
		String actual;
		for (int i = 0; i < allElements.length; i++) {

			System.out.println("Actual : " + options.get(i).getText());

			System.out.println("Expected: " + allElements[i].trim());
			actual = options.get(i).getText().trim();
			if (actual.equals(allElements[i].trim())) {
				flag = true;
			} else {
				flag = false;
				break;
			}
		}

		return flag;

	}

	public boolean verifyCurrentDateInput(String path, String type) {
		boolean flag = false;
		WebElement element = explicitWaitForElement(path, type, null);
		String actual = element.getAttribute("value").trim();
		DateFormat DtFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date date = new Date();
		DtFormat.setTimeZone(TimeZone.getTimeZone("US/Central"));
		String expected = DtFormat.format(date).toString().trim();
		if (actual.trim().contains(expected)) {
			flag = true;

		}
		return flag;

	}

	public boolean selectList(final String path, String type, String data) {

		Boolean flag = false;

		WebElement select = explicitWaitForElement(path, type, null);

		List<WebElement> options = select.findElements(By.tagName("option"));
		String expected = data.trim();
		System.out.println("Expected: " + expected);
		for (WebElement option : options) {

			String actual = option.getText().trim();
			System.out.println("Actual: " + actual);
			if (actual.equals(expected)) {

				option.click();
				flag = true;
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				return flag;
			}
		}

		return flag;
	}

	public boolean verifyDropdownSelectedValue(String path, String type, String data) {

		Boolean flag = false;
		WebElement select = explicitWaitForElement(path, type, null);

		Select sel = new Select(select);
		String defSelectedVal = sel.getFirstSelectedOption().getText();

		if (defSelectedVal.trim().equals(data.trim())) {
			flag = true;

			return flag;
		} else {
			return flag;
		}
	}

	public  boolean verifyElementSize(String path, String type, int size, AppiumDriver driver) {

		List elements = driver.findElements(locatortype(type, path));

		if (elements.size() == size) {
			System.out.println("Element is Present " + size + "times");
			return true;
		} else {
			System.out.println("Element is not Present with required size");

			return false;
		}
	}

	public  boolean uploadFilesUsingSendKeys(String path, String type, String data,
			AppiumDriver driver) throws InterruptedException {
		WebElement element = driver.findElement(locatortype(type, path));
		element.clear();
		element.sendKeys(System.getProperty("user.dir") + "\\src\\test\\resources\\uploadFiles\\" + data);
		System.out.println("Value Entered");
		return true;

	}

	public  boolean writeInInputCharByChar(String path, String type, String data,
			AppiumDriver driver) throws InterruptedException {
		WebElement element = driver.findElement(locatortype(type, path));
		element.clear();
		String b[] = data.split("");

		for (int i = 0; i < b.length; i++) {

			element.sendKeys(b[i]);
			Thread.sleep(1000);

		}
		System.out.println("Value Entered");
		return true;

	}

	public  boolean isRadioSelected(String path, String type) {

		WebElement element = (explicitWaitForElement(path, type, null));
		if (element.isSelected()) {
			return true;
		} else {

			return false;
		}
	}

	public  boolean isRadioNotSelected(String path, String type) {

		WebElement element = (explicitWaitForElement(path, type, null));
		if (element.isSelected()) {
			return false;
		} else {

			return true;
		}
	}

	public  boolean clearInput(String path, String type, AppiumDriver driver) {
		WebElement element = driver.findElement(locatortype(type, path));

		element.clear();

		System.out.println("input field cleared");
		return true;

	}

	public boolean delDirectory() {
		File delDestination = new File(System.getProperty("user.dir") + "\\src\\test\\resources\\downloadFile");
		if (delDestination.exists()) {
			File[] files = delDestination.listFiles();
			for (int i = 0; i < files.length; i++) {
				if (files[i].isDirectory()) {
					delDirectory();
				} else {
					files[i].delete();
				}
			}
		}
		return (delDestination.delete());
	}

	public boolean verifyCssProperty(String path, String type, String data) {

		String property[] = data.split(":", 2);
		String exp_prop = property[0];
		String exp_value = property[1];
		boolean flag = false;
		String prop = (explicitWaitForElement(path, type, null)).getCssValue(exp_prop);
		System.out.println(prop);

		if (prop.trim().equals(exp_value.trim())) {
			flag = true;
			return flag;
		}

		else {
			return flag;

		}

	}

	//	public  boolean switchContext(AppiumDriver driver) {
	//		boolean colFlag1 = false;
	//		Set<String> contextNames = driver.getContextHandles();
	//		for (String contextName : contextNames) {
	//			if (contextName.contains("WEBVIEW")) {
	////				driver.context(contextName);
	//				// System.out.println("switched to webview");
	//				colFlag1 = true;
	//				break;
	//			}
	//
	//		}
	//		return colFlag1;
	//
	//	}

	//	public  boolean switchContext1(AppiumDriver driver) {
	//		boolean colFlag1 = false;
	//		Set<String> contextNames = driver.getContextHandles();
	//		for (String contextName : contextNames) {
	//			if (contextName.contains("NATIVE")) {
	//				driver.context(contextName);
	//				// System.out.println("switched to native");
	//				colFlag1 = true;
	//				break;
	//			}
	//
	//		}
	//		return colFlag1;
	//
	//	}

	//	public  void HideKeyboard(AppiumDriver driver) {
	//		driver.hideKeyboard();
	//	}

	public  void SpiralWheel(AppiumDriver driver, By locator) {
		// String txt1 = "PRE_UAT Test Location";
		// MobileElement el1 = driver.findElement(locator);
		JavascriptExecutor js = driver;
		HashMap<String, String> scrollObject = new HashMap<String, String>();
		scrollObject.put("direction", "down");
		js.executeScript("mobile: scroll", scrollObject);

	}

	public  String verifyCurrentDate() {

		DateFormat DtFormat = new SimpleDateFormat("MMM dd yyyy");
		Date date = new Date();
		DtFormat.setTimeZone(TimeZone.getTimeZone("BST"));
		String expected = DtFormat.format(date).toString().trim();
		return expected;
	}

	public  WebElement explicitWaitForElement(String path, String type, AppiumDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, (10));

		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locatortype(type, path)));

		return element;

	}

	//	public void runBackGround(Duration time, AppiumDriver driver) {
	//		driver.runAppInBackground(time);
	//	}

	/* To ZoomOut */
	public void robotZoomOut() {
		try {
			re = new Robot();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		re.keyPress(KeyEvent.VK_CONTROL);
		re.keyPress(KeyEvent.VK_SUBTRACT);
		re.keyRelease(KeyEvent.VK_SUBTRACT);
		re.keyRelease(KeyEvent.VK_CONTROL);
	}

	/* To ZoomIn */
	public void robotZoomIn() {
		try {
			re = new Robot();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		re.keyPress(KeyEvent.VK_CONTROL);
		re.keyPress(KeyEvent.VK_ADD);
		re.keyRelease(KeyEvent.VK_ADD);
		re.keyRelease(KeyEvent.VK_CONTROL);
	}

	/* To ScrollUp using ROBOT */
	public void robotScrollUp(By locator, String message) {
		try {
			re = new Robot();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		re.keyPress(KeyEvent.VK_PAGE_UP);
		re.keyRelease(KeyEvent.VK_PAGE_UP);
	}

	/* To ScrollDown using ROBOT */
	public void robotScrollDown(By locator, String message) throws Exception {
		re = new Robot();
		re.keyPress(KeyEvent.VK_PAGE_DOWN);
		re.keyRelease(KeyEvent.VK_PAGE_DOWN);
	}

	public JSONObject getTestData(String jsonFileName, String recordName, int dataArrayNumber ) throws Exception {
		JSONParser jsonParser = new JSONParser();
		FileReader reader = new FileReader(getCurrentWorkingDir()+"\\src\\test\\resources\\testData\\"+jsonFileName+"_"+testEnv+".json");


		Object obj = jsonParser.parse(reader);
		JSONObject userloginJsonobj=(JSONObject)obj;
		JSONArray userloginArray = (JSONArray)userloginJsonobj.get(recordName);
		JSONObject users= (JSONObject)userloginArray.get(dataArrayNumber);
		return users;
	}

	public String getCurrentWorkingDir() {
		return System.getProperty("user.dir");
	}
}
