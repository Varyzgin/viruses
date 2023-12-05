
package webservice;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the webservice package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _AvailableMovesDownloader_QNAME = new QName("http://server/", "availableMovesDownloader");
    private final static QName _AvailableMovesDownloaderResponse_QNAME = new QName("http://server/", "availableMovesDownloaderResponse");
    private final static QName _Connect_QNAME = new QName("http://server/", "connect");
    private final static QName _ConnectResponse_QNAME = new QName("http://server/", "connectResponse");
    private final static QName _MovesHistoryDownloader_QNAME = new QName("http://server/", "movesHistoryDownloader");
    private final static QName _MovesHistoryDownloaderResponse_QNAME = new QName("http://server/", "movesHistoryDownloaderResponse");
    private final static QName _MovesSender_QNAME = new QName("http://server/", "movesSender");
    private final static QName _MovesSenderResponse_QNAME = new QName("http://server/", "movesSenderResponse");
    private final static QName _Push_QNAME = new QName("http://server/", "push");
    private final static QName _PushResponse_QNAME = new QName("http://server/", "pushResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: webservice
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AvailableMovesDownloader }
     * 
     */
    public AvailableMovesDownloader createAvailableMovesDownloader() {
        return new AvailableMovesDownloader();
    }

    /**
     * Create an instance of {@link AvailableMovesDownloaderResponse }
     * 
     */
    public AvailableMovesDownloaderResponse createAvailableMovesDownloaderResponse() {
        return new AvailableMovesDownloaderResponse();
    }

    /**
     * Create an instance of {@link Connect }
     * 
     */
    public Connect createConnect() {
        return new Connect();
    }

    /**
     * Create an instance of {@link ConnectResponse }
     * 
     */
    public ConnectResponse createConnectResponse() {
        return new ConnectResponse();
    }

    /**
     * Create an instance of {@link MovesHistoryDownloader }
     * 
     */
    public MovesHistoryDownloader createMovesHistoryDownloader() {
        return new MovesHistoryDownloader();
    }

    /**
     * Create an instance of {@link MovesHistoryDownloaderResponse }
     * 
     */
    public MovesHistoryDownloaderResponse createMovesHistoryDownloaderResponse() {
        return new MovesHistoryDownloaderResponse();
    }

    /**
     * Create an instance of {@link MovesSender }
     * 
     */
    public MovesSender createMovesSender() {
        return new MovesSender();
    }

    /**
     * Create an instance of {@link MovesSenderResponse }
     * 
     */
    public MovesSenderResponse createMovesSenderResponse() {
        return new MovesSenderResponse();
    }

    /**
     * Create an instance of {@link Push }
     * 
     */
    public Push createPush() {
        return new Push();
    }

    /**
     * Create an instance of {@link PushResponse }
     * 
     */
    public PushResponse createPushResponse() {
        return new PushResponse();
    }

    /**
     * Create an instance of {@link Cell }
     * 
     */
    public Cell createCell() {
        return new Cell();
    }

    /**
     * Create an instance of {@link CellTypeArray }
     * 
     */
    public CellTypeArray createCellTypeArray() {
        return new CellTypeArray();
    }

    /**
     * Create an instance of {@link BooleanArray }
     * 
     */
    public BooleanArray createBooleanArray() {
        return new BooleanArray();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AvailableMovesDownloader }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AvailableMovesDownloader }{@code >}
     */
    @XmlElementDecl(namespace = "http://server/", name = "availableMovesDownloader")
    public JAXBElement<AvailableMovesDownloader> createAvailableMovesDownloader(AvailableMovesDownloader value) {
        return new JAXBElement<AvailableMovesDownloader>(_AvailableMovesDownloader_QNAME, AvailableMovesDownloader.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AvailableMovesDownloaderResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AvailableMovesDownloaderResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://server/", name = "availableMovesDownloaderResponse")
    public JAXBElement<AvailableMovesDownloaderResponse> createAvailableMovesDownloaderResponse(AvailableMovesDownloaderResponse value) {
        return new JAXBElement<AvailableMovesDownloaderResponse>(_AvailableMovesDownloaderResponse_QNAME, AvailableMovesDownloaderResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Connect }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Connect }{@code >}
     */
    @XmlElementDecl(namespace = "http://server/", name = "connect")
    public JAXBElement<Connect> createConnect(Connect value) {
        return new JAXBElement<Connect>(_Connect_QNAME, Connect.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConnectResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ConnectResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://server/", name = "connectResponse")
    public JAXBElement<ConnectResponse> createConnectResponse(ConnectResponse value) {
        return new JAXBElement<ConnectResponse>(_ConnectResponse_QNAME, ConnectResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MovesHistoryDownloader }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link MovesHistoryDownloader }{@code >}
     */
    @XmlElementDecl(namespace = "http://server/", name = "movesHistoryDownloader")
    public JAXBElement<MovesHistoryDownloader> createMovesHistoryDownloader(MovesHistoryDownloader value) {
        return new JAXBElement<MovesHistoryDownloader>(_MovesHistoryDownloader_QNAME, MovesHistoryDownloader.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MovesHistoryDownloaderResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link MovesHistoryDownloaderResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://server/", name = "movesHistoryDownloaderResponse")
    public JAXBElement<MovesHistoryDownloaderResponse> createMovesHistoryDownloaderResponse(MovesHistoryDownloaderResponse value) {
        return new JAXBElement<MovesHistoryDownloaderResponse>(_MovesHistoryDownloaderResponse_QNAME, MovesHistoryDownloaderResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MovesSender }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link MovesSender }{@code >}
     */
    @XmlElementDecl(namespace = "http://server/", name = "movesSender")
    public JAXBElement<MovesSender> createMovesSender(MovesSender value) {
        return new JAXBElement<MovesSender>(_MovesSender_QNAME, MovesSender.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MovesSenderResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link MovesSenderResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://server/", name = "movesSenderResponse")
    public JAXBElement<MovesSenderResponse> createMovesSenderResponse(MovesSenderResponse value) {
        return new JAXBElement<MovesSenderResponse>(_MovesSenderResponse_QNAME, MovesSenderResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Push }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Push }{@code >}
     */
    @XmlElementDecl(namespace = "http://server/", name = "push")
    public JAXBElement<Push> createPush(Push value) {
        return new JAXBElement<Push>(_Push_QNAME, Push.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PushResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link PushResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://server/", name = "pushResponse")
    public JAXBElement<PushResponse> createPushResponse(PushResponse value) {
        return new JAXBElement<PushResponse>(_PushResponse_QNAME, PushResponse.class, null, value);
    }

}
