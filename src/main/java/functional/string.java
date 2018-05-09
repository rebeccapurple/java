package functional;

import java.util.List;
import java.util.Locale;
import java.util.Collection;
import java.util.regex.Pattern;

public class string {
    /**
     * remove this string.check class
     */
    public static class check {
        public static boolean empty(String v){ return v == null || v.length() == 0; }
        public static boolean empty(String v, boolean trim){ return trim ? (v == null || empty(v.trim())) : empty(v); }
    }

    public static boolean empty(String v){ return v == null || v.length() == 0; }
    public static boolean empty(String v, boolean trim){ return trim ? (v == null || empty(v.trim())) : empty(v); }

    public static boolean validate(Pattern expression, String v){
        return v!=null && expression!=null && v.matches(expression.pattern());
    }

    public static boolean contain(String x, String... strings){
        if(strings != null){
            for(String y : strings){
                if(functional.string.equal(x, y)){
                    return true;
                }
            }
        }
        return false;
    }

    public static class to {
        public static String upper(String v){ return v != null ? v.toUpperCase() : null; }
    }

    public static boolean equal(String x, String y){ return x==null ? y==null : x.equals(y); }
    public static boolean equal(String x, String y, boolean casing){ return x==null ? y==null : (casing ? x.equals(y) : x.equalsIgnoreCase(y)); }

    public static String replace(String original, String target, String replacement){ return original != null ? original.replace(target, replacement) : ""; }
    public static String replace(String original, String target, String replacement, boolean all){
        if(original != null){
            return all ? original.replace(target, replacement) : original.replaceAll(target, replacement);
        }
        return null;
    }

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

    public static String from(Long v){ return v != null ? v.toString() : null; }
    public static String from(Integer v){ return v!=null ? v.toString() : null; }
    public static String get(String v){ return v!=null ? v : ""; }

    /**
     * returns a new {@link String} composed of copies of {@code elements} joined together with a
     * copy of the specified {@code delimiter}.
     *
     * For example,
     *
     * <pre>String message = rebeccapurple.string.join(".", "io", "textory", null, "", "goldenrod"); // message returned is: "io.textory.goldenrod"</pre>
     *
     * note: if an element is null or zero length, then element is not added.
     *
     * @param delimiter the delimiter that separates each element
     * @param elements the elements to join together
     * @return a new {@link String} that is composed of the {@code elements} separated by the {@code delimiter}.
     * @throws NullPointerException if delimiter or elements is null, throw {@link NullPointerException}.
     */
    @SuppressWarnings("Duplicates")
    public static String join(CharSequence delimiter, CharSequence[] elements){
        if(delimiter == null || elements == null){ throw new NullPointerException(); }

        StringBuilder builder = new StringBuilder();
        for(CharSequence cs : elements){
            if(cs!=null && cs.length()!=0){
                builder.append(cs);
                builder.append(delimiter);
            }
        }
        if(builder.length() > 0){
            builder.deleteCharAt(builder.length() - 1);
        }
        return builder.toString();
    }

    @SuppressWarnings("Duplicates")
    public static String join(CharSequence delimiter, Collection<? extends CharSequence> elements) {
        if(delimiter == null || elements == null){ throw new NullPointerException(); }

        StringBuilder builder = new StringBuilder();
        for(CharSequence cs : elements){
            if(cs!=null && cs.length()!=0){
                builder.append(cs);
                builder.append(delimiter);
            }
        }
        if(builder.length() > 0){
            builder.deleteCharAt(builder.length() - 1);
        }
        return builder.toString();
    }

    @SuppressWarnings("Duplicates")
    public static String join(CharSequence delimiter, List<? extends CharSequence> elements, int start, int end) {
        if(delimiter == null || elements == null){ throw new NullPointerException(); }
        if(start >= 0){
            StringBuilder builder = new StringBuilder();
            for(int i = start; i < end; i++) {
                CharSequence cs = elements.get(i);
                if(cs!=null && cs.length()!=0){
                    builder.append(cs);
                    builder.append(delimiter);
                }
            }
            if(builder.length() > 0){
                builder.deleteCharAt(builder.length() - 1);
            }
            return builder.toString();
        }
        return null;
    }

    public static String[] split(String original, String delimiter){ return original != null ? original.split(delimiter) : null; }
    public static String[] split(String original, String delimiter, int limit){ return original != null ? original.split(delimiter, limit) : null; }
}
