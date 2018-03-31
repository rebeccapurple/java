package rebeccapurple.schedule;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Time extends rebeccapurple.scheduler.Task {
    @SerializedName("timestamp") @Expose private long __timestamp;

    public Time(long timestamp, Operator operator) {
        super(operator);
        __timestamp = timestamp;
    }

    @Override public long timestamp() { return __timestamp; }
}
