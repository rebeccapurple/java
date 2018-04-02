package functional;

import rebeccapurple.log.Date;
import rebeccapurple.log.Method;
import rebeccapurple.log.Type;

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
    public static class type {
        public static class to {
            public static String string(int v){
                switch(v){
                    case Type.ERROR:        return __shorten ? "e" : "error";
                    case Type.WARNING:      return __shorten ? "w" : "warning";
                    case Type.CAUTION:      return __shorten ? "c" : "caution";
                    case Type.NOTICE:       return __shorten ? "n" : "notice";
                    case Type.INFORMATION:  return __shorten ? "i" : "information";
                    case Type.DEBUG:        return __shorten ? "d" : "debug";
                    case Type.VERBOSE:      return __shorten ? "v" : "verbose";
                    case Type.FLOW:         return __shorten ? "f" : "flow";
                    case Type.USER:         return __shorten ? "u" : "user";
                    default:                return __shorten ? "?" : "unknown";
                }
            }
        }

        public static void enable(int v){ __types |= v; }
        public static void disable(int v){ __types &= (~v); }
    }



    private static String __tag = "novemberizing";
    private static int __types = Type.ALL;
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
    public static void console(boolean v){ __console = v; }

    public static void add(Method method){
        if(method != null){
            __methods.add(method);
        }
    }

    public static void del(Method method){ __methods.remove(method); }

    public static void e(String message){ e(__tag, message, null); }
    public static void e(String message, Throwable exception){ e(__tag, message, exception); }
    public static void e(String tag, String message){ e(tag, message, null); }
    public static void e(String tag, String message, Throwable exception){ write(Type.ERROR, tag, message, exception, exception!=null ? exception.getStackTrace() : thread.stacktrace(Thread.currentThread())); }
    public static void e(Object object){ e(__tag, json.from(object), null); }
    public static void e(Object object, Throwable exception){ e(__tag, json.from(object), exception); }
    public static void e(String tag, Object object){ e(tag, json.from(object), null); }
    public static void e(String tag, Object object, Throwable exception){ e(tag, json.from(object), exception); }

    public static void w(String message){ w(__tag, message, null); }
    public static void w(String message, Throwable exception){ w(__tag, message, exception); }
    public static void w(String tag, String message){ w(tag, message, null); }
    public static void w(String tag, String message, Throwable exception){ write(Type.WARNING, tag, message, exception, exception!=null ? exception.getStackTrace() : thread.stacktrace(Thread.currentThread())); }
    public static void w(Object object){ w(__tag, json.from(object), null); }
    public static void w(Object object, Throwable exception){ w(__tag, json.from(object), exception); }
    public static void w(String tag, Object object){ w(tag, json.from(object), null); }
    public static void w(String tag, Object object, Throwable exception){ w(tag, json.from(object), exception); }

    public static void c(String message){ c(__tag, message, null); }
    public static void c(String message, Throwable exception){ c(__tag, message, exception); }
    public static void c(String tag, String message){ c(tag, message, null); }
    public static void c(String tag, String message, Throwable exception){ write(Type.CAUTION, tag, message, exception, exception!=null ? exception.getStackTrace() : thread.stacktrace(Thread.currentThread())); }
    public static void c(Object object){ c(__tag, json.from(object), null); }
    public static void c(Object object, Throwable exception){ c(__tag, json.from(object), exception); }
    public static void c(String tag, Object object){ c(tag, json.from(object), null); }
    public static void c(String tag, Object object, Throwable exception){ c(tag, json.from(object), exception); }

    public static void n(String message){ n(__tag, message, null); }
    public static void n(String message, Throwable exception){ n(__tag, message, exception); }
    public static void n(String tag, String message){ n(tag, message, null); }
    public static void n(String tag, String message, Throwable exception){ write(Type.NOTICE, tag, message, exception, exception!=null ? exception.getStackTrace() : thread.stacktrace(Thread.currentThread())); }
    public static void n(Object object){ n(__tag, json.from(object), null); }
    public static void n(Object object, Throwable exception){ n(__tag, json.from(object), exception); }
    public static void n(String tag, Object object){ n(tag, json.from(object), null); }
    public static void n(String tag, Object object, Throwable exception){ n(tag, json.from(object), exception); }

    public static void i(String message){ i(__tag, message, null); }
    public static void i(String message, Throwable exception){ i(__tag, message, exception); }
    public static void i(String tag, String message){ i(tag, message, null); }
    public static void i(String tag, String message, Throwable exception){ write(Type.INFORMATION, tag, message, exception, exception!=null ? exception.getStackTrace() : thread.stacktrace(Thread.currentThread())); }
    public static void i(Object object){ i(__tag, json.from(object), null); }
    public static void i(Object object, Throwable exception){ i(__tag, json.from(object), exception); }
    public static void i(String tag, Object object){ i(tag, json.from(object), null); }
    public static void i(String tag, Object object, Throwable exception){ i(tag, json.from(object), exception); }

    public static void d(String message){ d(__tag, message, null); }
    public static void d(String message, Throwable exception){ d(__tag, message, exception); }
    public static void d(String tag, String message){ d(tag, message, null); }
    public static void d(String tag, String message, Throwable exception){ write(Type.DEBUG, tag, message, exception, exception!=null ? exception.getStackTrace() : thread.stacktrace(Thread.currentThread())); }
    public static void d(Object object){ d(__tag, json.from(object), null); }
    public static void d(Object object, Throwable exception){ d(__tag, json.from(object), exception); }
    public static void d(String tag, Object object){ d(tag, json.from(object), null); }
    public static void d(String tag, Object object, Throwable exception){ d(tag, json.from(object), exception); }

    public static void v(String message){ v(__tag, message, null); }
    public static void v(String message, Throwable exception){ v(__tag, message, exception); }
    public static void v(String tag, String message){ v(tag, message, null); }
    public static void v(String tag, String message, Throwable exception){ write(Type.VERBOSE, tag, message, exception, exception!=null ? exception.getStackTrace() : thread.stacktrace(Thread.currentThread())); }
    public static void v(Object object){ v(__tag, json.from(object), null); }
    public static void v(Object object, Throwable exception){ v(__tag, json.from(object), exception); }
    public static void v(String tag, Object object){ v(tag, json.from(object), null); }
    public static void v(String tag, Object object, Throwable exception){ v(tag, json.from(object), exception); }

    public static void f(String message){ f(__tag, message, null); }
    public static void f(String message, Throwable exception){ f(__tag, message, exception); }
    public static void f(String tag, String message){ f(tag, message, null); }
    public static void f(String tag, String message, Throwable exception){ write(Type.FLOW, tag, message, exception, exception!=null ? exception.getStackTrace() : thread.stacktrace(Thread.currentThread())); }
    public static void f(Object object){ f(__tag, json.from(object), null); }
    public static void f(Object object, Throwable exception){ f(__tag, json.from(object), exception); }
    public static void f(String tag, Object object){ f(tag, json.from(object), null); }
    public static void f(String tag, Object object, Throwable exception){ f(tag, json.from(object), exception); }

    public static void u(String type, String message){ u(type, __tag, message, null); }
    public static void u(String type, String message, Throwable exception){ u(type, __tag, message, exception); }
    public static void u(String type, String tag, String message){ u(type, tag, message, null); }
    public static void u(String type, String tag, String message, Throwable exception){ write(Type.USER, type, tag, message, exception, exception!=null ? exception.getStackTrace() : thread.stacktrace(Thread.currentThread())); }
    public static void u(String type, Object object){ u(type, __tag, json.from(object), null); }
    public static void u(String type, Object object, Throwable exception){ u(type, __tag, json.from(object), exception); }
    public static void u(String type, String tag, Object object){ u(type, tag, json.from(object), null); }
    public static void u(String type, String tag, Object object, Throwable exception){ u(type, tag, json.from(object), exception); }
}
