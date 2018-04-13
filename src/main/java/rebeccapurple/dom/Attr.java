package rebeccapurple.dom;

import org.w3c.dom.DOMException;
import org.w3c.dom.Element;
import org.w3c.dom.TypeInfo;

public class Attr extends Node implements org.w3c.dom.Attr {
    private String __name;
    private String __value;

    @Override public String getName() { return __name; }
    @Override public boolean getSpecified() { return __value != null; }
    @Override public String getValue() { return __value; }
    @Override public void setValue(String value) throws DOMException { __value = value; }

    @Override public Element getOwnerElement() { throw new DOMException(DOMException.NOT_SUPPORTED_ERR, null); }
    @Override public TypeInfo getSchemaTypeInfo() { throw new DOMException(DOMException.NOT_SUPPORTED_ERR, null); }
    @Override public boolean isId() { throw new DOMException(DOMException.NOT_SUPPORTED_ERR, null); }

    public Attr(Document document, String name) {
        super(document);
        __name = name;
    }
}
