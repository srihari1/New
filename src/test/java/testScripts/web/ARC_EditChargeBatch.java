package testScripts.web;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import commonFunctionLibrary.WebFunctionLibrary;
import helperClasses.WebAllureListener;
import pageObjects.web.ChargesPage;

@Listeners({ WebAllureListener.class })
public class ARC_EditChargeBatch extends WebFunctionLibrary {

	public ChargesPage chargesPageObjects = PageFactory.initElements(getDriver(), ChargesPage.class);
	public JSONObject jsonDataObj;
	static Logger logger = Logger.getLogger(ARC_EditChargeBatch.class.getName());
	ARC_AddChargeBatch addcharge = new ARC_AddChargeBatch();

	@Test(priority = 9, description = "Edit the charge batch")
	public void editChangeBatch() throws Exception {
		clickAction(chargesPageObjects.charges);
		hover(chargesPageObjects.chargeFunctions);
		clickAction(chargesPageObjects.FindChargeBatches);
		switchFrameByElement(chargesPageObjects.CreateChargeBatchIframe);
		clickAction(chargesPageObjects.ChargeBatchFilterFind);
		EditBatch();
		clickAction(chargesPageObjects.ChargeBatchInvoicesEdit);
		clickAction(chargesPageObjects.ChargeBatchInvoiceDelete);
		alertOk();
	}

	@AfterClass
	public void closeBrowser() throws Exception {
		getDriver().quit();
	}

}
