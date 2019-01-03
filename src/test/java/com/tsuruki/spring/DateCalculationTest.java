package com.tsuruki.spring;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.tsuruki.spring.services.DateCalcService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DateCalculationTest {
	private MockMvc sut;
	
	@MockBean
	private DateCalcService service;
	
	@Autowired
	private DateCalculation target;
	
	@Before
	public void setUp() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("classpath:templates/");
		viewResolver.setSuffix(".html");
		
		sut = MockMvcBuilders.standaloneSetup(target).setViewResolvers(viewResolver).build();
	}
	
	@Test
	public void 一覧ページのリクエスト結果が正常となりViewとしてindexが返る事() throws Exception {
		sut.perform(get("/"))
		.andExpect(status().isOk())
		.andExpect(view().name("index"));
	}
	
	@Test
	public void 一覧ページで計算基準日を入力して計算実行を押すと計算サービスが呼ばれている事() throws Exception {
		sut.perform(post("/").param("base", "20181201"))
		.andExpect(status().isOk())
		.andExpect(view().name("index"));

//		verify(service, times(1)).calculate("20181201", any());
		verify(service, times(1)).search();
	}
	
	@Test
	public void 新規登録ページのリクエスト結果が正常となりViewとしてregisterが返る事() throws Exception {
		sut.perform(get("/register"))
			.andExpect(status().isOk())
			.andExpect(view().name("register"));
	}

	@Test
	public void 新規登録ページで登録処理を行うとサービスで処理されて一覧画面に遷移される事() throws Exception {
		sut.perform(post("/register").param("dateId", "Y01"))
			.andExpect(status().is(302))
			.andExpect(view().name("redirect:/"));

		verify(service, times(1)).save(any());
	}
	
	@Test
	public void 更新ページのリクエスト結果が正常となりサービスの検索を呼ばれてViewとしてeditが返る事() throws Exception {
		sut.perform(get("/edit/{dateId}", "Y01"))
			.andExpect(status().isOk())
			.andExpect(view().name("edit"));

		verify(service, times(1)).find("Y01");
	}

	@Test
	public void 更新ページで更新処理を行うとサービスで処理されて一覧画面に遷移される事() throws Exception {
		sut.perform(post("/edit").param("dateId", "Y01"))
			.andExpect(status().is(302))
			.andExpect(view().name("redirect:/"));

		verify(service, times(1)).save(any());
	}
	@Test
	public void 一覧ページで削除処理を行うとサービスで処理されて同一画面に遷移される事() throws Exception {
		sut.perform(post("/{dateId}", "Y01"))
			.andExpect(status().is(302))
			.andExpect(view().name("redirect:/"));

		verify(service, times(1)).delete("Y01");
	}
}
