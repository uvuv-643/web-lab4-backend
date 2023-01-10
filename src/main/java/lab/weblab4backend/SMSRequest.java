package lab.weblab4backend;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SMSRequest {
    @XmlElement
    public String phone;
}
