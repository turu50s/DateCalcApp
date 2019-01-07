package com.tsuruki.spring.view.page;

import static com.codeborne.selenide.Selenide.page;

import org.openqa.selenium.support.FindBy;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

public class RegisterPage {

	@FindBy(id = "registerButton")
	private SelenideElement registerButton;
	
	@FindBy(id = "backButton")
	private SelenideElement backButton;
	
	@FindBy(id = "dateId")
	private SelenideElement dateId;
	
	@FindBy(id = "dateName")
	private SelenideElement dateName;
	
	@FindBy(id = "calcYear")
	private SelenideElement calcYear;
	
	@FindBy(id = "calcMonth")
	private SelenideElement calcMonth;
	
	@FindBy(id = "calcDay")
	private SelenideElement calcDay;
	
	public String title() {
		return Selenide.title();
	}

	public IndexPage backToIndex() {
		backButton.click();
		return page(IndexPage.class);
	}
	
	public RegisterPage setDateId(String dateid) {
		dateId.setValue(dateid);
		return page(RegisterPage.class);
	}
	
	public RegisterPage setDateName(String datename) {
		dateName.setValue(datename);
		return page(RegisterPage.class);
	}
	
	public RegisterPage setCalcYear(String calcyear) {
		calcYear.setValue(calcyear);
		return page(RegisterPage.class);
	}
	
	public RegisterPage setCalcMonth(String calcmonth) {
		calcMonth.setValue(calcmonth);
		return page(RegisterPage.class);
	}
	
	public RegisterPage setCalcDay(String calcday) {
		calcDay.setValue(calcday);
		return page(RegisterPage.class);
	}
	
	public IndexPage registData() {
		registerButton.click();
		return page(IndexPage.class);
	}
}
