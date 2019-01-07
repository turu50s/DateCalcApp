package com.tsuruki.spring.view.page;

import static com.codeborne.selenide.Selenide.page;

import org.openqa.selenium.support.FindBy;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

public class UpdatePage {
	
	@FindBy(id = "updateButton")
	private SelenideElement updateButton;
	
	@FindBy(id = "backButton")
	private SelenideElement backButton;
	
	public String title() {
		return Selenide.title();
	}
	

	public IndexPage backToIndex() {
		backButton.click();
		return page(IndexPage.class);
	}
}
