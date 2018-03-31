package rebeccapurple;

import java.util.Locale;

public class string {
    public static class check {
        public static boolean empty(String v){ return v == null || v.length() == 0; }
    }

    public static String replace(String original, String target, String replacement){ return original != null ? original.replace(target, replacement) : ""; }

    public static String from(Class<?> c, boolean simple){ return c!=null ? (simple ? c.getSimpleName() : c.getCanonicalName()) : ""; }

    public static String from(StackTraceElement element){
        if(element != null){
            String c = replace(element.getClassName(), "$", ".");
            return String.format(Locale.getDefault(), "%s.%s (%s:%d)",
                    c,
                    element.getMethodName(),
                    element.getFileName(),
                    element.getLineNumber());
        }
        return "";
    }
}
