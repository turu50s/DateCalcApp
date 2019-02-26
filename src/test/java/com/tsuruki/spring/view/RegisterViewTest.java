package com.tsuruki.spring.view;

import static com.codeborne.selenide.Condition.visible;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.codeborne.selenide.Configuration;
import com.tsuruki.spring.view.page.IndexPage;
import com.tsuruki.spring.view.page.RegisterPage;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@TestPropertySource(locations = "classpath:test.properties")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
//@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@Ignore("exclude tests")
public class RegisterViewTest {

	
	private RegisterPage page;
	
	@BeforeClass
	public static void setUp() {
		Configuration.holdBrowserOpen = false;
	}
	
	@Before
	public void setUpTest() {
		page = IndexPage.open().moveToRegister();
	}
	
	@Test
	public void No1_新規登録画面から一覧画面へ戻れる事() {
		IndexPage actual = page.backToIndex();
		
		assertThat(actual.title()).isEqualTo("日付計算一覧");
	}
	
	@Test
	public void No2_新規登録画面で新規登録出来る事() {
		IndexPage indexPage = page.setDateId("Y02").setDateName("前年")
				.setCalcYear("-1").setCalcMonth("0").setCalcDay("0").registData();
		IndexPage actual = indexPage.setBaseDate("20181201").calc();
		
		actual.result().shouldBe(visible);
		assertThat(actual.resultCount()).isEqualTo(3);
	}
}
