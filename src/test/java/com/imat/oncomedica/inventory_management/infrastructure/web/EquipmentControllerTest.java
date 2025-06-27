package com.imat.oncomedica.inventory_management.infrastructure.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.imat.oncomedica.inventory_management.application.dto.equipment.CreateEquipmentRequest;
import com.imat.oncomedica.inventory_management.application.dto.equipment.EquipmentResponse;
import com.imat.oncomedica.inventory_management.domain.service.EquipmentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.MediaType;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class EquipmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private EquipmentService equipmentService;


    @Test
    void shouldCreateEquipmentSuccessfully() throws Exception {

        CreateEquipmentRequest request = new CreateEquipmentRequest();

        request.setEquipmentName("CHILLER CENTRIFUGO 1");
        request.setType("CHILLER");
        request.setInventoryCode("313GTII0056-000");
        request.setBrand("LG");
        request.setModel("RCWFLDD");
        request.setSeries("8101200003");
        request.setLocation("AZOTEA");
        request.setArea("TORRE 3");
        request.setFrequency("MENSUAL");
        request.setFloor("13");
        request.setTower("3");

        EquipmentResponse equipmentResponse = getEquipmentResponse(request);

        Mockito.when(equipmentService.saveEquipment(Mockito.any(CreateEquipmentRequest.class)))
                .thenReturn(equipmentResponse);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/equipments/create")
                        .contentType(String.valueOf(MediaType.APPLICATION_JSON))
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "/api/equipments/1"))
                .andExpect((ResultMatcher) jsonPath("$.equipmentName").value("CHILLER CENTRIFUGO 1"));
    }

    private static EquipmentResponse getEquipmentResponse(CreateEquipmentRequest request) {
        EquipmentResponse equipmentResponse = new EquipmentResponse();

        equipmentResponse.setId(1);
        equipmentResponse.setEquipmentName(request.getEquipmentName());
        equipmentResponse.setType(request.getType());
        equipmentResponse.setInventoryCode(request.getInventoryCode());
        equipmentResponse.setBrand(request.getBrand());
        equipmentResponse.setModel(request.getModel());
        equipmentResponse.setSeries(request.getSeries());
        equipmentResponse.setLocation(request.getLocation());
        equipmentResponse.setArea(request.getArea());
        equipmentResponse.setFrequency(request.getFrequency());
        equipmentResponse.setFloor(request.getFloor());
        equipmentResponse.setTower(request.getTower());
        return equipmentResponse;
    }
}
