package testScripts.web;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import commonFunctionLibrary.WebFunctionLibrary;
import helperClasses.WebAllureListener;
import pageObjects.web.ChargesPage;
import pageObjects.web.LeaseAdministrationPage;
import pageObjects.web.SetupPage;
import pageObjects.web.LoginPage;

@Listeners({ WebAllureListener.class })
public class ARC_AddChargeBatch extends WebFunctionLibrary {

	public ChargesPage chargesPageObjects = PageFactory.initElements(getDriver(), ChargesPage.class);
	public LoginPage loginPageObjects = PageFactory.initElements(getDriver(), LoginPage.class);
	public SetupPage setupPage = PageFactory.initElements(getDriver(), SetupPage.class);
	public LeaseAdministrationPage leaseAdministrationPage = PageFactory.initElements(getDriver(),
			LeaseAdministrationPage.class);
	static Logger logger = Logger.getLogger(ARC_AddChargeBatch.class.getName());
	public JSONObject jsonDataObj;
	static String mainWindow;
	static String childWindow1;

	@Test(priority = 1, description = "Select Receivable Role")
	public void receivableRole() throws Exception {
		clickAction(chargesPageObjects.roles);
		clickAction(chargesPageObjects.alliedAccountsReceivable);
	}

	@Test(priority = 2, description = "Create charge batch")
	public void createChangeBatch() throws Exception {
		clickAction(chargesPageObjects.charges);
		hover(chargesPageObjects.chargeFunctions);
		clickAction(chargesPageObjects.CreateChargeBatch);
		switchFrameByElement(chargesPageObjects.CreateChargeBatchIframe);
		EnterText(chargesPageObjects.CreateChargeBatchNotes, "test");
		clickAction(chargesPageObjects.CreateChargeBatchSave);
	}

	@Test(priority = 3, description = "Add a charge")
	public void addCharge() throws Exception {
		clickAction(chargesPageObjects.CreateChargeBatchToolbar);
		clickAction(chargesPageObjects.ChangeInvoiceChangeTo);
		switchFrameByElement(chargesPageObjects.ChangeInvoiceTenantIframe);
		clickAction(chargesPageObjects.ChangeInvoiceTenant);
		clickAction(chargesPageObjects.ChangeInvoiceTenantSelect);
		switchDefaultContent();
		switchFrameByElement(chargesPageObjects.CreateChargeBatchIframe);
		clickAction(chargesPageObjects.ChangeInvoiceChargeCode);
		switchFrameByElement(chargesPageObjects.ChangeInvoiceTenantIframe);
		clickAction(chargesPageObjects.ChangeInvoiceChargeCodeSelect);
		clickAction(chargesPageObjects.ChangeInvoiceTenantSelect);
		switchDefaultContent();
		switchFrameByElement(chargesPageObjects.CreateChargeBatchIframe);
		EnterText(chargesPageObjects.ChangeInvoiceChargeCodeNet, "5000");
		EnterText(chargesPageObjects.ChangeInvoiceChargeCodeNotes, "test");
		clickAction(chargesPageObjects.ChangeInvoiceSave);
		mainWindow = getWindow();
		switchDefaultContent();
	}

	@Test(priority = 4, description = "Add a backup")
	public void addbackup() throws Exception {
		clickAction(chargesPageObjects.charges);
		hover(chargesPageObjects.chargeFunctions);
		clickAction(chargesPageObjects.FindChargeBatches);
		switchFrameByElement(chargesPageObjects.CreateChargeBatchIframe);
		clickAction(chargesPageObjects.ChargeBatchFilterFind);
		EditBatch();
		clickAction(chargesPageObjects.ChargeBatchInvoicesEdit);
		clickAction(chargesPageObjects.ChargeInvoiceFunctions);
		clickAction(chargesPageObjects.ChargeInvoiceFunctionsAttachments);
		switchWindow(mainWindow);
		String child1 = getWindow();
		clickAction(chargesPageObjects.ChargeInvoiceAttachmentsUpload);
		switchsubchildWindow(mainWindow, child1);
		uploadBackup(mainWindow, child1);
		clickAction(chargesPageObjects.ChargeInvoiceAttachmentsUploadFileSave);
		closeWindow();
		switchWindow(child1);
		switchFrameByElement(chargesPageObjects.CreateChargeBatchIframe);
		clickAction(chargesPageObjects.Attachments);
		switchDefaultContent();
	}

