package rebeccapurple.dom.smil;

import org.w3c.dom.smil.Time;

import java.util.ArrayList;

public class TimeList implements org.w3c.dom.smil.TimeList {
    private ArrayList<Time> __times;

    @Override public Time item(int index) { return __times != null && 0<= index && index < __times.size() ? __times.get(index) : null; }
    @Override public int getLength() { return __times != null ? __times.size() : 0; }
}
