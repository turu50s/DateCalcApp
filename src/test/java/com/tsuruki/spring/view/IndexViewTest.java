package com.tsuruki.spring.view;

import static com.codeborne.selenide.Condition.visible;
import static org.assertj.core.api.Assertions.assertThat;

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
import com.tsuruki.spring.view.page.RegisterPage;
import com.tsuruki.spring.view.page.UpdatePage;

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
//	@Sql("classpath:data.sql")
    public void No1_一覧画面で計算基準日に20181201を入れて結果が一覧で取得出来る事() {
		IndexPage actual = page.setBaseDate("20181201").calc();
		
		actual.result().shouldBe(visible);
		assertThat(actual.resultCount()).isEqualTo(2);
    }
	
	@Test
	public void No2_一覧画面から新規登録画面へ遷移できる事() {
		RegisterPage actual = page.moveToRegister();
		
		assertThat(actual.title()).isEqualTo("計算式登録");
    }
	
	@Test
	public void No3_一覧画面から更新画面へ遷移できる事() {
		page.setBaseDate("20181201").calc();
		
		UpdatePage actual = page.moveToUpdate(1);
		
		assertThat(actual.title()).isEqualTo("計算式更新");
	}
	
	@Test
	public void No4_一覧画面から削除実行できる事() {
		page.setBaseDate("20181201").calc();
		
		page.deleteExecute(1);
		
		IndexPage actual = page.setBaseDate("20181201").calc();
		assertThat(actual.resultCount()).isEqualTo(1);
	}
}
