package rebeccapurple;
import java.util.Calendar;
import java.util.LinkedHashSet;
import java.util.Locale;

/**
 *
 * @author      sean <hyunsik-park@textory.com>
 * @since       0.0.1
 * @date        2018. 3. 28.
 * @stereotype  singleton
 * @visibility  public
 */

public class log {

    public static class TYPE {
        public static final int ERROR       = 0x00000001 <<  1;
        public static final int WARNING     = 0x00000001 <<  2;
        public static final int CAUTION     = 0x00000001 <<  3;
        public static final int NOTICE      = 0x00000001 <<  4;
        public static final int INFORMATION = 0x00000001 <<  5;
        public static final int DEBUG       = 0x00000001 <<  6;
        public static final int VERBOSE     = 0x00000001 <<  7;
        public static final int FLOW        = 0x00000001 <<  8;
        public static final int USER        = 0x00000001 <<  9;

        public static final int ALL         = 0xFFFFFFFF;
        public static final int NONE        = 0x00000000;
    }

    public static class Date {
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


    public interface Method {
        void out(int classification, String type, Date current, long thread, String tag, String message, Throwable exception);
    }

    public static class type {
        public static class to {
            public static String string(int v){
                switch(v){
                    case TYPE.ERROR:        return __shorten ? "e" : "error";
                    case TYPE.WARNING:      return __shorten ? "w" : "warning";
                    case TYPE.CAUTION:      return __shorten ? "c" : "caution";
                    case TYPE.NOTICE:       return __shorten ? "n" : "notice";
                    case TYPE.INFORMATION:  return __shorten ? "i" : "information";
                    case TYPE.DEBUG:        return __shorten ? "d" : "debug";
                    case TYPE.VERBOSE:      return __shorten ? "v" : "verbose";
                    case TYPE.FLOW:         return __shorten ? "f" : "flow";
                    case TYPE.USER:         return __shorten ? "u" : "user";
                    default:                return __shorten ? "?" : "unknown";
                }
            }
        }

        public static void enable(int v){ __types |= v; }
        public static void disable(int v){ __types &= (~v); }
    }



    private static String __tag = "novemberizing";
    private static int __types = TYPE.ALL;
    private static boolean __shorten = false;
    private static boolean __console = false;
    private static boolean __stacktrace = false;
    private static boolean __date = false;
    private static boolean __function = true;
    private static int __depth = 8;
    private static LinkedHashSet<Method> __methods = new LinkedHashSet<>();

    private static void write(int classification, String type, String tag, String message, Throwable exception, StackTraceElement[] elements){
        Date current = new Date();
        StringBuilder builder = new StringBuilder();
        if(__date) {
            builder.append(String.format(Locale.getDefault(), "%04d/%02d/%02d %02d:%02d:%02d.%03d ",
                    current.year(),
                    current.month(),
                    current.day(),
                    current.hour(),
                    current.minute(),
                    current.second(),
                    current.millisecond()));
        }
        builder.append(String.format(Locale.getDefault(), "%s", message));
        if(__function && !collection.check.empty(elements)) {
            StackTraceElement element = thread.get(elements, __depth);
            if(element != null){
                builder.append(String.format(Locale.getDefault(), " %s.%s(%s:%d) ",
                        string.replace(element.getClassName(), "$", "."),
                        element.getMethodName(),
                        element.getFileName(),
                        element.getLineNumber()));
            }
        }
        if(exception != null) {
            builder.append(" exception (");
            builder.append(string.from(exception.getClass(), false));
            if(!string.check.empty(exception.getMessage())) {
                builder.append("/");
                builder.append(exception.getMessage());
            }
            builder.append(")");
        }
        if(!collection.check.empty(elements) && __stacktrace){
            builder.append("\n");
            for(StackTraceElement element : elements) {
                builder.append("    ");
                builder.append(string.from(element));
                builder.append("\n");
            }
        }
        String out = builder.toString();
        if(__console) {
            System.out.println(out);
        }
        if(__methods.size() > 0) {
            for (Method method : __methods) {
                method.out(classification, type, current, thread.id(Thread.currentThread()), tag, out, exception);
            }
        }
    }

