//
// This file was generated by the Eclipse Implementation of JAXB, v2.3.7 
// See https://eclipse-ee4j.github.io/jaxb-ri 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2023.02.28 at 03:32:10 PM GMT+04:00 
//


package ru.tfoms.schemas.person;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for MilPersonResult complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MilPersonResult"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="oip" type="{http://ffoms.ru/types/commonTypes}Oip" minOccurs="0"/&gt;
 *         &lt;element name="localPersonIndex" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="errors" type="{http://ffoms.ru/types/commonTypes}ListOfErrors" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MilPersonResult", namespace = "http://ffoms.ru/types/insuranceSuspendSchema", propOrder = {
    "oip",
    "localPersonIndex",
    "errors"
})
public class MilPersonResult {

    protected String oip;
    @XmlElement(required = true)
    protected String localPersonIndex;
    protected ListOfErrors errors;

    /**
     * Gets the value of the oip property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOip() {
        return oip;
    }

    /**
     * Sets the value of the oip property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOip(String value) {
        this.oip = value;
    }

    /**
     * Gets the value of the localPersonIndex property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocalPersonIndex() {
        return localPersonIndex;
    }

    /**
     * Sets the value of the localPersonIndex property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocalPersonIndex(String value) {
        this.localPersonIndex = value;
    }

    /**
     * Gets the value of the errors property.
     * 
     * @return
     *     possible object is
     *     {@link ListOfErrors }
     *     
     */
    public ListOfErrors getErrors() {
        return errors;
    }

    /**
     * Sets the value of the errors property.
     * 
     * @param value
     *     allowed object is
     *     {@link ListOfErrors }
     *     
     */
    public void setErrors(ListOfErrors value) {
        this.errors = value;
    }

}
