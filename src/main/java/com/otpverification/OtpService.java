package com.otpverification;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import java.util.Random;

@Service
public class OtpService {

    @Value("${twilio.accountSID}")
    public String accountSID;

    @Value("${twilio.authToken}")
    public String authToken;
    @Value("${twilio.twilioPhoneNo}")
    public String twilioPhoneNo;  // +16562130877-----Twilio phone number

    //-------------------generateOtp------------------------------------------------
    public String generateOtp(String to) {
        //Generate a random otp, eg. a 6-digit no.
        int otp = new Random().nextInt(900000) + 100000;
        // Send the otp via Twilio Sms
       // sendOtp(to, String.valueOf(otp));
        return String.valueOf(otp);
    }

    //-------------------sendOtp------------------------------------------------
    public boolean sendOtp(String phoneNumber, String otp) {
//        System.out.println(authToken);
//        System.out.println(accountSID);

        Twilio.init(accountSID, authToken);   // it will check whether user is authenticated or not

        Message message = Message.creator(
                new PhoneNumber(phoneNumber),
                new PhoneNumber(twilioPhoneNo),
                "Your OTP is: " + otp
        ).create();
        System.out.print(message.getStatus().equals(Message.Status.SENT));
        return message.getStatus().equals(Message.Status.SENT);
    }
}
