package rebeccapurple;

public interface Task<IN> {
    class STATE {
        public static final int UNKNOWN    = 0x00000000;
        public static final int READY      = 0x00000001;
        public static final int INPROGRESS = 0x00000001 <<  1;
        public static final int COMPLETED  = 0x00000001 <<  2;
        public static final int CANCELLED  = 0x00000001 <<  3;
    }

    IN in();
    int state();
    Throwable exception();
    Long ttl();

    void cancel(Throwable exception);

    default boolean is(int v){ return (state() & v) == v; }
    default <T extends IN> T in(Class<T> c) throws ClassCastException { return c.cast(in()); }
}
