package pageObjects.web;

import org.openqa.selenium.By;

public class LoginPage {
	public By LoginForm = By.xpath("//form[@id='form18']");
	public By LoginEmail = By.xpath("//input[@id='okta-signin-username']");
	public By LoginPassword = By.xpath("//input[@id='okta-signin-password']");
	public By SignOnButton = By.xpath("//input[@id='okta-signin-submit']");
	public By OktaSendPush = By.xpath("//div[@class='o-form-button-bar']/input");
	public By proceed= By.xpath("//input[@id='cmdLogin']");
}
