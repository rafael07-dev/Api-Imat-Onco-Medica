package com.imat.oncomedica.inventory_management.infrastructure.report;

import com.imat.oncomedica.inventory_management.domain.entity.Order;
import com.imat.oncomedica.inventory_management.domain.exception.OrderNotFoundException;
import com.imat.oncomedica.inventory_management.domain.exception.ReportPdfGeneratorException;
import com.imat.oncomedica.inventory_management.domain.service.ReportService;
import net.sf.jasperreports.engine.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@Component
public class OrderPdfGenerator implements ReportService {

    private static final String OUTPUT_FOLDER = "uploads/orders";
    private static final Logger log = LoggerFactory.getLogger(OrderPdfGenerator.class);

    @Override
    public void generateOderReport(Order order) {
        try {

            InputStream templateStream = getClass().getClassLoader().getResourceAsStream("reports/order_template.jrxml");

            System.out.println("TemplateStream: " + templateStream);
            if (templateStream == null) throw new OrderNotFoundException("template not found");

            Map<String, Object> parameters = new HashMap<>();

            parameters.put("id", order.getId());
            parameters.put("creationDate", order.getCreationDate().toString());
            parameters.put("startDate", order.getMaintenance().getStartDate().toString());
            parameters.put("startTime", order.getMaintenance().getStartTime().toString());
            parameters.put("deliveryDate", order.getMaintenance().getDeliveryDate().toString());
            parameters.put("deliveryTime", order.getMaintenance().getDeliveryTime().toString());
            parameters.put("timeUsed", order.getMaintenance().getTimeUsed().toString());
            parameters.put("maintenanceStaffName", order.getMaintenanceStaff().getFirstName() + " "+ order.getMaintenanceStaff().getLastName());
            parameters.put("maintenanceStaffName2", order.getMaintenanceStaff().getFirstName() + " "+ order.getMaintenanceStaff().getLastName());
            parameters.put("occupation", order.getMaintenanceStaff().getOccupation());
            parameters.put("occupation2", order.getMaintenanceStaff().getOccupation());
            parameters.put("equipmentName", order.getEquipment().getEquipmentName());
            parameters.put("observation", order.getMaintenance().getStaffObservations());

            String folderPath = Paths.get(OUTPUT_FOLDER, order.getId().toString()).toAbsolutePath().toString();

            Path signaturePath = Paths.get(order.getMaintenanceStaff().getSignaturePath()).toAbsolutePath();

            System.out.println("Folder path: " + folderPath);

            System.out.println("signature path: " + signaturePath);

            if (!signaturePath.toFile().exists()) {
                throw new ReportPdfGeneratorException("Firma no encontrada: " + signaturePath);
            }

            InputStream signatureStream = Files.newInputStream(signaturePath);
            parameters.put("signatureImg", signatureStream);

            InputStream signatureStream2 = Files.newInputStream(signaturePath);
            parameters.put("signature2Img", signatureStream2);


            JasperReport jasperReportC = JasperCompileManager.compileReport(templateStream);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReportC, parameters, new JREmptyDataSource());

            File outputDir = new File(folderPath);
            if (!outputDir.exists()) outputDir.mkdirs();

            Path outputFile = Paths.get(folderPath, "order.pdf");
            JasperExportManager.exportReportToPdfFile(jasperPrint, outputFile.toString());

            log.info("pdf generated");

        }catch (Exception e){
            log.error("error generating pdf");
            throw new ReportPdfGeneratorException("error: " + e.getMessage());
        }
    }
}
