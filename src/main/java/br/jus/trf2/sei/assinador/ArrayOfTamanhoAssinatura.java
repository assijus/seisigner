
package br.jus.trf2.sei.assinador;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfTamanhoAssinatura complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfTamanhoAssinatura"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="TamanhoAssinatura" type="{http://www.w3.org/2001/XMLSchema}int" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfTamanhoAssinatura", propOrder = {
    "tamanhoAssinatura"
})
public class ArrayOfTamanhoAssinatura {

    @XmlElement(name = "TamanhoAssinatura", type = Integer.class)
    protected List<Integer> tamanhoAssinatura;

    /**
     * Gets the value of the tamanhoAssinatura property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the tamanhoAssinatura property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTamanhoAssinatura().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Integer }
     * 
     * 
     */
    public List<Integer> getTamanhoAssinatura() {
        if (tamanhoAssinatura == null) {
            tamanhoAssinatura = new ArrayList<Integer>();
        }
        return this.tamanhoAssinatura;
    }

}
