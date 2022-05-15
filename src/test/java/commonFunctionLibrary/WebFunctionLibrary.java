package commonFunctionLibrary;

import java.io.FileReader;
import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebFunctionLibrary {

	public static ThreadLocal<WebDriver> tdriver = new ThreadLocal<WebDriver>();
	public String browserName;
	public String url;
	public String testEnv;
	static Logger logger = Logger.getLogger(WebFunctionLibrary.class.getName());

	public WebDriver initialize_driver() {
		browserName = System.getProperty("BrowserName");
		url = System.getProperty("Url");
		testEnv = System.getProperty("TestEnv");
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.manage().window().maximize();
		tdriver.set(driver);
		return getDriver();
	}

	public static synchronized WebDriver getDriver() {
		return tdriver.get();
	}

	public void launchApplication() {
		getDriver().get(url);
	}

	public void clickAction(By objectProperty) throws Exception {
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(getDriver().findElement(objectProperty)));
		moveToElement(getDriver().findElement(objectProperty));
		wait.until(ExpectedConditions.elementToBeClickable(getDriver().findElement(objectProperty)));
		getDriver().findElement(objectProperty).click();
		logger.info("clicked on element " + objectProperty);
		Thread.sleep(3000);
	}

	public void clickActionWithJavascriptExecutor(By objectProperty) throws Exception {
		JavascriptExecutor executor = (JavascriptExecutor) getDriver();
		executor.executeScript("arguments[0].click();", getDriver().findElement(objectProperty));
		logger.info("clicked on element " + objectProperty);
		Thread.sleep(3000);
	}

	public void alertOk() {
		getDriver().switchTo().alert().accept();
	}

	public void uploadBackup(String mainWindow, String child1) {
		String ChildWindow = getDriver().getWindowHandle();
		if (!mainWindow.equalsIgnoreCase(ChildWindow) && !child1.equalsIgnoreCase(ChildWindow)) {
			getDriver().switchTo().window(ChildWindow);
			getDriver().findElement(By.xpath("//body[@id='Body1']//input[@id='File0']"))
					.sendKeys("C:\\Users\\sriharik\\Downloads\\backup.pdf");
			getDriver().findElement(By.xpath("//button[@id='YsiUpload_Button']")).click();
			getDriver().findElement(By.xpath("//button[@id='YsiClose_Button']")).click();
		}
		getDriver().switchTo().window(child1);
	}

	public void EditBatch() throws InterruptedException {
		List<WebElement> batches = getDriver().findElements(By.xpath("//table[@id='grdBatch_DataTable']//tr"));
		for (int i = 1; i <= batches.size(); i++) {
			Actions action = new Actions(getDriver());
			action.moveToElement(
					getDriver().findElement(By.xpath("(//table[@id='grdBatch_DataTable']//tr)[" + i + "]//td/span")))
					.perform();
			logger.info("compare Batch ID");
			if (i == batches.size()) {
				getDriver().findElement(By.xpath("(//table[@id='grdBatch_DataTable']//tr)[" + i + "]//td[7]/button"))
						.click();
			}
		}
		Thread.sleep(3000);
	}

	public void EnterText(By objectProperty, String text) throws InterruptedException {
		getDriver().findElement(objectProperty).sendKeys(text);
		logger.info("Enter Text on " + objectProperty);
		Thread.sleep(3000);
	}

	public void clearText(By objectProperty) {
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(20));
		wait.until(ExpectedConditions.elementToBeClickable(getDriver().findElement(objectProperty)));
		wait.until(ExpectedConditions.visibilityOf(getDriver().findElement(objectProperty)));
		getDriver().findElement(objectProperty).clear();
		logger.info("Cleared the text on " + objectProperty);
	}

	public String randomLeaglEntity() {
		String randomString = "478k-";
		String legalEntity = RandomStringUtils.random(8, randomString);
		return legalEntity;
	}

	public void dropDownSelectByValue(By objectProperty, String value) {
		Select dd = new Select(getDriver().findElement(objectProperty));
		dd.selectByValue(value);
		logger.info("Select Drop Down " + objectProperty);
	}

	public void switchFrameByIndex(int index) {
		logger.info("Switched to frame by Index");
		getDriver().switchTo().frame(index);
	}

	public void switchFrameByElement(By objectProperty) {
		logger.info("Switched to frame by element");
		WebElement ele = getDriver().findElement(objectProperty);
		getDriver().switchTo().frame(ele);
	}

	public void switchWindow(String mainWindowHandle) {
		Set<String> allWindowHandles = getDriver().getWindowHandles();
		Iterator<String> iterator = allWindowHandles.iterator();
		while (iterator.hasNext()) {
			String ChildWindow = iterator.next();
			if (!mainWindowHandle.equalsIgnoreCase(ChildWindow)) {
				getDriver().switchTo().window(ChildWindow);
			}
			logger.info("Switched to window " + ChildWindow);
		}
	}

	public void switchsubchildWindow(String mainWindow, String child1) {
		Set<String> allWindowHandles = getDriver().getWindowHandles();
		Iterator<String> iterator = allWindowHandles.iterator();
		while (iterator.hasNext()) {
			String ChildWindow = iterator.next();
			if (!mainWindow.equalsIgnoreCase(ChildWindow) && !child1.equalsIgnoreCase(ChildWindow)) {
				getDriver().switchTo().window(ChildWindow);
			}
			logger.info("Switched to child window " + ChildWindow);
		}
	}

	public void switchChildWindow(String ChildWindow) {
		getDriver().switchTo().window(ChildWindow);
		logger.info("Switched to child window " + ChildWindow);
	}

	public void switchWindowById(String window) {
		getDriver().switchTo().window(window);
		logger.info("Switched to child window " + window);

	}

	public void closeWindow() {

		getDriver().close();
		logger.info("closed window ");

	}

	public String getWindow() {
		String mainWindowHandle = getDriver().getWindowHandle();
		logger.info("Get Window ID ");
		return mainWindowHandle;
	}

	public void switchDefaultContent() {
		logger.info("Switch to default content");	
		getDriver().switchTo().defaultContent();
	}

	public WebElement getElement(By objectProperty) {
		return getDriver().findElement(objectProperty);
	}

	public String getText(By objectProperty) {
		logger.info("Get Text of " + objectProperty);
		return getDriver().findElement(objectProperty).getText();
	}

	public void moveToElement(WebElement webElement) throws Exception {
		Actions action = new Actions(getDriver());
		action.moveToElement(webElement).perform();
		logger.info("Move to element " + webElement);
	}

	public void hover(By objectProperty) throws Exception {
		WebElement element = getDriver().findElement(objectProperty);
		Actions action = new Actions(getDriver());
		action.moveToElement(element).perform();
		logger.info("Hover on element " + objectProperty);
	}

	public JSONObject getTestData(String jsonFileName, String recordName, int dataArrayNumber) throws Exception {
		JSONParser jsonParser = new JSONParser();
		FileReader reader = new FileReader(
				getCurrentWorkingDir() + "\\src\\test\\resources\\testData\\" + jsonFileName + "_" + testEnv + ".json");

		Object obj = jsonParser.parse(reader);
		JSONObject userloginJsonobj = (JSONObject) obj;
		JSONArray userloginArray = (JSONArray) userloginJsonobj.get(recordName);
		JSONObject users = (JSONObject) userloginArray.get(dataArrayNumber);
		return users;
	}

	public String getCurrentWorkingDir() {
		return System.getProperty("user.dir");
	}

}
