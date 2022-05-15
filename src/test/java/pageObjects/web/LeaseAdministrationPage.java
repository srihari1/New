package pageObjects.web;

import org.openqa.selenium.By;

public class LeaseAdministrationPage {
	public By LeaseAdministration= By.xpath("//div[@id='side-menu-wrap']/ul/li[@id='mi5']");
	public By Leases= By.xpath("//div[@id='side-menu-wrap']//ul//li[@id='mi5-2']");
	public By ReviewLease= By.xpath("(//div[@id='side-menu-wrap']//ul[@id='sm5-2']//li)[1]");
	public By LeaseId= By.xpath("//input[@id='HTENT']");
	public By CommercialLeasesStatus= By.xpath("//select[@name='Status']");
	public By CommercialLeasesSubmit= By.xpath("//input[@class='filter_submit']");
	public By Amendments= By.xpath("//span[@id='TabStrip_tab4']");
	public By LeaseAdministrationIframe= By.xpath("//iframe[@id='filter']");
	
	
	}
