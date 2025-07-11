package com.pomina.erpapp.appbaohanh.product_warranty_activation.converter;

import com.pomina.erpapp.appbaohanh.common.converter.BaseConverter;
import com.pomina.erpapp.appbaohanh.product_warranty_activation.dto.request.CustomerRequestDto;
import com.pomina.erpapp.appbaohanh.product_warranty_activation.dto.request.DistributorRequestDto;
import com.pomina.erpapp.appbaohanh.product_warranty_activation.dto.response.CustomerResponseDto;
import com.pomina.erpapp.appbaohanh.product_warranty_activation.dto.response.DistributorResponseDto;
import com.pomina.erpapp.appbaohanh.product_warranty_activation.entity.Customer;
import com.pomina.erpapp.appbaohanh.product_warranty_activation.entity.Distributor;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DistributorConverter extends BaseConverter<DistributorRequestDto, DistributorResponseDto, Distributor> {
}