	@Test(priority = 5, description = "Setup Legal Entity")
	public void setupLegalEntity() throws Exception {
		clickAction(setupPage.setup);
		hover(setupPage.legalEntity);
		clickAction(setupPage.reviewLegalEntity);
		switchFrameByElement(setupPage.legalEntityIframe);
		clickAction(setupPage.legalEntitySubmit);
		clickAction(setupPage.legalEntityNew);
		String leaglEntity = randomLeaglEntity();
		EnterText(setupPage.addLegalEntitycode, leaglEntity);
		dropDownSelectByValue(setupPage.addLegalEntityTax, "1");
		clickAction(setupPage.addLegalEntitySubmit);
		EnterText(setupPage.LegalEntityAddress, "ABC Test Road");
		EnterText(setupPage.LegalEntityCity, "Toronto");
		dropDownSelectByValue(setupPage.LegalEntityPostCode, "ON");
		EnterText(setupPage.LegalEntityZipCode, "M1W5RE");
		EnterText(setupPage.LegalEntityNotes, "test");
		dropDownSelectByValue(setupPage.LegalEntityDefaultSalesTranType, "1");
		dropDownSelectByValue(setupPage.LegalEntityDefaultPurchTranType, "3");
		EnterText(setupPage.LegalEntityHST, "test");
		clickAction(setupPage.LegalEntityTaxRegistered);
		clickAction(setupPage.legalEntitySave);
		switchDefaultContent();
	}

	@Test(priority = 6, description = "Property and search to Tenants")
	public void propertyAndSearchtoTenants() throws Exception {
		clickAction(leaseAdministrationPage.LeaseAdministration);
		hover(leaseAdministrationPage.Leases);
		clickAction(leaseAdministrationPage.ReviewLease);
		switchFrameByElement(leaseAdministrationPage.LeaseAdministrationIframe);
		clearText(leaseAdministrationPage.LeaseId);
		EnterText(leaseAdministrationPage.LeaseId, "t0000060");
		dropDownSelectByValue(leaseAdministrationPage.CommercialLeasesStatus, "Current");
		clickAction(leaseAdministrationPage.CommercialLeasesSubmit);
		clickAction(leaseAdministrationPage.Amendments);
		switchDefaultContent();
	}

	@Test(priority = 7, description = "Review the Batch Header")
	public void reviewBatchHeader() throws Exception {
		clickAction(chargesPageObjects.charges);
		hover(chargesPageObjects.chargeFunctions);
		clickAction(chargesPageObjects.FindChargeBatches);
		switchFrameByElement(chargesPageObjects.CreateChargeBatchIframe);
		clickAction(chargesPageObjects.ChargeBatchFilterFind);
		EditBatch();
		clickAction(chargesPageObjects.CreateChargeBatchSave);
		clearText(chargesPageObjects.BatchChargeTotalDeclared);
		EnterText(chargesPageObjects.BatchChargeTotalDeclared, "5650");
		clearText(chargesPageObjects.BatchChargeItemsDeclared);
		EnterText(chargesPageObjects.BatchChargeItemsDeclared, "1");
		clickAction(chargesPageObjects.CreateChargeBatchSave);
		clickAction(chargesPageObjects.BatchChargePost);
		alertOk();
		switchDefaultContent();
	}

