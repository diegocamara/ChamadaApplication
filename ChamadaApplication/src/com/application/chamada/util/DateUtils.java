package com.application.chamada.util;

import org.joda.time.DateTime;

public class DateUtils {

	public static String obterDescricaoData(DateTime dateTime){
		return dateTime != null ? dateTime.getDayOfMonth() + "/" + dateTime.getMonthOfYear() + "/" + dateTime.getYear() : new String();
	}
	
}
