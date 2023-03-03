//
// This file was generated by the Eclipse Implementation of JAXB, v2.3.7 
// See https://eclipse-ee4j.github.io/jaxb-ri 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2023.03.03 at 12:29:50 PM GMT+04:00 
//


package ru.tfoms.schemas.person;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Данные, идентифицирующие ДУДЛ (достаточно указания типа документа и серии/номера).
 *                 Возможен ввод и поиск данных неактуального документа
 *             
 * 
 * <p>Java class for PersonDocIdentSerNum complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PersonDocIdentSerNum"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="dudlSer" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="dudlNum" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="dudlType" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PersonDocIdentSerNum", propOrder = {
    "dudlSer",
    "dudlNum",
    "dudlType"
})
public class PersonDocIdentSerNum {

    protected String dudlSer;
    @XmlElement(required = true)
    protected String dudlNum;
    @XmlElement(required = true)
    protected String dudlType;

    /**
     * Gets the value of the dudlSer property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDudlSer() {
        return dudlSer;
    }

    /**
     * Sets the value of the dudlSer property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDudlSer(String value) {
        this.dudlSer = value;
    }

    /**
     * Gets the value of the dudlNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDudlNum() {
        return dudlNum;
    }

    /**
     * Sets the value of the dudlNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDudlNum(String value) {
        this.dudlNum = value;
    }

    /**
     * Gets the value of the dudlType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDudlType() {
        return dudlType;
    }

    /**
     * Sets the value of the dudlType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDudlType(String value) {
        this.dudlType = value;
    }

}
