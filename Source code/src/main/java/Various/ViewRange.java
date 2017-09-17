package Various;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by paulseno on 15.09.2017, 13:34.
 */
public class ViewRange {
    private GregorianCalendar firstDay;
    private GregorianCalendar lastDay;
    private int daysCount;
    private static int FIRST_YEAR = 2008;

    public ViewRange() {
        firstDay = new GregorianCalendar();

    }

    public GregorianCalendar getFirstDay() {
        return firstDay;
    }

    public GregorianCalendar getLastDay() {
        return lastDay;
    }

    public int getDaysCount() {
        return daysCount;
    }

    private boolean checkEndstop(GregorianCalendar calendar) {
        GregorianCalendar calendarNow = new GregorianCalendar();
        if (calendar.get(Calendar.YEAR) < FIRST_YEAR) {          // Endstop, no data earlier than this
            return false;
        }
        if (calendar.get(Calendar.YEAR) >= calendarNow.get(Calendar.YEAR) && calendar.get(Calendar.DAY_OF_YEAR) >= calendarNow.get(Calendar.DAY_OF_YEAR)) {
            return false;
        }
        return true;
    }
}
