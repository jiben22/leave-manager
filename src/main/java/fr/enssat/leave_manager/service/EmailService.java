package fr.enssat.leave_manager.service;

import fr.enssat.leave_manager.model.Mail;


public interface EmailService {
    void sendEmail(Mail mail);

}

