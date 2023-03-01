//
// This file was generated by the Eclipse Implementation of JAXB, v2.3.7 
// See https://eclipse-ee4j.github.io/jaxb-ri 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2023.03.01 at 01:57:17 PM GMT+04:00 
//


package ru.tfoms.schemas.person;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * Данные, идентифицирующие полис. Только для МО, СМО, ТФОМС.
 *                 Для корректной идентификации следует заполнить только поля, соответствующие типу полиса
 *                 Возможен ввод и поиск данных неактуального документа
 *             
 * 
 * <p>Java class for PcyData complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PcyData"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="pcyType" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;pattern value="[СВЕПЭКХМЦ]"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="enp" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;pattern value="\d{16}"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="pcySer" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="pcyNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="tmpcertNum" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;pattern value="\d{9}"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PcyData", propOrder = {
    "pcyType",
    "enp",
    "pcySer",
    "pcyNum",
    "tmpcertNum"
})
public class PcyData {

    protected String pcyType;
    protected String enp;
    protected String pcySer;
    protected String pcyNum;
    protected String tmpcertNum;

    /**
     * Gets the value of the pcyType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPcyType() {
        return pcyType;
    }

    /**
     * Sets the value of the pcyType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPcyType(String value) {
        this.pcyType = value;
    }

    /**
     * Gets the value of the enp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEnp() {
        return enp;
    }

    /**
     * Sets the value of the enp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEnp(String value) {
        this.enp = value;
    }

    /**
     * Gets the value of the pcySer property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPcySer() {
        return pcySer;
    }

    /**
     * Sets the value of the pcySer property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPcySer(String value) {
        this.pcySer = value;
    }

    /**
     * Gets the value of the pcyNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPcyNum() {
        return pcyNum;
    }

    /**
     * Sets the value of the pcyNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPcyNum(String value) {
        this.pcyNum = value;
    }

    /**
     * Gets the value of the tmpcertNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTmpcertNum() {
        return tmpcertNum;
    }

    /**
     * Sets the value of the tmpcertNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTmpcertNum(String value) {
        this.tmpcertNum = value;
    }

}
