package com.tsuruki.spring.view;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
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
import com.tsuruki.spring.view.page.UpdatePage;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@TestPropertySource(locations = "classpath:test.properties")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
//@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class UpdateViewTest {

	private UpdatePage page;
	
	@BeforeClass
	public static void setUp() {
		Configuration.holdBrowserOpen = false;
	}
	
	@Before
	public void setUpTest() {
		page = IndexPage.open().setBaseDate("20181201").calc().moveToUpdate(1);
	}
	
	@Test
	public void No1_更新画面から一覧画面へ戻れる事() {
		IndexPage actual = page.backToIndex();
		
		assertThat(actual.title()).isEqualTo("日付計算一覧");
	}
}
