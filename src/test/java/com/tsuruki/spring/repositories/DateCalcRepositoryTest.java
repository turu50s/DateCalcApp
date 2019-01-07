package com.tsuruki.spring.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.tsuruki.spring.DateCalc;

@RunWith(SpringRunner.class)
@DataJpaTest
//@Sql("classpath:data.sql")
@TestPropertySource(locations = "classpath:test.properties")
public class DateCalcRepositoryTest {
	
//	@Autowired
//	private TestEntityManager testEntityManager;

	@Autowired
	private DateCalcRepository sut;
	
	@Before
	public void init() throws Exception {
		// テストデータ作成
//		testEntityManager.persist(createDateCalc("Y01", "翌年", 1, 0, 0));
//		testEntityManager.persist(createDateCalc("M01", "翌月", 0, 1, 0));
	}
	
	@Test
	public void 検索_全件して結果をリストで取得出来る事() throws Exception {
		List<DateCalc> actual = sut.findAll();
		
		assertThat(actual.size()).isEqualTo(2);
	}
	
	@Test
	public void 検索_1件して結果がキーに紐づく1件だけ取得出来る事() throws Exception {
		DateCalc actual = sut.findByDateId("Y01");
		
		assertThat(actual.getDateId()).isEqualTo("Y01");
		assertThat(actual.getDateName()).isEqualTo("翌年");
		assertThat(actual.getCalcYear()).isEqualTo(1);
		assertThat(actual.getCalcMonth()).isEqualTo(0);
		assertThat(actual.getCalcDay()).isEqualTo(0);
	}
	
	@Test
	public void 存在しないデータを検索すると結果がNULLとなる事() throws Exception {
		DateCalc actual = sut.findByDateId("Z01");
		
		assertThat(actual).isNull();
	}
	
	@Test
	public void NULLで検索すると結果がNULLとなる事() throws Exception {
		DateCalc actual = sut.findByDateId(null);

		assertThat(actual).isNull();
	}

	@Test
	public void 新規登録が出来る事() throws Exception {
		DateCalc newData = createDateCalc("D01", "翌日", 0, 0, 1); 
		sut.saveAndFlush(newData);
		DateCalc actual = sut.findByDateId("D01");
		
		assertThat(actual.getDateId()).isEqualTo("D01");
	}
	
	@Test
	public void キーに紐づく1件の更新が出来る事() throws Exception {
		DateCalc updateData = sut.findByDateId("Y01");
		updateData.setDateName("翌々年");
		updateData.setCalcYear(2);
		sut.saveAndFlush(updateData);
		DateCalc actual = sut.findByDateId("Y01");
		
		assertThat(actual.getDateName()).isEqualTo("翌々年");
		assertThat(actual.getCalcYear()).isEqualTo(2);
	}
	
	@Test
	public void キーに紐づく1件の削除が出来る事() throws Exception {
		sut.deleteById("M01");
		List<DateCalc> actual = sut.findAll();
		
		assertThat(actual.size()).isEqualTo(1);
	}
	
	public DateCalc createDateCalc(String id, String name, int year, int month, int day) {
		DateCalc dateCalc = new DateCalc();
		dateCalc.setDateId(id);
		dateCalc.setDateName(name);
		dateCalc.setCalcYear(year);
		dateCalc.setCalcMonth(month);
		dateCalc.setCalcDay(day);
		return dateCalc;
	}
}
