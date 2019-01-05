package com.tsuruki.spring.view.page;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

public class IndexPage {
	
	private static final String URL = "http://localhost:8080/";
	
	@FindBy(id = "base")
	private SelenideElement base;
	
	@FindBy(id = "calcButton")
	private SelenideElement calcButton;
	
	@FindBy(id = "registerButton")
	private SelenideElement registerButton;
	
	public static IndexPage open() {
		return Selenide.open(URL, IndexPage.class);
	}
	
	public String title() {
		return Selenide.title();
	}
	
	public IndexPage setBaseDate(String baseDate) {
		base.setValue(baseDate);
		return page(IndexPage.class);
	}
	
	public IndexPage calc() {
		calcButton.click();
		return page(IndexPage.class);
	}
	
	public SelenideElement result() {
		return $(By.cssSelector("table tbody .text-center"));
	}
}
