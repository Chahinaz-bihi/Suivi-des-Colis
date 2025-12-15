package org.example.colisservice.service;

import org.example.colisservice.entities.Colis;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendColisDeliveredEmail(String to, Colis colis) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("📦 Colis livré avec succès");
        message.setText(
                "Bonjour,\n\n" +
                        "Votre colis avec la référence " + colis.getReference() + " a été livré.\n\n" +
                        "📍 Destinataire : " + colis.getDestinataire() + "\n" +
                        "⚖️ Poids : " + colis.getPoids() + " kg\n\n" +
                        "Merci pour votre confiance.\n"
        );

        mailSender.send(message);
    }
}

