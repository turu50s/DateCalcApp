package com.tsuruki.spring.view;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;

import com.codeborne.selenide.Configuration;

public class IndexViewTest {
	
	@BeforeClass
	public static void setUp() {
	    // タイムアウトの時間を5000ミリ秒にする(デフォルト:4000ミリ秒)
	    Configuration.timeout = 5000;

	    // ベースURLを変更する (デフォルト:http://localhost:8080)
	    Configuration.baseUrl = "http://localhost:8080/";

	    // レポート用のディレクトリを変更する
	    Configuration.reportsFolder = "/var/selenide";

	    // テスト実行後にブラウザを開いたままにしない
	    Configuration.holdBrowserOpen = false;
	}
	
	@Before
	public void setUpTest() {
		open("http://localhost:8080/");
	}
	
	@Test
    public void test() {
//        // Googleトップページ
//        // "selenide"を検索
//        $("#lst-ib").val("selenide").pressEnter();
		$(By.id("base")).setValue("20181212");
		$(By.id("calculation")).click();
		
		$(By.cssSelector(".table tbody .text-center")).shouldBe(visible);
//
//        // 検索ページ
//        // Selenideの公式ページをクリック
//        $(By.linkText("Selenide: concise UI tests in Java")).click();
//
//        // Selenide公式ページ
//        // 「What is Selenide?」という文言があることを確認
//        $("body").shouldHave(text("What is Selenide?"));
    }


}
