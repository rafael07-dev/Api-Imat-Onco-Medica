package com.imat.oncomedica.inventory_management.infrastructure.notification;

import com.imat.oncomedica.inventory_management.domain.entity.Maintenance;
import com.imat.oncomedica.inventory_management.domain.service.NotificationService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailNotificationService implements NotificationService {

    private final JavaMailSender mailSender;

    public EmailNotificationService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void notifyMaintenanceAssigned(Maintenance maintenance) {

        String email = maintenance.getMaintenanceStaff().getEmail();
        String subject = "Nuevo manteniemiento asignado";
        String message = String.format("Hola %s, se te ha asignado un mantenimiento para el equipo: %s,",
                maintenance.getMaintenanceStaff().getFirstName(),
                maintenance.getEquipment().getEquipmentName());


        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(email);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);

        mailSender.send(mailMessage);
    }
}