	@Test(priority = 8, description = "Post a Charge into the previous month")
	public void posthargeIntoPreviousMonth() throws Exception {
		clickAction(chargesPageObjects.charges);
		hover(chargesPageObjects.chargeFunctions);
		clickAction(chargesPageObjects.FindChargeBatches);
		switchFrameByElement(chargesPageObjects.CreateChargeBatchIframe);
		clickAction(chargesPageObjects.ChargeBatchFilterFind);
		EditBatch();
		clickAction(chargesPageObjects.CreateChargeBatchToolbar);
		clickAction(chargesPageObjects.ChangeInvoiceChangeTo);
		switchFrameByElement(chargesPageObjects.ChangeInvoiceTenantIframe);
		clickAction(chargesPageObjects.ChangeInvoiceTenant);
		clickAction(chargesPageObjects.ChangeInvoiceTenantSelect);
		switchDefaultContent();
		switchFrameByElement(chargesPageObjects.CreateChargeBatchIframe);
		clearText(chargesPageObjects.ChargeInvoicePostMonth);
		EnterText(chargesPageObjects.ChargeInvoicePostMonth, "02/2022");
		clickAction(chargesPageObjects.ChangeInvoiceChargeCode);
		switchFrameByElement(chargesPageObjects.ChangeInvoiceTenantIframe);
		clickAction(chargesPageObjects.ChangeInvoiceChargeCodeSelect);
		clickAction(chargesPageObjects.ChangeInvoiceTenantSelect);
		switchDefaultContent();
		switchFrameByElement(chargesPageObjects.CreateChargeBatchIframe);
		EnterText(chargesPageObjects.ChangeInvoiceChargeCodeNet, "5000");
		EnterText(chargesPageObjects.ChangeInvoiceChargeCodeNotes, "test");
		clickAction(chargesPageObjects.ChangeInvoiceSave);
		clickActionWithJavascriptExecutor(chargesPageObjects.ChangeInvoiceSave);
		clickActionWithJavascriptExecutor(chargesPageObjects.CreateChargeBatchSave);
		String BatchChargeTotalEntered = getText(chargesPageObjects.BatchChargeTotalEntered);
		clearText(chargesPageObjects.BatchChargeTotalDeclared);
		EnterText(chargesPageObjects.BatchChargeTotalDeclared, BatchChargeTotalEntered);
		String BatchChargeItemsEntered = getText(chargesPageObjects.BatchChargeItemsEntered);
		clearText(chargesPageObjects.BatchChargeItemsDeclared);
		EnterText(chargesPageObjects.BatchChargeItemsDeclared, BatchChargeItemsEntered);
		clickActionWithJavascriptExecutor(chargesPageObjects.CreateChargeBatchSave);
		clickAction(chargesPageObjects.BatchChargePost);
		alertOk();
		switchDefaultContent();
	}

//	@Test(priority = 9, description = "Post a Charge into the next month")
//	public void posthargeIntoNextMonth() throws Exception {
//		clickAction(chargesPageObjects.charges);
//		hover(chargesPageObjects.chargeFunctions);
//		clickAction(chargesPageObjects.FindChargeBatches);
//		switchFrameByElement(chargesPageObjects.CreateChargeBatchIframe);
//		clickAction(chargesPageObjects.ChargeBatchFilterFind);
//		EditBatch();
//		clickAction(chargesPageObjects.CreateChargeBatchToolbar);
//		clickAction(chargesPageObjects.ChangeInvoiceChangeTo);
//		switchFrameByElement(chargesPageObjects.ChangeInvoiceTenantIframe);
//		clickAction(chargesPageObjects.ChangeInvoiceTenant);
//		clickAction(chargesPageObjects.ChangeInvoiceTenantSelect);
//		switchDefaultContent();
//		switchFrameByElement(chargesPageObjects.CreateChargeBatchIframe);
//		clearText(chargesPageObjects.ChargeInvoicePostMonth);
//		EnterText(chargesPageObjects.ChargeInvoicePostMonth, "04/2022");
//		clickAction(chargesPageObjects.ChangeInvoiceChargeCode);
//		switchFrameByElement(chargesPageObjects.ChangeInvoiceTenantIframe);
//		clickAction(chargesPageObjects.ChangeInvoiceChargeCodeSelect);
//		clickAction(chargesPageObjects.ChangeInvoiceTenantSelect);
//		switchDefaultContent();
//		switchFrameByElement(chargesPageObjects.CreateChargeBatchIframe);
//		EnterText(chargesPageObjects.ChangeInvoiceChargeCodeNet, "5000");
//		EnterText(chargesPageObjects.ChangeInvoiceChargeCodeNotes, "test");
//		clickAction(chargesPageObjects.ChangeInvoiceSave);
//		clickActionWithJavascriptExecutor(chargesPageObjects.ChangeInvoiceSave);
//		clickActionWithJavascriptExecutor(chargesPageObjects.CreateChargeBatchSave);
//		String BatchChargeTotalEntered = getText(chargesPageObjects.BatchChargeTotalEntered);
//		clearText(chargesPageObjects.BatchChargeTotalDeclared);
//		EnterText(chargesPageObjects.BatchChargeTotalDeclared, BatchChargeTotalEntered);
//		String BatchChargeItemsEntered = getText(chargesPageObjects.BatchChargeItemsEntered);
//		clearText(chargesPageObjects.BatchChargeItemsDeclared);
//		EnterText(chargesPageObjects.BatchChargeItemsDeclared, BatchChargeItemsEntered);
//		clickActionWithJavascriptExecutor(chargesPageObjects.CreateChargeBatchSave);
//		clickAction(chargesPageObjects.BatchChargePost);
//		alertOk();
//		switchDefaultContent();
//	}
}
