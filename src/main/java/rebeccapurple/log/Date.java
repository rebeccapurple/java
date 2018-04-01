package rebeccapurple.log;

import java.util.Calendar;

public class Date {
    private int __year;
    private int __month;
    private int __day;
    private int __hour;
    private int __minute;
    private int __second;
    private long __millisecond;

    public int year(){ return __year; }
    public int month(){ return __month; }
    public int day(){ return __day; }
    public int hour(){ return __hour; }
    public int minute(){ return __minute; }
    public int second(){ return __second; }
    public long millisecond(){ return __millisecond; }

    public void year(int v){ __year = v; }
    public void month(int v){ __month = v; }
    public void day(int v){ __day = v; }
    public void hour(int v){ __hour = v; }
    public void minute(int v){ __minute = v; }
    public void second(int v){ __second = v; }
    public void millisecond(long v){ __millisecond = v; }

    public void set(Calendar calendar){
        __year = calendar.get(Calendar.YEAR);
        __month = calendar.get(Calendar.MONTH);
        __day = calendar.get(Calendar.DATE);
        __hour = calendar.get(Calendar.HOUR_OF_DAY);
        __minute = calendar.get(Calendar.MINUTE);
        __second = calendar.get(Calendar.SECOND);
        __millisecond = calendar.getTimeInMillis() % 1000;
    }

    public Date(){ set(Calendar.getInstance()); }
    public Date(Calendar calendar){ set(calendar); }
}
