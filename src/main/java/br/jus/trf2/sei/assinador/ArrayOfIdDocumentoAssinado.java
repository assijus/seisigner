
package br.jus.trf2.sei.assinador;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfIdDocumentoAssinado complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfIdDocumentoAssinado"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="IdDocumentoAssinado" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfIdDocumentoAssinado", propOrder = {
    "idDocumentoAssinado"
})
public class ArrayOfIdDocumentoAssinado {

    @XmlElement(name = "IdDocumentoAssinado")
    protected List<String> idDocumentoAssinado;

    /**
     * Gets the value of the idDocumentoAssinado property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the idDocumentoAssinado property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIdDocumentoAssinado().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getIdDocumentoAssinado() {
        if (idDocumentoAssinado == null) {
            idDocumentoAssinado = new ArrayList<String>();
        }
        return this.idDocumentoAssinado;
    }

}
