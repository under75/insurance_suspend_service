//
// This file was generated by the Eclipse Implementation of JAXB, v2.3.7 
// See https://eclipse-ee4j.github.io/jaxb-ri 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2023.02.28 at 03:32:10 PM GMT+04:00 
//


package ru.tfoms.schemas.person;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * Стандартный ответ, на запрос запуска асинхронной операции
 * 
 * <p>Java class for AsyncStartResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AsyncStartResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://ffoms.ru/types/commonTypes}CommonResponseType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="opToken" type="{http://ffoms.ru/types/commonTypes}OpToken" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AsyncStartResponse", propOrder = {
    "opToken"
})
@XmlSeeAlso({
    AsyncPollResponse.class,
    SuspendOmsPolicyStartResponse.class
})
public class AsyncStartResponse
    extends CommonResponseType
{

    protected String opToken;

    /**
     * Gets the value of the opToken property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOpToken() {
        return opToken;
    }

    /**
     * Sets the value of the opToken property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOpToken(String value) {
        this.opToken = value;
    }

}
