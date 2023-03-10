//
// This file was generated by the Eclipse Implementation of JAXB, v2.3.7 
// See https://eclipse-ee4j.github.io/jaxb-ri 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2023.03.09 at 11:47:09 AM GMT+04:00 
//


package ru.tfoms.schemas.person;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * Стандартный ответ, на запрос статуса асинхронной операции
 * 
 * <p>Java class for AsyncPollResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AsyncPollResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://ffoms.ru/types/commonTypes}AsyncStartResponse"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="processingStatus" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AsyncPollResponse", propOrder = {
    "processingStatus"
})
@XmlSeeAlso({
    SuspendOmsPolicyPollResponse.class
})
public class AsyncPollResponse
    extends AsyncStartResponse
{

    @XmlElement(required = true)
    protected String processingStatus;

    /**
     * Gets the value of the processingStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProcessingStatus() {
        return processingStatus;
    }

    /**
     * Sets the value of the processingStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProcessingStatus(String value) {
        this.processingStatus = value;
    }

}
