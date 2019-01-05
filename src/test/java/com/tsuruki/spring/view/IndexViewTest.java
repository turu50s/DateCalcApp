package com.tsuruki.spring.view;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.codeborne.selenide.Configuration;
import com.tsuruki.spring.view.page.IndexPage;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@TestPropertySource(locations = "classpath:test.properties")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class IndexViewTest {
	
	private IndexPage page;
	
	@BeforeClass
	public static void setUp() {
	    // テスト実行後にブラウザを開いたままにしない
	    Configuration.holdBrowserOpen = false;
	}
	
	@Before
	public void setUpTest() {
		page = IndexPage.open();
	}
	
	@Test
    public void No1_一覧画面で計算基準日に20181201を入れて結果が一覧で取得出来る事() {
		IndexPage actual = page.setBaseDate("20181201").calc();
		
		actual.result().shouldBe(visible);
    }
	
}
