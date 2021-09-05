package pl.sda.taskapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.smsapi.api.SmsFactory;
import pl.smsapi.exception.SmsapiException;

@Service
public class SmsSenderService {

    private final String phoneNumber = "608636414";
    private final SmsFactory smsFactory;

    @Autowired
    private SmsSenderService(SmsFactory smsFactory) {
        this.smsFactory = smsFactory;
    }

    public void sendSms(String message) {
        try {
            smsFactory.actionSend()
                    .setTo(phoneNumber)
                    .setText(message)
                    .execute();
        } catch (SmsapiException e) {
            e.printStackTrace();
        }
    }
}
