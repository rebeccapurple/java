package rebeccapurple.dom.smil;

import org.w3c.dom.DOMException;
import org.w3c.dom.Element;

import static org.w3c.dom.DOMException.NOT_SUPPORTED_ERR;

public class Time implements org.w3c.dom.smil.Time {
    public static final int INDEFINITE = (1     );
    public static final int OFFSET     = (1 << 1);
    public static final int SYNCBASE   = (1 << 2);
    public static final int SYNCTOPREV = (1 << 3);
    public static final int EVENT      = (1 << 4);
    public static final int MARKER     = (1 << 5);
    public static final int WALLCLOCK  = (1 << 6);
    public static final int NEGATIVE   = (1 << 7);
    public static final int ALL        = 0xFF;

    public static class to {
        public static float clock(String v){
            try {
                float result = 0;
                v  = v.trim();
                if(v.endsWith("ms")) {
                    result = number(v, 2, true);
                } else if(v.endsWith("s")) {
                    result = 1000 * number(v, 1, true);
                } else if(v.endsWith("min")) {
                    result = 60000 * number(v, 3, true);
                } else if(v.endsWith("h")) {
                    result = 3600000 * number(v, 1, true);
                } else {
                    try {
                        return number(v, 0, true) * 1000;
                    } catch(NumberFormatException e) {
                        functional.log.d("", e);
                    }
                    String[] values = v.split(":");
                    int index;
                    if(values.length == 2) {
                        index = 0;
                    } else if(values.length == 3) {
                        result = 3600000 * (int) number(values[0], 0, false);
                        index = 1;
                    } else {
                        throw new IllegalArgumentException();
                    }
                    int minute = (int) number(values[index], 0, false);
                    if(0 <= minute && minute <= 59) {
                        result += 60000 * minute;
                    } else {
                        throw new IllegalArgumentException();
                    }
                    float second = number(values[index], 0, true);
                    if(0 <= second && second <= 59) {
                        result += 60000 * second;
                    } else {
                        throw new IllegalArgumentException();
                    }
                }
                return result;
            } catch(NumberFormatException e) {
                throw new IllegalArgumentException();
            }
        }

        public static float number(String v, int ignore, boolean decimal){
            v = v.substring(0, v.length() - ignore);

            float result;
            int comma = v.indexOf('.');
            if(comma != -1){
                if(!decimal) {
                    throw new IllegalArgumentException("int value contains decimal");
                }
                v = v + "000";
                result = Float.parseFloat(v.substring(0, comma));
                result += Float.parseFloat(v.substring(comma + 1, comma + 4))/1000;
            } else {
                result = Integer.parseInt(v);
            }
            return result;
        }
    }



    private boolean __resolved;
    private double __offset;
    private short __type;

    @Override public boolean getResolved() { return __resolved; }
    @Override public double getResolvedOffset() { return __offset; }
    @Override public short getTimeType() { return __type; }

    @Override public double getOffset() { throw new DOMException(NOT_SUPPORTED_ERR, ""); }
    @Override public void setOffset(double offset) throws DOMException { throw new DOMException(NOT_SUPPORTED_ERR, ""); }
    @Override public Element getBaseElement() { throw new DOMException(NOT_SUPPORTED_ERR, ""); }
    @Override public void setBaseElement(Element baseElement) throws DOMException { throw new DOMException(NOT_SUPPORTED_ERR, ""); }
    @Override public boolean getBaseBegin() { throw new DOMException(NOT_SUPPORTED_ERR, ""); }
    @Override public void setBaseBegin(boolean baseBegin) throws DOMException { throw new DOMException(NOT_SUPPORTED_ERR, ""); }
    @Override public String getEvent() { throw new DOMException(NOT_SUPPORTED_ERR, ""); }
    @Override public void setEvent(String event) throws DOMException { throw new DOMException(NOT_SUPPORTED_ERR, ""); }
    @Override public String getMarker() { throw new DOMException(NOT_SUPPORTED_ERR, ""); }
    @Override public void setMarker(String marker) throws DOMException { throw new DOMException(NOT_SUPPORTED_ERR, ""); }

    public Time(String value, int constraints) {
        if(functional.string.equal(value, "indefinite")) {
            if((constraints & INDEFINITE) != 0){
                __type = SMIL_TIME_INDEFINITE;
            } else if((constraints & OFFSET) != 0) {
                if(value.startsWith("+")) {
                    value = value.substring(1);
                    __offset = (to.clock(value)/1000.0);
                } else if(value.startsWith("-")) {
                    value = value.substring(1);
                    __offset = (-1 * to.clock(value)/1000.0);
                } else {
                    __offset = (to.clock(value)/1000.0);
                }
                __resolved = true;
                __type = SMIL_TIME_INDEFINITE;
            } else {
                throw new IllegalArgumentException("Unsupported Time Value");
            }
        }
    }
}
