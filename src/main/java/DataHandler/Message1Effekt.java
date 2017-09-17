package DataHandler;

import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by paulseno on 16.09.2017, 14:14.
 */
public class Message1Effekt {
    private final int BYTE1POS = 0;
    private final int YEARPOS = 7;
    private final int MONTHPOS = 9;
    private final int DAYMONTHPOS = 10;
    private final int DAYWEEKPOS = 11;
    private final int HOURPOS = 12;
    private final int MINUTEPOS = 13;
    private final int SECONDPOS = 14;
    private final int HUNDRETHPOS = 15;
    private final int EFFEKTPOS = 24;

    private int year;
    private int month;
    private int dayOfMonth;
    private int dayOfWeek;
    private int hour;
    private int minute;
    private int second;
    private int hundreth;
    private GregorianCalendar dateTime;
    private int effekt;

    public Message1Effekt(List<Integer> informationData) {
        year = (informationData.get(YEARPOS) << 8) + informationData.get(YEARPOS + 1);
        month = informationData.get(MONTHPOS);
        if (month < 1 || month > 12) {
            System.out.println("DLSM error: month " + month);
        }

        dayOfMonth = informationData.get(DAYMONTHPOS);
        if (dayOfMonth < 1 || dayOfMonth > 31) {
            System.out.println("DLSM error: dayOfMonth " + dayOfMonth);
        }

        dayOfWeek = informationData.get(DAYWEEKPOS);
        if (dayOfWeek < 1 || dayOfWeek > 7) {
            System.out.println("DLSM error: dayOfWeek " + dayOfWeek);
        }

        hour = informationData.get(HOURPOS);
        if (hour < 0 || hour > 23) {
            System.out.println("DLSM error: hour " + hour);
        }

        minute = informationData.get(MINUTEPOS);
        if (minute < 0 || minute > 59) {
            System.out.println("DLSM error: minute " + minute);
        }

        second = informationData.get(SECONDPOS);
        if (second < 0 || second > 59) {
            System.out.println("DLSM error: second " + second);
        }

        hundreth = informationData.get(HUNDRETHPOS);

        dateTime = new GregorianCalendar(year, month, dayOfMonth, hour, minute, second);

        effekt = (informationData.get(EFFEKTPOS) << 8) + informationData.get(EFFEKTPOS + 1);

        VerifyFixed(informationData);

        //System.out.println("DLSM effekt " + effekt);
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDayOfMonth() {
        return dayOfMonth;
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public int getSecond() {
        return second;
    }

    public int getEffekt() {
        return effekt;
    }

    public GregorianCalendar getDateTime() {
        return dateTime;
    }


    private boolean VerifyFixed(List<Integer> informationData)
    {
        if (informationData.get(0) != 0x0F) {
            System.out.println("DlmsMessage Fail A");
            return false;
        }
        if (informationData.get(1) != 0x40) {
            System.out.println("DlmsMessage Fail B");
            return false;
        }
        if (informationData.get(2) != 0x00) {
            System.out.println("DlmsMessage Fail C1");
            return false;
        }
        if (informationData.get(3) != 0x00) {
            System.out.println("DlmsMessage Fail C2");
            return false;
        }
        if (informationData.get(4) != 0x00) {
            System.out.println("DlmsMessage Fail C3");
            return false;
        }
        if (informationData.get(5) != 0x09) {
            System.out.println("DlmsMessage Fail D");
            return false;
        }
        if (informationData.get(6) != 0x0C) {
            System.out.println("DlmsMessage Fail E");
            return false;
        }

        if (informationData.get(16) != 0x80) {
            System.out.println("DlmsMessage Fail G " + informationData.get(16));
            return false;
        }
        if (informationData.get(17) != 0x00) {
            System.out.println("DlmsMessage Fail H1 ");
            return false;
        }
        if (informationData.get(18) != 0x00) {
            System.out.println("DlmsMessage Fail H2 ");
            return false;
        }
        if (informationData.get(19) != 0x02) {
            System.out.println("DlmsMessage Fail I ");
            return false;
        }
        if (informationData.get(20) != 0x01) {
            System.out.println("DlmsMessage Fail J ");
            return false;
        }
        if (informationData.get(21) != 0x06) {
            System.out.println("DlmsMessage Fail K ");
            return false;
        }
        if (informationData.get(22) != 0x00) {
            System.out.println("DlmsMessage Fail L1 ");
            return false;
        }
        if (informationData.get(23) != 0x00) {
            System.out.println("DlmsMessage Fail L2 ");
            return false;
        }
        return true;
    }
}
