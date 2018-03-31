package rebeccapurple.schedule;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Timeout extends FunctionalTask {
    @SerializedName("millisecond") @Expose private long __millisecond;

    @Override public long timestamp() { return System.currentTimeMillis() + __millisecond; }

    public Timeout(long millisecond, Operator operator) {
        super(operator);
        __millisecond = millisecond;
    }
}
