
package com.fortinet.fmg.ws.r200806;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>addAdom complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="addAdom">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="servicePass" type="{http://r200806.ws.fmg.fortinet.com/}servicePass" minOccurs="0"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="version" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="mr" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="isBackupMode" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="VPNManagement" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="deviceSNVdom" type="{http://r200806.ws.fmg.fortinet.com/}devSNVdom" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="deviceIDVdom" type="{http://r200806.ws.fmg.fortinet.com/}devIDVdom" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "addAdom", propOrder = {
    "servicePass",
    "name",
    "version",
    "mr",
    "isBackupMode",
    "vpnManagement",
    "deviceSNVdom",
    "deviceIDVdom"
})
public class AddAdom {

    protected ServicePass servicePass;
    @XmlElement(required = true)
    protected String name;
    protected int version;
    protected int mr;
    protected Boolean isBackupMode;
    @XmlElement(name = "VPNManagement")
    protected Boolean vpnManagement;
    protected List<DevSNVdom> deviceSNVdom;
    protected List<DevIDVdom> deviceIDVdom;

    /**
     * 获取servicePass属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ServicePass }
     *     
     */
    public ServicePass getServicePass() {
        return servicePass;
    }

    /**
     * 设置servicePass属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ServicePass }
     *     
     */
    public void setServicePass(ServicePass value) {
        this.servicePass = value;
    }

    /**
     * 获取name属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * 设置name属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * 获取version属性的值。
     * 
     */
    public int getVersion() {
        return version;
    }

    /**
     * 设置version属性的值。
     * 
     */
    public void setVersion(int value) {
        this.version = value;
    }

    /**
     * 获取mr属性的值。
     * 
     */
    public int getMr() {
        return mr;
    }

    /**
     * 设置mr属性的值。
     * 
     */
    public void setMr(int value) {
        this.mr = value;
    }

    /**
     * 获取isBackupMode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsBackupMode() {
        return isBackupMode;
    }

    /**
     * 设置isBackupMode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsBackupMode(Boolean value) {
        this.isBackupMode = value;
    }

    /**
     * 获取vpnManagement属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isVPNManagement() {
        return vpnManagement;
    }

    /**
     * 设置vpnManagement属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setVPNManagement(Boolean value) {
        this.vpnManagement = value;
    }

    /**
     * Gets the value of the deviceSNVdom property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the deviceSNVdom property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDeviceSNVdom().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DevSNVdom }
     * 
     * 
     */
    public List<DevSNVdom> getDeviceSNVdom() {
        if (deviceSNVdom == null) {
            deviceSNVdom = new ArrayList<DevSNVdom>();
        }
        return this.deviceSNVdom;
    }

    /**
     * Gets the value of the deviceIDVdom property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the deviceIDVdom property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDeviceIDVdom().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DevIDVdom }
     * 
     * 
     */
    public List<DevIDVdom> getDeviceIDVdom() {
        if (deviceIDVdom == null) {
            deviceIDVdom = new ArrayList<DevIDVdom>();
        }
        return this.deviceIDVdom;
    }

}
