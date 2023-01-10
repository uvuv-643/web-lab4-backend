package lab.weblab4backend.services;


import lab.weblab4backend.dao.SmsDao;
import lab.weblab4backend.dao.TokenDao;
import lab.weblab4backend.model.SMS;
import lab.weblab4backend.model.Token;

import java.time.ZonedDateTime;

public class SMSService {

    private final SmsDao dao = new SmsDao();

    public SMSService() {
    }

    public SMS findSMS(String phone) {
        return dao.findByPhone(phone);
    }

    public void add(String phone, String token) {

        SMS token1 = new SMS();
        token1.setPhone(phone);
        token1.setToken(token);
        token1.setCreatedAt(ZonedDateTime.now());
        this.dao.save(token1);
    }

}