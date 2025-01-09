package com.imat.oncomedica.inventory_management.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import lombok.Data;
import java.util.List;

@Data
public class MaintenanceScheduleDTO {
    private Integer id;
    private Integer equipmentId;
    @Valid
    @JsonProperty("monthlyMaintenances")
    private List<MonthlyMaintenanceDTO> monthlyMaintenances;
}