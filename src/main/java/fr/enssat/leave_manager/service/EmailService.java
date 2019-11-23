package fr.enssat.leave_manager.service;

import fr.enssat.leave_manager.model.Mail;
import org.springframework.stereotype.Service;

@Service
public interface EmailService {
    void sendEmail(Mail mail);
}
