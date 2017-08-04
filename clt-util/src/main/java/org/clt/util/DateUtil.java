package org.clt.util;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	
	public static Date addSeconds(final Date date, final Integer seconds) {
		return add(date, Calendar.SECOND, seconds);
	}
	
	private static Date add(final Date date, final int calendarField, final int amount) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        
        final Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(calendarField, amount);
        return c.getTime();
    }
}
