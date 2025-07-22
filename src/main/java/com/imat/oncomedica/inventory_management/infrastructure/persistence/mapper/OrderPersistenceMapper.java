package com.imat.oncomedica.inventory_management.infrastructure.persistence.mapper;

import com.imat.oncomedica.inventory_management.domain.model.Order;
import com.imat.oncomedica.inventory_management.infrastructure.persistence.entity.OrderEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderPersistenceMapper {

    Order toModel(OrderEntity order);
    OrderEntity toOrderEntity(Order order);
}
