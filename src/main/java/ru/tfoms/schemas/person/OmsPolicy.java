//
// This file was generated by the Eclipse Implementation of JAXB, v2.3.7 
// See https://eclipse-ee4j.github.io/jaxb-ri 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2023.03.01 at 01:57:17 PM GMT+04:00 
//


package ru.tfoms.schemas.person;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * Массив с данными полиса, созданного по итогам регистрации заявления.
 *                 Если полис не создавался, то в данном параметре вернется null
 *             
 * 
 * <p>Java class for OmsPolicy complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="OmsPolicy"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="pcySer" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="pcyNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="enpCalc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="enp" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="pcyDateB" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *         &lt;element name="pcyDateE" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
 *         &lt;element name="pcyDateH" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
 *         &lt;element name="pcyDateT" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
 *         &lt;element name="pcyDateEnpCalc" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
 *         &lt;element name="pcyDatePr" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
 *         &lt;element name="pcyType" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="pcyStatus" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="okato" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="dsource" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="dsourceType" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="descr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="gender" type="{http://ffoms.ru/types/commonTypes}GenderType" minOccurs="0"/&gt;
 *         &lt;element name="insurName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="insurfName" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="insurOgrn" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="insurfOgrn" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="insurCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="insurfCode" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="insurfDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
 *         &lt;element name="tmpcertNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="tmpcertDateB" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
 *         &lt;element name="tmpcertDateE" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
 *         &lt;element name="uekNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="pcyCategory" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="surname" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="patronymic" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="firstName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="birthDay" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
 *         &lt;element name="blankNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OmsPolicy", propOrder = {
    "pcySer",
    "pcyNum",
    "enpCalc",
    "enp",
    "pcyDateB",
    "pcyDateE",
    "pcyDateH",
    "pcyDateT",
    "pcyDateEnpCalc",
    "pcyDatePr",
    "pcyType",
    "pcyStatus",
    "okato",
    "dsource",
    "dsourceType",
    "descr",
    "gender",
    "insurName",
    "insurfName",
    "insurOgrn",
    "insurfOgrn",
    "insurCode",
    "insurfCode",
    "insurfDate",
    "tmpcertNum",
    "tmpcertDateB",
    "tmpcertDateE",
    "uekNum",
    "pcyCategory",
    "surname",
    "patronymic",
    "firstName",
    "birthDay",
    "blankNum"
})
public class OmsPolicy {

    protected String pcySer;
    protected String pcyNum;
    protected String enpCalc;
    @XmlElement(required = true)
    protected String enp;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar pcyDateB;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar pcyDateE;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar pcyDateH;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar pcyDateT;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar pcyDateEnpCalc;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar pcyDatePr;
    @XmlElement(required = true)
    protected String pcyType;
    @XmlElement(required = true)
    protected String pcyStatus;
    @XmlElement(required = true)
    protected String okato;
    @XmlElement(required = true)
    protected String dsource;
    @XmlElement(required = true)
    protected String dsourceType;
    protected String descr;
    @XmlSchemaType(name = "integer")
    protected Integer gender;
    protected String insurName;
    @XmlElement(required = true)
    protected String insurfName;
    protected String insurOgrn;
    @XmlElement(required = true)
    protected String insurfOgrn;
    protected String insurCode;
    @XmlElement(required = true)
    protected String insurfCode;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar insurfDate;
    protected String tmpcertNum;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar tmpcertDateB;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar tmpcertDateE;
    protected String uekNum;
    protected String pcyCategory;
    protected String surname;
    protected String patronymic;
    protected String firstName;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar birthDay;
    protected String blankNum;

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
     * Gets the value of the enpCalc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEnpCalc() {
        return enpCalc;
    }