    private static void write(int classification, String tag, String message, Throwable exception, StackTraceElement[] elements){
        if((__types & classification) != 0 && (__methods.size()>0 || __console)) {
            write(classification, type.to.string(classification), tag, message, exception, elements);
        }
    }

    public static void tag(String v){ __tag = v; }
    public static void shorten(boolean v){ __shorten = v; }
    public static void types(int v){ __types = v; }
    public static void stacktrace(boolean v){ __stacktrace = v; }
    public static void date(boolean v){ __date = v; }
    public static void function(boolean v){ __function = v; }
    public static void depth(int v){ __depth = v; }

    public static void add(Method method){
        if(method != null){
            __methods.add(method);
        }
    }

    public static void del(Method method){ __methods.remove(method); }

    public static void e(String message){ e(__tag, message, null); }
    public static void e(String message, Throwable exception){ e(__tag, message, exception); }
    public static void e(String tag, String message){ e(tag, message, null); }
    public static void e(String tag, String message, Throwable exception){ write(TYPE.ERROR, tag, message, exception, exception!=null ? exception.getStackTrace() : thread.stacktrace(Thread.currentThread())); }
    public static void e(Object object){ e(__tag, rebeccapurple.json.from(object), null); }
    public static void e(Object object, Throwable exception){ e(__tag, rebeccapurple.json.from(object), exception); }
    public static void e(String tag, Object object){ e(tag, rebeccapurple.json.from(object), null); }
    public static void e(String tag, Object object, Throwable exception){ e(tag, rebeccapurple.json.from(object), exception); }

    public static void w(String message){ w(__tag, message, null); }
    public static void w(String message, Throwable exception){ w(__tag, message, exception); }
    public static void w(String tag, String message){ w(tag, message, null); }
    public static void w(String tag, String message, Throwable exception){ write(TYPE.WARNING, tag, message, exception, exception!=null ? exception.getStackTrace() : thread.stacktrace(Thread.currentThread())); }
    public static void w(Object object){ w(__tag, rebeccapurple.json.from(object), null); }
    public static void w(Object object, Throwable exception){ w(__tag, rebeccapurple.json.from(object), exception); }
    public static void w(String tag, Object object){ w(tag, rebeccapurple.json.from(object), null); }
    public static void w(String tag, Object object, Throwable exception){ w(tag, rebeccapurple.json.from(object), exception); }

    public static void c(String message){ c(__tag, message, null); }
    public static void c(String message, Throwable exception){ c(__tag, message, exception); }
    public static void c(String tag, String message){ c(tag, message, null); }
    public static void c(String tag, String message, Throwable exception){ write(TYPE.CAUTION, tag, message, exception, exception!=null ? exception.getStackTrace() : thread.stacktrace(Thread.currentThread())); }
    public static void c(Object object){ c(__tag, rebeccapurple.json.from(object), null); }
    public static void c(Object object, Throwable exception){ c(__tag, rebeccapurple.json.from(object), exception); }
    public static void c(String tag, Object object){ c(tag, rebeccapurple.json.from(object), null); }
    public static void c(String tag, Object object, Throwable exception){ c(tag, rebeccapurple.json.from(object), exception); }

    public static void n(String message){ n(__tag, message, null); }
    public static void n(String message, Throwable exception){ n(__tag, message, exception); }
    public static void n(String tag, String message){ n(tag, message, null); }
    public static void n(String tag, String message, Throwable exception){ write(TYPE.NOTICE, tag, message, exception, exception!=null ? exception.getStackTrace() : thread.stacktrace(Thread.currentThread())); }
    public static void n(Object object){ n(__tag, rebeccapurple.json.from(object), null); }
    public static void n(Object object, Throwable exception){ n(__tag, rebeccapurple.json.from(object), exception); }
    public static void n(String tag, Object object){ n(tag, rebeccapurple.json.from(object), null); }
    public static void n(String tag, Object object, Throwable exception){ n(tag, rebeccapurple.json.from(object), exception); }

