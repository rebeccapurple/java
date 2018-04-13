package rebeccapurple.dom;

import org.w3c.dom.DOMException;

public abstract class Document extends Node implements org.w3c.dom.Document {

    @Override public org.w3c.dom.Attr createAttribute(String name) throws DOMException { return new Attr(__document, name); }

    @Override public org.w3c.dom.DocumentType getDoctype() { throw new DOMException(DOMException.NOT_SUPPORTED_ERR, null); }
    @Override public org.w3c.dom.DOMImplementation getImplementation() { throw new DOMException(DOMException.NOT_SUPPORTED_ERR, null); }
    @Override public org.w3c.dom.DocumentFragment createDocumentFragment() { throw new DOMException(DOMException.NOT_SUPPORTED_ERR, null); }
    @Override public org.w3c.dom.Text createTextNode(String data) { throw new DOMException(DOMException.NOT_SUPPORTED_ERR, null); }
    @Override public org.w3c.dom.Comment createComment(String data) { throw new DOMException(DOMException.NOT_SUPPORTED_ERR, null); }
    @Override public org.w3c.dom.CDATASection createCDATASection(String data) throws DOMException { throw new DOMException(DOMException.NOT_SUPPORTED_ERR, null); }
    @Override public org.w3c.dom.ProcessingInstruction createProcessingInstruction(String target, String data) throws DOMException { throw new DOMException(DOMException.NOT_SUPPORTED_ERR, null); }
    @Override public org.w3c.dom.EntityReference createEntityReference(String name) throws DOMException { throw new DOMException(DOMException.NOT_SUPPORTED_ERR, null); }
    @Override public org.w3c.dom.NodeList getElementsByTagName(String name) { throw new DOMException(DOMException.NOT_SUPPORTED_ERR, null); }
    @Override public org.w3c.dom.Node importNode(org.w3c.dom.Node importedNode, boolean deep) throws DOMException  { throw new DOMException(DOMException.NOT_SUPPORTED_ERR, null); }
    @Override public org.w3c.dom.Element createElementNS(String namespaceURI, String qualifiedName) throws DOMException { throw new DOMException(DOMException.NOT_SUPPORTED_ERR, null); }
    @Override public org.w3c.dom.Attr createAttributeNS(String namespaceURI, String qualifiedName) throws DOMException { throw new DOMException(DOMException.NOT_SUPPORTED_ERR, null); }
    @Override public org.w3c.dom.NodeList getElementsByTagNameNS(String namespaceURI, String localName) { throw new DOMException(DOMException.NOT_SUPPORTED_ERR, null); }
    @Override public org.w3c.dom.Element getElementById(String elementId) { throw new DOMException(DOMException.NOT_SUPPORTED_ERR, null); }
    @Override public String getInputEncoding() { throw new DOMException(DOMException.NOT_SUPPORTED_ERR, null); }
    @Override public String getXmlEncoding() { throw new DOMException(DOMException.NOT_SUPPORTED_ERR, null); }
    @Override public boolean getXmlStandalone() { throw new DOMException(DOMException.NOT_SUPPORTED_ERR, null); }
    @Override public void setXmlStandalone(boolean xmlStandalone) throws DOMException { throw new DOMException(DOMException.NOT_SUPPORTED_ERR, null); }
    @Override public String getXmlVersion() { throw new DOMException(DOMException.NOT_SUPPORTED_ERR, null); }
    @Override public void setXmlVersion(String xmlVersion) throws DOMException { throw new DOMException(DOMException.NOT_SUPPORTED_ERR, null); }
    @Override public boolean getStrictErrorChecking() { throw new DOMException(DOMException.NOT_SUPPORTED_ERR, null); }
    @Override public void setStrictErrorChecking(boolean strictErrorChecking) { throw new DOMException(DOMException.NOT_SUPPORTED_ERR, null); }
    @Override public String getDocumentURI() { throw new DOMException(DOMException.NOT_SUPPORTED_ERR, null); }
    @Override public void setDocumentURI(String documentURI) { throw new DOMException(DOMException.NOT_SUPPORTED_ERR, null); }
    @Override public org.w3c.dom.Node adoptNode(org.w3c.dom.Node source) throws DOMException { throw new DOMException(DOMException.NOT_SUPPORTED_ERR, null); }
    @Override public org.w3c.dom.DOMConfiguration getDomConfig() { throw new DOMException(DOMException.NOT_SUPPORTED_ERR, null); }
    @Override public void normalizeDocument() { throw new DOMException(DOMException.NOT_SUPPORTED_ERR, null); }
    @Override public org.w3c.dom.Node renameNode(org.w3c.dom.Node n, String namespaceURI, String qualifiedName) throws DOMException { throw new DOMException(DOMException.NOT_SUPPORTED_ERR, null); }

    public Document(Document document) {
        super(document);
    }
}
