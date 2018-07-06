package com.transaction.statistics.mystatisticsapp.util;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Seconds;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class Util {

  /**
   * This method will return the date according to the UTC time zone.
   *
   * @return @{@link Date}
   */
  public static Date getDate() {
    TimeZone tz = TimeZone.getTimeZone("UTC");
    TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    Calendar cal = Calendar.getInstance(tz);
    return cal.getTime();
  }

  /**
   * This method is used to calculate the difference in seconds
   * given epochTime.
   * @param currentEpochTime  Current epoch time in using UTC zone.
   * @param previousEpochTime Transactions epoch time.
   * @return int (difference in seconds)
   */
  public static int calculateSecondsBetweenDates(Long currentEpochTime, Long previousEpochTime){
    DateTime currentDate = new DateTime(currentEpochTime, DateTimeZone.UTC);
    DateTime previousDate = new DateTime(previousEpochTime, DateTimeZone.UTC);

    return Seconds.secondsBetween(previousDate,currentDate).getSeconds();
  }
}
