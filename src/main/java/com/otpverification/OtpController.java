package com.otpverification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OtpController {

    @Autowired
    private OtpService otpService;

    @PostMapping("/send")
    public ResponseEntity<String> sendOtp(@RequestParam String phoneNumber) {
        System.out.println("+"+phoneNumber);
       // System.out.println(phoneNumber);

        String otp = otpService.generateOtp("+"+phoneNumber);
        //System.out.print(otp);
        // Store the otp and associate it with the user in ur db
        return ResponseEntity.ok("OTP sent successfully to: "+phoneNumber);
    }

    @PostMapping("/verify")
    public ResponseEntity<String> verifyOtp(@RequestParam String phoneNumber, @RequestParam String otp) {
        // Retrieve the stored OTP for the user from the db and compare it with the submitted otp
        // If they match, the otp is valid
        return ResponseEntity.ok("OTP is valid");  // Otp verified successfully
    }
}
