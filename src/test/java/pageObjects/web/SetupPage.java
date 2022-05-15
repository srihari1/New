package pageObjects.web;

import org.openqa.selenium.By;

public class SetupPage {
	public By setup = By.xpath("//div[@id='side-menu-wrap']/ul/li[@id='mi9']");
	public By legalEntity = By.xpath("//div[@id='side-menu-wrap']//ul[@id='sm9']//li[@id='mi9-1']");
	public By reviewLegalEntity = By.xpath("//div[@id='side-menu-wrap']//ul[@id='sm9-1']//li");
	public By legalEntityIframe = By.xpath("//iframe[@id='filter']");
	public By legalEntitySubmit = By.xpath("//div[@id='filterDiv']//tbody//tr[8]//input");
	public By legalEntityNew = By.xpath("//button[@id='PageButtons_NewButton_Button']");
	public By addLegalEntitycode = By.xpath("//input[@id='Code_TextBox']");
	public By addLegalEntityTax = By.xpath("//select[@id='TaxAuthority_DropDownList']");
	public By addLegalEntitySubmit = By.xpath("//button[@id='YsiButtonSubmit_Button']");
	public By LegalEntityAddress = By.xpath("//input[@id='AddressBlock_Address1']");
	public By LegalEntityCity = By.xpath("//input[@id='AddressBlock_City']");
	public By LegalEntityPostCode = By.xpath("//select[@id='AddressBlock_State']");
	public By LegalEntityZipCode = By.xpath("//input[@id='AddressBlock_ZipCode']");
	public By LegalEntityNotes = By.xpath("//textarea[@id='Notes_TextBox']");
	public By LegalEntityDefaultSalesTranType = By.xpath("//select[@id='VatTranType_DropDownList']");
	public By LegalEntityDefaultPurchTranType = By.xpath("//select[@id='PurVatTranType_DropDownList']");
	public By LegalEntityHST = By.xpath("//input[@id='RegistrationNumber_TextBox']");
	public By LegalEntityTaxRegistered = By.xpath("//input[@id='VatRegistered_CheckBox']");
	public By legalEntitySave = By.xpath("//button[@id='PageButtons_SaveButton_Button']");
}
