
package webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for myLine complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="myLine"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="rowUp" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="rowDown" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="columnLeft" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="columnRight" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "myLine", propOrder = {
    "rowUp",
    "rowDown",
    "columnLeft",
    "columnRight"
})
public class MyLine {

    protected int rowUp;
    protected int rowDown;
    protected int columnLeft;
    protected int columnRight;

    /**
     * Gets the value of the rowUp property.
     * 
     */
    public int getRowUp() {
        return rowUp;
    }

    /**
     * Sets the value of the rowUp property.
     * 
     */
    public void setRowUp(int value) {
        this.rowUp = value;
    }

    /**
     * Gets the value of the rowDown property.
     * 
     */
    public int getRowDown() {
        return rowDown;
    }

    /**
     * Sets the value of the rowDown property.
     * 
     */
    public void setRowDown(int value) {
        this.rowDown = value;
    }

    /**
     * Gets the value of the columnLeft property.
     * 
     */
    public int getColumnLeft() {
        return columnLeft;
    }

    /**
     * Sets the value of the columnLeft property.
     * 
     */
    public void setColumnLeft(int value) {
        this.columnLeft = value;
    }

    /**
     * Gets the value of the columnRight property.
     * 
     */
    public int getColumnRight() {
        return columnRight;
    }

    /**
     * Sets the value of the columnRight property.
     * 
     */
    public void setColumnRight(int value) {
        this.columnRight = value;
    }

}
