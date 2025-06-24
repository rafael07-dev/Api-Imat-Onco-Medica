package com.imat.oncomedica.inventory_management.infrastructure.report;

import com.imat.oncomedica.inventory_management.domain.entity.Order;
import com.imat.oncomedica.inventory_management.domain.exception.ReportPdfGeneratorException;
import com.imat.oncomedica.inventory_management.domain.service.ReportService;
import net.sf.jasperreports.engine.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@Component
public class OrderPdfGenerator implements ReportService {

    private static final String TEMPLATE_PATH = "/reports/order_template.jrxml";
    private static final String OUTPUT_FOLDER = "uploads/orders/";
    private static final Logger log = LoggerFactory.getLogger(OrderPdfGenerator.class);

    @Override
    public void generateOderReport(Order order) {
        try {
            InputStream templateStream = getClass().getResourceAsStream(TEMPLATE_PATH);
            JasperReport jasperReport = JasperCompileManager.compileReport(templateStream);

            Map<String, Object> parameters = new HashMap<>();

            parameters.put("id", order.getId());
            parameters.put("creationDate", order.getCreationDate());
            parameters.put("startDate", order.getMaintenance().getStartDate());
            parameters.put("startTime", order.getMaintenance().getStartTime());
            parameters.put("deliveryDate", order.getMaintenance().getDeliveryDate());
            parameters.put("deliveryTime", order.getMaintenance().getDeliveryTime());
            parameters.put("timeUsed", order.getMaintenance().getTimeUsed());
            parameters.put("maintenanceStaffName", order.getMaintenanceStaff().getFirstName() + " "+ order.getMaintenanceStaff().getLastName());
            parameters.put("occupation", order.getMaintenanceStaff().getOccupation());
            parameters.put("equipmentName", order.getEquipment().getEquipmentName());
            parameters.put("observation", order.getMaintenance().getObservations());

            String folderPath = OUTPUT_FOLDER + order.getId() + "/";
            parameters.put("evidenceImg", folderPath + "evidence.jpg");
            parameters.put("signatureImg", folderPath + "signature_staff.jpg");

            JRDataSource dataSource = new JREmptyDataSource();

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

            File outputDir = new File(folderPath);
            if (!outputDir.exists()) outputDir.mkdirs();

            String outputFile = folderPath + "order.pdf";
            JasperExportManager.exportReportToPdfFile(jasperPrint, outputFile);

            log.info("pdf generated");

        }catch (Exception e){
            log.error("error generating pdf");
            throw new ReportPdfGeneratorException();
        }
    }
}
