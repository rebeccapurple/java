package rebeccapurple;

public class scheduler {
    public static abstract class Loop {
        protected Thread __thread;
        protected Runnable __loop;

        public void on(){

        }
        public abstract void off();
    }
}
