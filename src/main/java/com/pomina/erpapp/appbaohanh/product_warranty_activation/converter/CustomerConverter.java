package com.pomina.erpapp.appbaohanh.product_warranty_activation.converter;

import com.pomina.erpapp.appbaohanh.common.converter.BaseConverter;
import com.pomina.erpapp.appbaohanh.product_warranty_activation.dto.request.CustomerCreateDto;
import com.pomina.erpapp.appbaohanh.product_warranty_activation.dto.request.CustomerRequestDto;
import com.pomina.erpapp.appbaohanh.product_warranty_activation.dto.request.CustomerUpdateDto;
import com.pomina.erpapp.appbaohanh.product_warranty_activation.dto.response.CustomerResponseDto;
import com.pomina.erpapp.appbaohanh.product_warranty_activation.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerConverter extends BaseConverter<CustomerRequestDto, CustomerResponseDto, Customer> {
     // Mapping from CreateDto to RequestDto
    CustomerRequestDto toCustomerRequestDto(CustomerCreateDto createDto);

    // Mapping from UpdateDto to RequestDto
    CustomerRequestDto toCustomerRequestDto(CustomerUpdateDto updateDto);



}
