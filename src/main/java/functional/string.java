package functional;

import java.util.Locale;
import java.util.Collection;

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
                builder.append(".");
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
                builder.append(".");
            }
        }
        if(builder.length() > 0){
            builder.deleteCharAt(builder.length() - 1);
        }
        return builder.toString();
    }
}