    /**
     * Sets the value of the enpCalc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEnpCalc(String value) {
        this.enpCalc = value;
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
     * Gets the value of the pcyDateB property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getPcyDateB() {
        return pcyDateB;
    }

    /**
     * Sets the value of the pcyDateB property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setPcyDateB(XMLGregorianCalendar value) {
        this.pcyDateB = value;
    }

    /**
     * Gets the value of the pcyDateE property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getPcyDateE() {
        return pcyDateE;
    }

    /**
     * Sets the value of the pcyDateE property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setPcyDateE(XMLGregorianCalendar value) {
        this.pcyDateE = value;
    }

    /**
     * Gets the value of the pcyDateH property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getPcyDateH() {
        return pcyDateH;
    }

    /**
     * Sets the value of the pcyDateH property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setPcyDateH(XMLGregorianCalendar value) {
        this.pcyDateH = value;
    }

    /**
     * Gets the value of the pcyDateT property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getPcyDateT() {
        return pcyDateT;
    }

    /**
     * Sets the value of the pcyDateT property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setPcyDateT(XMLGregorianCalendar value) {
        this.pcyDateT = value;
    }

    /**
     * Gets the value of the pcyDateEnpCalc property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getPcyDateEnpCalc() {
        return pcyDateEnpCalc;
    }

    /**
     * Sets the value of the pcyDateEnpCalc property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setPcyDateEnpCalc(XMLGregorianCalendar value) {
        this.pcyDateEnpCalc = value;
    }

    /**
     * Gets the value of the pcyDatePr property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getPcyDatePr() {
        return pcyDatePr;
    }

    /**
     * Sets the value of the pcyDatePr property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setPcyDatePr(XMLGregorianCalendar value) {
        this.pcyDatePr = value;
    }

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
     * Gets the value of the pcyStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPcyStatus() {
        return pcyStatus;
    }

    /**
     * Sets the value of the pcyStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPcyStatus(String value) {
        this.pcyStatus = value;
    }

    /**
     * Gets the value of the okato property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOkato() {
        return okato;
    }

    /**
     * Sets the value of the okato property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOkato(String value) {
        this.okato = value;
    }

    /**
     * Gets the value of the dsource property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDsource() {
        return dsource;
    }

    /**
     * Sets the value of the dsource property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDsource(String value) {
        this.dsource = value;
    }

    /**
     * Gets the value of the dsourceType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDsourceType() {
        return dsourceType;
    }

    /**
     * Sets the value of the dsourceType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDsourceType(String value) {
        this.dsourceType = value;
    }

    /**
     * Gets the value of the descr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescr() {
        return descr;
    }

    /**
     * Sets the value of the descr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescr(String value) {
        this.descr = value;
    }

    /**
     * Gets the value of the gender property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getGender() {
        return gender;
    }

    /**
     * Sets the value of the gender property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setGender(Integer value) {
        this.gender = value;
    }

    /**
     * Gets the value of the insurName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInsurName() {
        return insurName;
    }

    /**
     * Sets the value of the insurName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInsurName(String value) {
        this.insurName = value;
    }

    /**
     * Gets the value of the insurfName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInsurfName() {
        return insurfName;
    }

    /**
     * Sets the value of the insurfName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInsurfName(String value) {
        this.insurfName = value;
    }

    /**
     * Gets the value of the insurOgrn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInsurOgrn() {
        return insurOgrn;
    }

    /**
     * Sets the value of the insurOgrn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInsurOgrn(String value) {
        this.insurOgrn = value;
    }

    /**
     * Gets the value of the insurfOgrn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInsurfOgrn() {
        return insurfOgrn;
    }

    /**
     * Sets the value of the insurfOgrn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInsurfOgrn(String value) {
        this.insurfOgrn = value;
    }

    /**
     * Gets the value of the insurCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInsurCode() {
        return insurCode;
    }

    /**
     * Sets the value of the insurCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInsurCode(String value) {
        this.insurCode = value;
    }

    /**
     * Gets the value of the insurfCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInsurfCode() {
        return insurfCode;
    }

    /**
     * Sets the value of the insurfCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInsurfCode(String value) {
        this.insurfCode = value;
    }

    /**
     * Gets the value of the insurfDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getInsurfDate() {
        return insurfDate;
    }

    /**
     * Sets the value of the insurfDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setInsurfDate(XMLGregorianCalendar value) {
        this.insurfDate = value;
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

    /**
     * Gets the value of the tmpcertDateB property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getTmpcertDateB() {
        return tmpcertDateB;
    }

    /**
     * Sets the value of the tmpcertDateB property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setTmpcertDateB(XMLGregorianCalendar value) {
        this.tmpcertDateB = value;
    }

    /**
     * Gets the value of the tmpcertDateE property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getTmpcertDateE() {
        return tmpcertDateE;
    }

    /**
     * Sets the value of the tmpcertDateE property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setTmpcertDateE(XMLGregorianCalendar value) {
        this.tmpcertDateE = value;
    }

    /**
     * Gets the value of the uekNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUekNum() {
        return uekNum;
    }

    /**
     * Sets the value of the uekNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUekNum(String value) {
        this.uekNum = value;
    }

    /**
     * Gets the value of the pcyCategory property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPcyCategory() {
        return pcyCategory;
    }

    /**
     * Sets the value of the pcyCategory property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPcyCategory(String value) {
        this.pcyCategory = value;
    }

    /**
     * Gets the value of the surname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Sets the value of the surname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSurname(String value) {
        this.surname = value;
    }

    /**
     * Gets the value of the patronymic property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPatronymic() {
        return patronymic;
    }

    /**
     * Sets the value of the patronymic property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPatronymic(String value) {
        this.patronymic = value;
    }

    /**
     * Gets the value of the firstName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the value of the firstName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFirstName(String value) {
        this.firstName = value;
    }

    /**
     * Gets the value of the birthDay property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getBirthDay() {
        return birthDay;
    }

    /**
     * Sets the value of the birthDay property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setBirthDay(XMLGregorianCalendar value) {
        this.birthDay = value;
    }

    /**
     * Gets the value of the blankNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBlankNum() {
        return blankNum;
    }

    /**
     * Sets the value of the blankNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBlankNum(String value) {
        this.blankNum = value;
    }

}
