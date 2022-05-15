package testScripts.mobile;

import java.net.MalformedURLException;

import org.json.simple.JSONObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import commonFunctionLibrary.MobileFunctionLibrary;
import helperClasses.AppiumAllureListener;
import pageObjects.mobile.CalculatorObjects;

@Listeners({ AppiumAllureListener.class })
public class CalculatorTestEmulator extends MobileFunctionLibrary{
	
	CalculatorObjects calculatorObjects = PageFactory.initElements(getDriver(), CalculatorObjects.class);
	public JSONObject jsonDataObj;
	
	@BeforeClass
	public void setup() throws Exception {		
		initialize_driver("com.google.android.calculator", "com.android.calculator2.Calculator");		
	}

	@Test
	public void test1234() throws Exception {
		jsonDataObj= getTestData("testdata", "OrderDetails", 0);
		System.out.println((String)jsonDataObj.get("GuestEmail"));
		System.out.println("Calculate sum of two numbers");
		//Locate elements using By.name() to enter data and click +/= buttons 
		clickAction(calculatorObjects.button_one);
		clickAction(calculatorObjects.button_plus);
		clickAction(calculatorObjects.button_two);
		clickAction(calculatorObjects.button_equals);
		// Get the result text
		WebElement sumOfNumbersEle = getElement(calculatorObjects.button_finalValue);
		String sumOfNumbers = sumOfNumbersEle.getText();
		//verify if result is 3
		Assert.assertTrue(sumOfNumbers.endsWith("4"));
	}
}

