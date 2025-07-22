package com.imat.oncomedica.inventory_management.infrastructure.persistence.mapper;

import com.imat.oncomedica.inventory_management.domain.model.Equipment;
import com.imat.oncomedica.inventory_management.infrastructure.persistence.entity.EquipmentEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EquipmentPersistenceMapper {

    Equipment toEquipmentModel(EquipmentEntity entity);
}
