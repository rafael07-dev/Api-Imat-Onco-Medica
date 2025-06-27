package com.imat.oncomedica.inventory_management.application.mapper;

import com.imat.oncomedica.inventory_management.application.dto.order.OrderResponse;
import com.imat.oncomedica.inventory_management.domain.entity.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderResponse toOrderResponse(Order order);

}