    public static void i(String message){ i(__tag, message, null); }
    public static void i(String message, Throwable exception){ i(__tag, message, exception); }
    public static void i(String tag, String message){ i(tag, message, null); }
    public static void i(String tag, String message, Throwable exception){ write(TYPE.INFORMATION, tag, message, exception, exception!=null ? exception.getStackTrace() : thread.stacktrace(Thread.currentThread())); }
    public static void i(Object object){ i(__tag, rebeccapurple.json.from(object), null); }
    public static void i(Object object, Throwable exception){ i(__tag, rebeccapurple.json.from(object), exception); }
    public static void i(String tag, Object object){ i(tag, rebeccapurple.json.from(object), null); }
    public static void i(String tag, Object object, Throwable exception){ i(tag, rebeccapurple.json.from(object), exception); }

    public static void d(String message){ d(__tag, message, null); }
    public static void d(String message, Throwable exception){ d(__tag, message, exception); }
    public static void d(String tag, String message){ d(tag, message, null); }
    public static void d(String tag, String message, Throwable exception){ write(TYPE.DEBUG, tag, message, exception, exception!=null ? exception.getStackTrace() : thread.stacktrace(Thread.currentThread())); }
    public static void d(Object object){ d(__tag, rebeccapurple.json.from(object), null); }
    public static void d(Object object, Throwable exception){ d(__tag, rebeccapurple.json.from(object), exception); }
    public static void d(String tag, Object object){ d(tag, rebeccapurple.json.from(object), null); }
    public static void d(String tag, Object object, Throwable exception){ d(tag, rebeccapurple.json.from(object), exception); }

    public static void v(String message){ v(__tag, message, null); }
    public static void v(String message, Throwable exception){ v(__tag, message, exception); }
    public static void v(String tag, String message){ v(tag, message, null); }
    public static void v(String tag, String message, Throwable exception){ write(TYPE.VERBOSE, tag, message, exception, exception!=null ? exception.getStackTrace() : thread.stacktrace(Thread.currentThread())); }
    public static void v(Object object){ v(__tag, rebeccapurple.json.from(object), null); }
    public static void v(Object object, Throwable exception){ v(__tag, rebeccapurple.json.from(object), exception); }
    public static void v(String tag, Object object){ v(tag, rebeccapurple.json.from(object), null); }
    public static void v(String tag, Object object, Throwable exception){ v(tag, rebeccapurple.json.from(object), exception); }

    public static void f(String message){ f(__tag, message, null); }
    public static void f(String message, Throwable exception){ f(__tag, message, exception); }
    public static void f(String tag, String message){ f(tag, message, null); }
    public static void f(String tag, String message, Throwable exception){ write(TYPE.FLOW, tag, message, exception, exception!=null ? exception.getStackTrace() : thread.stacktrace(Thread.currentThread())); }
    public static void f(Object object){ f(__tag, rebeccapurple.json.from(object), null); }
    public static void f(Object object, Throwable exception){ f(__tag, rebeccapurple.json.from(object), exception); }
    public static void f(String tag, Object object){ f(tag, rebeccapurple.json.from(object), null); }
    public static void f(String tag, Object object, Throwable exception){ f(tag, rebeccapurple.json.from(object), exception); }

    public static void u(String type, String message){ u(type, __tag, message, null); }
    public static void u(String type, String message, Throwable exception){ u(type, __tag, message, exception); }
    public static void u(String type, String tag, String message){ u(type, tag, message, null); }
    public static void u(String type, String tag, String message, Throwable exception){ write(TYPE.USER, type, tag, message, exception, exception!=null ? exception.getStackTrace() : thread.stacktrace(Thread.currentThread())); }
    public static void u(String type, Object object){ u(type, __tag, rebeccapurple.json.from(object), null); }
    public static void u(String type, Object object, Throwable exception){ u(type, __tag, rebeccapurple.json.from(object), exception); }
    public static void u(String type, String tag, Object object){ u(type, tag, rebeccapurple.json.from(object), null); }
    public static void u(String type, String tag, Object object, Throwable exception){ u(type, tag, rebeccapurple.json.from(object), exception); }
}
