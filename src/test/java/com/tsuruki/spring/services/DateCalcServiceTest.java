package com.tsuruki.spring.services;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.format.DateTimeParseException;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.tsuruki.spring.DateCalc;

import com.tsuruki.spring.services.DateCalcService;

@RunWith(Enclosed.class)
public class DateCalcServiceTest {
	
	public static class 日付計算例外処理 {

		private DateCalcService sut;
		
		@Before
		public void before() throws Exception {
			sut = new DateCalcService();
		}
		
		@Test(expected = NullPointerException.class)
		public void 基準日にNULLを渡すとNullPointerExceptionになる事() throws Exception {
			sut.calculate(null, setUpFormula(0,0,0));
		}
		
		@Test(expected = NullPointerException.class)
		public void 日付計算式にNULLを渡すとNullPointerExceptionになる事() throws Exception {
			sut.calculate("20181208", null);
		}
		
		@Test(expected = DateTimeParseException.class)
		public void 計算基準日をyyyyMMdd以外の形式で渡すとDateTimeParseExceptionとなる事() throws Exception {
			sut.calculate("201812", setUpFormula(0,0,0));
		}
		
		public DateCalc setUpFormula(int year, int month, int day) {
			DateCalc formula = new DateCalc();
			formula.setCalcYear(year);
			formula.setCalcMonth(month);
			formula.setCalcDay(day);
			return formula;
		}
	}
	
	@RunWith(Parameterized.class)
	public static class 日付計算 {
		private String baseDate;
		private int year;
		private int month;
		private int day;
		private String expected;
		
		@Parameters(name = "{index} baseDate: {0}, year: {1}, month: {2}, day: {3}, expected: {4}")
		public static Object[][] params() {
			return new Object[][] {
				// すべて未指定
				{"20181201", 0, 0, 0, "20181201"},
				// すべて指定
				{"20181201", 1, 1, 1, "20200102"},
				// 月の加算で日数の切り捨て
				{"20181031", 0, 1, 0, "20181130"},
				// 年月またぎ
				{"20181202", 0, 13, 0, "20200102"},
				{"20181202", 0, 0, 365, "20191202"},
				// 翌日
				{"20181201", 0, 0, 1, "20181202"},
				// 前日
				{"20181201", 0, 0, -1, "20181130"},
				// 翌月
				{"20181101", 0, 1, 0, "20181201"},
				// 前月
				{"20181201", 0, -1, 0, "20181101"},
				// 翌年
				{"20181201", 1, 0, 0, "20191201"},
				// 前年
				{"20181201", -1, 0, 0, "20171201"},
			};
		}
		
		public 日付計算(String baseDate, int year, int month, int day, String expected) {
			this.baseDate = baseDate;
			this.year = year;
			this.month = month;
			this.day = day;
			this.expected = expected;
		}

		@Test
		public void test() throws Exception {
			DateCalc formula = new DateCalc();
			formula.setCalcYear(year);
			formula.setCalcMonth(month);
			formula.setCalcDay(day);

			DateCalcService sut = new DateCalcService();
			String actual = sut.calculate(baseDate, formula);

			assertThat(actual).isEqualTo(expected);
		}
	}
	
//	public static class 日付計算 {
//		
//		private DateCalcService sut;
//		
//		@Before
//		public void before() throws Exception {
//			sut = new DateCalcService();
//		}
//		
//		/**
//		 *  テストは４行で書く
//		 */
//		
//		@Test
//		public void 翌日の日付計算式に基準日20181208を渡して計算結果が20181209になること() throws Exception {
//			
//			DateCalc formula = setUpFormula(0,0,1); 				/** セットアップ */
//			
//			String actual = sut.calculate("20181208", formula);		/** 実行 */
//			
//			assertThat(actual).isEqualTo("20181209");				/** 検証 */
//			
//		}
//		/**
//		 * 日付計算式の初期設定
//		 * 
//		 * @return 設定された日付計算式
//		*/
//		public DateCalc setUpFormula(int year, int month, int day) {
//			DateCalc formula = new DateCalc();
//			formula.setCalcYear(year);
//			formula.setCalcMonth(month);
//			formula.setCalcDay(day);
//			return formula;
//		}
//	}
	
	
	/**
	 * parameterized test
	 * @author shigeru
	 *
	 */
	@RunWith(Theories.class)
	public static class 日付計算式に基準日20181208を渡して計算結果が期待値になること {
		
		@DataPoints
		public static DateCalcFixture[] dateCalcFix = {
				new DateCalcFixture(1,0,0,"20191208"),
				new DateCalcFixture(0,1,0,"20190108"),
				new DateCalcFixture(0,0,1,"20181209"),
				new DateCalcFixture(-1,0,0,"20171208"),
				new DateCalcFixture(0,-1,0,"20181108"),
				new DateCalcFixture(0,0,-1,"20181207"),
		};
		
		@Theory
		public void calc(DateCalcFixture fix) throws Exception {
			DateCalc formula = new DateCalc();
			formula.setCalcYear(fix.year);
			formula.setCalcMonth(fix.month);
			formula.setCalcDay(fix.day);
			
			DateCalcService sut = new DateCalcService();
			String actual = sut.calculate("20181208", formula);
			
			assertThat(actual).isEqualTo(fix.expected);
			
		}
	}
	
	static class DateCalcFixture{
		int year;
		int month;
		int day;
		String expected;
		
		DateCalcFixture(int year, int month, int day, String expected) {
			this.year = year;
			this.month = month;
			this.day = day;
			this.expected = expected;
		}
	}
}
