package pageObjects.web;

import org.openqa.selenium.By;

public class ChargesPage {
	public By roles = By.xpath("//div[@id='side-menu-wrap']/ul/li[@id='mi0']");
	public By alliedAccountsReceivable = By.xpath("(//div[@id='side-menu-wrap']/ul[@id='sm0']/li)[11]");
	public By charges = By.xpath("//div[@id='side-menu-wrap']/ul/li[@id='mi2']/a");
	public By chargeFunctions = By.xpath("//div[@id='side-menu-wrap']//ul//li[@id='mi2-1']/a");
	public By CreateChargeBatch = By.xpath("(//div[@id='side-menu-wrap']//ul[@id='sm2-1']//li)[1]/a");
	public By FindChargeBatches = By.xpath("(//div[@id='side-menu-wrap']//ul[@id='sm2-1']//li)[2]/a");
	public By CreateChargeBatchNotes = By.xpath("//textarea[@id='Notes_TextBox']");
	public By CreateChargeBatchSave = By.xpath("//button[@id='YsiPageButtons1_SaveButton_Button']");
	public By CreateChargeBatchToolbar = By.xpath("(//div[@id='grdCharge_DataGridContainer']//img)[1]");
	public By ChangeInvoiceChangeTo = By.xpath("//a[@id='PersonId_LookupLink']");
	public By CreateChargeBatchIframe = By.xpath("//iframe[@id='filter']");
	public By ChangeInvoiceTenantIframe = By.xpath("//iframe[@id='popupiframe']");
	public By ChangeInvoiceTenant = By.xpath("//tr[@id='row39']/td/input");
	public By ChangeInvoiceTenantSelect = By.xpath("//button[@id='cmdOK_Button']");
	public By ChangeInvoiceChargeCode = By
			.xpath("//button[@id='grdDetail_DataTable_row0_ChargeCodeIdLookup_ChargeCodeId_Button']");
	public By ChangeInvoiceChargeCodeSelect = By.xpath("//tr[@id='row9']/td/input");
	public By ChangeInvoiceChargeCodeNet = By.xpath("//input[@id='grdDetail_DataTable_row0_Amount']");
	public By ChangeInvoiceChargeCodeNotes = By.xpath("//textarea[@id='grdDetail_DataTable_row0_Notes']");
	public By ChangeInvoiceSave = By.xpath("//button[@id='cmdSave_Button']");
	public By ChargeBatchId = By.xpath("//span[@id='lblId_Label']");
	public By ChargeBatchFilterFind = By.xpath("//button[@id='cmdFind_Button']");
	public By ChargeBatchInvoiceDelete = By.xpath("//button[@id='cmdDelete_Button']");
	public By ChargeBatchInvoicesEdit = By.xpath("//tr[@id='grdCharge_DataTable_row0']//button");
	public By ChargeInvoiceFunctions = By.xpath("(//div[@class='fnBtnContainerDIV']//a)[1]");
	public By ChargeInvoiceFunctionsAttachments = By.xpath("(//div[@class='fnBtnContainerDIV']//a)[2]");
	public By ChargeInvoiceAttachmentsUpload = By.xpath("//button[@id='btnUpload_Button']");
	public By ChargeInvoiceAttachmentsUploadFile = By.xpath("//input[@id='File0']");
	public By ChargeInvoiceAttachmentsUploadFileSave = By.xpath("//button[@id='btnSave_Button']");
	public By ChargeInvoiceAttachmentsUploadFileClose = By.xpath("//button[@id='btnClose_Button']");
	public By BatchChargeTotalEntered = By.xpath("//span[@id='AmountEntered_Label']");
	public By BatchChargeTotalDeclared = By.xpath("//input[@id='AmountDeclared_TextBox']");
	public By BatchChargeItemsEntered = By.xpath("//span[@id='CountEntered_Label']");
	public By BatchChargeItemsDeclared = By.xpath("//input[@id='CountDeclared_TextBox']");
	public By BatchChargePost = By.xpath("//button[@id='cmdPost_Button']");
	public By ChargeInvoicePostMonth = By.xpath("//input[@id='PostMonth_TextBox']");
	public By ChargeClick = By.xpath("//span[@id='lblStatus_Label']");
	public By Attachments = By.xpath("//div[@class='secondary-btn']/a");
}
