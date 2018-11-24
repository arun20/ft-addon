package co.th.lh.ts.ftaddon.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public final class DateTimeUtil {

	public static Date convertStringToDateWithTimeZone(String dateInString) {
		ZonedDateTime result = ZonedDateTime.parse(dateInString, DateTimeFormatter.ISO_DATE_TIME);
		LocalDateTime localDateTime = result.toLocalDateTime();
		return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
	}

	public static Date convertStringToTime(String timeInString) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

		Date date = null;
		try {
			date = sdf.parse(timeInString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	public static String convertTimeToString(Date time) {
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		String date = null;
		try {
			date = df.format(time);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}
	
}
