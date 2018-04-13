package rebeccapurple.dom;

import org.w3c.dom.DOMException;

import java.util.ArrayList;

import static org.w3c.dom.DOMException.NOT_SUPPORTED_ERR;

public class NamedNodeMap implements org.w3c.dom.NamedNodeMap {
    private ArrayList<Node> __list;

    @Override public org.w3c.dom.Node getNamedItem(String name) { return functional.collection.get(__list, name, (node, key) -> functional.object.equal(node.__name, key)); }

    @Override
    public org.w3c.dom.Node setNamedItem(org.w3c.dom.Node o) throws DOMException {
        if(o instanceof Node){
            Node n = (Node) o;
            return functional.collection.put(__list!=null ? __list : new ArrayList<>(), n, (node)-> functional.string.equal(n.__name, node.__name));
        } else {
            throw new DOMException(NOT_SUPPORTED_ERR, "(o instanceof Node) == false");
        }
    }

    @Override public org.w3c.dom.Node removeNamedItem(String name) throws DOMException { return functional.collection.del(__list, name, (node, k) -> functional.string.equal(node.__name, k)); }
    @Override public org.w3c.dom.Node item(int index) { return __list != null && 0<= index && index < __list.size() ? __list.get(index) : null; }
    @Override public int getLength() { return __list!=null ? __list.size() : 0; }

    @Override public org.w3c.dom.Node getNamedItemNS(String namespaceURI, String localName) throws DOMException { throw new DOMException(NOT_SUPPORTED_ERR, ""); }
    @Override public org.w3c.dom.Node setNamedItemNS(org.w3c.dom.Node arg) throws DOMException { throw new DOMException(NOT_SUPPORTED_ERR, ""); }
    @Override public org.w3c.dom.Node removeNamedItemNS(String namespaceURI, String localName) throws DOMException { throw new DOMException(NOT_SUPPORTED_ERR, ""); }
}
