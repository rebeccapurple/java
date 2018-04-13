package rebeccapurple.dom;

import org.w3c.dom.DOMException;

import static org.w3c.dom.DOMException.NOT_FOUND_ERR;
import static org.w3c.dom.DOMException.NOT_SUPPORTED_ERR;

public abstract class Node implements org.w3c.dom.Node {
    protected static class sibling {
        protected static Node previous(Node o){
            if(o.__parent != null && o.__parent.__children != null){
                return o.__parent.__children.previous(o);
            }
            return null;
        }

        protected static Node next(Node o){
            if(o.__parent != null && o.__parent.__children != null){
                return o.__parent.__children.next(o);
            }
            return null;
        }
    }

    protected String __name;
    protected String __value;
    protected short __type;
    protected Node __parent;
    protected NodeList __children;
    protected NamedNodeMap __attributes;
    protected Document __document;


    @Override public String getNodeName() { return __name; }
    @Override public String getNodeValue() throws DOMException { return __value; }
    @Override public void setNodeValue(String v) throws DOMException { __value = v; }
    @Override public short getNodeType() { return __type; }
    @Override public org.w3c.dom.Node getParentNode() { return __parent; }
    @Override public org.w3c.dom.NodeList getChildNodes() { return __children; }
    @Override public org.w3c.dom.Node getFirstChild() { return __children != null ? __children.first() : null; }
    @Override public org.w3c.dom.Node getLastChild() { return __children != null ? __children.last(): null; }
    @Override public org.w3c.dom.Node getPreviousSibling() { return sibling.previous(this); }
    @Override public org.w3c.dom.Node getNextSibling() { return sibling.next(this); }
    @Override public org.w3c.dom.NamedNodeMap getAttributes() { return __attributes; }
    @Override public org.w3c.dom.Document getOwnerDocument() { return __document; }

    @Override
    public org.w3c.dom.Node insertBefore(org.w3c.dom.Node child, org.w3c.dom.Node reference) throws DOMException {
        if(child instanceof Node && reference instanceof Node){
            if(__children != null){
                return __children.insert((Node) reference, (Node) child);
            }
            throw new DOMException(NOT_FOUND_ERR, "__children == null");
        }
        throw new DOMException(NOT_SUPPORTED_ERR, "(child instanceof Node && reference instanceof Node) == false");
    }

    @Override
    public org.w3c.dom.Node replaceChild(org.w3c.dom.Node child, org.w3c.dom.Node reference) throws DOMException {
        if(child instanceof Node && reference instanceof Node){
            if(__children != null){
                return __children.replace((Node) reference, (Node) child);
            }
            throw new DOMException(NOT_FOUND_ERR, "__children == null");
        }
        throw new DOMException(NOT_SUPPORTED_ERR, "(child instanceof Node && reference instanceof Node) == false");
    }

    @Override
    public org.w3c.dom.Node removeChild(org.w3c.dom.Node child) throws DOMException {
        if(child instanceof Node){
            if(__children != null){
                return __children.remove((Node) child);
            }
        }
        return null;
    }

    @Override
    public org.w3c.dom.Node appendChild(org.w3c.dom.Node child) throws DOMException {
        if(child instanceof Node){
            if(__children == null){
                __children = new NodeList();
            }
            return __children.add((Node) child);
        }
        return null;
    }

    @Override public boolean hasChildNodes() { return __children!=null && __children.getLength() > 0; }
    @Override public boolean hasAttributes() { return __attributes != null && __attributes.getLength() > 0; }

    @Override public org.w3c.dom.Node cloneNode(boolean deep) { throw new DOMException(DOMException.NOT_SUPPORTED_ERR, null); }
    @Override public void normalize() { throw new DOMException(DOMException.NOT_SUPPORTED_ERR, null); }
    @Override public boolean isSupported(String feature, String version) { throw new DOMException(DOMException.NOT_SUPPORTED_ERR, null); }
    @Override public String getNamespaceURI() { throw new DOMException(DOMException.NOT_SUPPORTED_ERR, null); }
    @Override public String getPrefix() { throw new DOMException(DOMException.NOT_SUPPORTED_ERR, null); }
    @Override public String getLocalName() { throw new DOMException(DOMException.NOT_SUPPORTED_ERR, null); }
    @Override public String getBaseURI() { throw new DOMException(DOMException.NOT_SUPPORTED_ERR, null); }

    @Override public void setPrefix(String prefix) throws DOMException { throw new DOMException(DOMException.NOT_SUPPORTED_ERR, null); }
    @Override public short compareDocumentPosition(org.w3c.dom.Node other) throws DOMException { throw new DOMException(DOMException.NOT_SUPPORTED_ERR, null); }
    @Override public String getTextContent() throws DOMException { throw new DOMException(DOMException.NOT_SUPPORTED_ERR, null); }
    @Override public void setTextContent(String textContent) throws DOMException { throw new DOMException(DOMException.NOT_SUPPORTED_ERR, null); }
    @Override public boolean isSameNode(org.w3c.dom.Node other) { throw new DOMException(DOMException.NOT_SUPPORTED_ERR, null); }
    @Override public String lookupPrefix(String namespaceURI) { throw new RuntimeException(); }
    @Override public boolean isDefaultNamespace(String namespaceURI) { throw new DOMException(DOMException.NOT_SUPPORTED_ERR, null); }
    @Override public String lookupNamespaceURI(String prefix) { throw new DOMException(DOMException.NOT_SUPPORTED_ERR, null); }
    @Override public boolean isEqualNode(org.w3c.dom.Node arg) { throw new DOMException(DOMException.NOT_SUPPORTED_ERR, null); }
    @Override public Object getFeature(String feature, String version) { throw new DOMException(DOMException.NOT_SUPPORTED_ERR, null); }
    @Override public Object setUserData(String key, Object data, org.w3c.dom.UserDataHandler handler) { throw new DOMException(DOMException.NOT_SUPPORTED_ERR, null); }
    @Override public Object getUserData(String key) { throw new DOMException(DOMException.NOT_SUPPORTED_ERR, null); }

    public Node(Document document){
        __document = document;
        __name = "";
        __value = null;
        __type = 0;
        __parent = null;
        __children = null;
        __attributes = null;
    }
}
