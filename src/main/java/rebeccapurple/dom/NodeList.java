package rebeccapurple.dom;

import org.w3c.dom.DOMException;

import java.util.ArrayList;
import java.util.ListIterator;

import static org.w3c.dom.DOMException.NOT_FOUND_ERR;

public class NodeList implements org.w3c.dom.NodeList {
    private ArrayList<Node> __nodes;

    Node first(){ return __nodes != null ? __nodes.get(0) : null; }
    Node last(){ return __nodes != null && __nodes.size()>0 ? __nodes.get(__nodes.size() - 1) : null; }

    Node previous(Node o){
        Node previous = null;
        if(__nodes != null){
            for (Node __node : __nodes) {
                if (__node == o) {
                    return previous;
                } else {
                    previous = __node;
                }
            }
        }
        return null;
    }

    Node next(Node o){
        if(__nodes != null){
            for(int i = 0; i<__nodes.size(); i++){
                if(__nodes.get(i) == o){
                    return i + 1 < __nodes.size() ? __nodes.get(i + 1) : null;
                }
            }
        }
        return null;
    }

    Node insert(Node reference, Node o){
        if(__nodes != null){
            ListIterator<Node> it = __nodes.listIterator();
            while(it.hasNext()) {
                Node node = it.next();
                if(functional.object.equal(reference, node)){
                    it.add(o);
                    return o;
                }
            }
        }
        throw new DOMException(NOT_FOUND_ERR, "__nodes == null");
    }

    Node replace(Node reference, Node o){
        if(__nodes != null){
            for(int i = 0; i< __nodes.size(); i++)
            {
                if(functional.object.equal(reference, __nodes.get(i))){
                    __nodes.set(i, o);
                    return reference;
                }
            }
        }
        throw new DOMException(NOT_FOUND_ERR, "__nodes == null");
    }

    Node remove(Node o){
        if(__nodes != null){
            __nodes.remove(o);
        }
        return o;
    }

    Node add(Node o){
        functional.collection.add(__nodes == null ? new ArrayList<>() : __nodes,  o);
        return o;
    }

    @Override public org.w3c.dom.Node item(int index) { return __nodes != null && 0 <= index && index < __nodes.size() ? __nodes.get(index) : null; }
    @Override public int getLength() { return __nodes != null ? __nodes.size() : 0; }
}
