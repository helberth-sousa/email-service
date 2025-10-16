package com.sousa.email_service.controllers;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sousa.email_service.application.EmailSenderService;
import com.sousa.email_service.core.EmailRequest;
import com.sousa.email_service.core.Exceptions.EmailServiceException;
import org.springframework.http.HttpStatus;
//import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/email")
public class EmailSenderController {
    private final EmailSenderService emailSenderService;

    //@Autowired
    public EmailSenderController(EmailSenderService emailSenderService) {
        this.emailSenderService = emailSenderService;
    }

    @PostMapping()
    public ResponseEntity<String> sendEmail(@RequestBody EmailRequest emailRequest) {
        try {
            this.emailSenderService.sendEmail(emailRequest.to(), emailRequest.subject(), emailRequest.body());
            return ResponseEntity.ok("Email sent successfully");
        } catch (EmailServiceException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send email: " + ex.getMessage());
        }
    }
}
