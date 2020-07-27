package br.ce.wcaquino.taskbackend.utils;

import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Test;

public class DataUtilsTest {

	@Test
	public void shouldReturnTrueForFutureDate() {
		LocalDate date = LocalDate.of(2030,  01, 01);
		DateUtils.isEqualOrFutureDate(date);
		Assert.assertTrue(DateUtils.isEqualOrFutureDate(date));;
	}
	
	@Test
	public void shouldReturnTrueForPastDate() {
		LocalDate date = LocalDate.of(2010,  01, 01);
		DateUtils.isEqualOrFutureDate(date);
		Assert.assertFalse(DateUtils.isEqualOrFutureDate(date));;
	}
	
	@Test
	public void shouldReturnTrueForCurrenttDate() {
		LocalDate date = LocalDate.now();
		DateUtils.isEqualOrFutureDate(date);
		Assert.assertTrue(DateUtils.isEqualOrFutureDate(date));;
	}
	
}
