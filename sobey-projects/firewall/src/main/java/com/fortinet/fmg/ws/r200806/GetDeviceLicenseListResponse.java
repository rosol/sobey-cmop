
package com.fortinet.fmg.ws.r200806;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>getDeviceLicenseListResponse complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="getDeviceLicenseListResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://r200806.ws.fmg.fortinet.com/}deviceLicenseList" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getDeviceLicenseListResponse", propOrder = {
    "_return"
})
public class GetDeviceLicenseListResponse {

    @XmlElement(name = "return")
    protected DeviceLicenseList _return;

    /**
     * 获取return属性的值。
     * 
     * @return
     *     possible object is
     *     {@link DeviceLicenseList }
     *     
     */
    public DeviceLicenseList getReturn() {
        return _return;
    }

    /**
     * 设置return属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link DeviceLicenseList }
     *     
     */
    public void setReturn(DeviceLicenseList value) {
        this._return = value;
    }

}
