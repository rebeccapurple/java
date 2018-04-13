package rebeccapurple.dom.smil;

import org.w3c.dom.DOMException;
import org.w3c.dom.smil.TimeList;

public class ElementTime implements org.w3c.dom.smil.ElementTime {
    @Override
    public TimeList getBegin() {
        return null;
    }

    @Override
    public void setBegin(TimeList begin) throws DOMException {

    }

    @Override
    public TimeList getEnd() {
        return null;
    }

    @Override
    public void setEnd(TimeList end) throws DOMException {

    }

    @Override
    public float getDur() {
        return 0;
    }

    @Override
    public void setDur(float dur) throws DOMException {

    }

    @Override
    public short getRestart() {
        return 0;
    }

    @Override
    public void setRestart(short restart) throws DOMException {

    }

    @Override
    public short getFill() {
        return 0;
    }

    @Override
    public void setFill(short fill) throws DOMException {

    }

    @Override
    public float getRepeatCount() {
        return 0;
    }

    @Override
    public void setRepeatCount(float repeatCount) throws DOMException {

    }

    @Override
    public float getRepeatDur() {
        return 0;
    }

    @Override
    public void setRepeatDur(float repeatDur) throws DOMException {

    }

    @Override
    public boolean beginElement() {
        return false;
    }

    @Override
    public boolean endElement() {
        return false;
    }

    @Override
    public void pauseElement() {

    }

    @Override
    public void resumeElement() {

    }

    @Override
    public void seekElement(float seekTo) {

    }

    @Override
    public short getFillDefault() {
        return 0;
    }

    @Override
    public void setFillDefault(short fillDefault) throws DOMException {

    }
}
