package com.tsuruki.spring.services;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import com.tsuruki.spring.DateCalc;

@RunWith(Enclosed.class)
public class DateCalcServiceTest {
	public static class 日付計算 {
		
		private DateCalcService sut;
		
		@Before
		public void before() throws Exception {
			sut = new DateCalcService();
		}
		
		/**
		 *  テストは４行で書く
		 */
		
		@Test
		public void 翌日の日付計算式に基準日20181208を渡して計算結果が20181209になること() throws Exception {
			
			DateCalc formula = setUpFormula(0,0,1); 				/** セットアップ */
			
			String actual = sut.calculate("20181208", formula);		/** 実行 */
			
			assertThat(actual).isEqualTo("20181209");				/** 検証 */
			
		}
		/**
		 * 日付計算式の初期設定
		 * 
		 * @return 設定された日付計算式
		*/
		private DateCalc setUpFormula(int year, int month, int day) {
			DateCalc formula = new DateCalc();
			formula.setCalcYear(year);
			formula.setCalcMonth(month);
			formula.setCalcDay(day);
			return formula;
		}
	}
	
	
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
