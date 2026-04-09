package com.example.demo.controller;

import com.example.demo.service.EmailService;
import jakarta.mail.MessagingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/notifications")
public class EmailController {

    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    // POST /api/notifications/email/simple
    @PostMapping("/email/simple")
    public ResponseEntity<String> sendSimpleEmail(@RequestBody SimpleEmailRequest req) {
        emailService.sendSimpleEmail(req.to(), req.subject(), req.body());
        return ResponseEntity.ok("Simple email sent");
    }

    // POST /api/notifications/email/template
    @PostMapping("/email/template")
    public ResponseEntity<String> sendTemplateEmail(@RequestBody TemplateEmailRequest req)
            throws MessagingException {
        Map<String, Object> vars = new HashMap<>(req.variables());
        emailService.sendTemplateEmail(req.to(), req.subject(), "notification", vars);
        return ResponseEntity.ok("Template email sent");
    }

    // POST /api/notifications/email/attachment
    @PostMapping("/email/attachment")
    public ResponseEntity<String> sendEmailWithAttachment(@RequestBody AttachmentEmailRequest req)
            throws MessagingException {
        emailService.sendEmailWithAttachment(req.to(), req.subject(), req.body(), req.filePath());
        return ResponseEntity.ok("Email with attachment sent");
    }

    // request records

    public record SimpleEmailRequest(String to, String subject, String body) {}

    public record TemplateEmailRequest(
            String to,
            String subject,
            Map<String, String> variables
    ) {}

    public record AttachmentEmailRequest(
            String to,
            String subject,
            String body,
            String filePath
    ) {}
}