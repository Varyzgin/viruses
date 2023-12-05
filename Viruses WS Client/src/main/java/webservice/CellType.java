
package webservice;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for cellType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="cellType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="NONE"/&gt;
 *     &lt;enumeration value="X"/&gt;
 *     &lt;enumeration value="X_CONST"/&gt;
 *     &lt;enumeration value="O"/&gt;
 *     &lt;enumeration value="O_CONST"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "cellType")
@XmlEnum
public enum CellType {

    NONE,
    X,
    X_CONST,
    O,
    O_CONST;

    public String value() {
        return name();
    }

    public static CellType fromValue(String v) {
        return valueOf(v);
    }

}
