package model.message;

import javax.xml.bind.annotation.XmlEnumValue;

/**
 * Created by Alexander on 03/06/2017.
 */
public enum Event {
    @XmlEnumValue(value = "message")
    MESSAGE,
    @XmlEnumValue(value = "userlogin")
    USER_LOGIN,
    @XmlEnumValue(value = "userlogout")
    USER_LOGOUT
}