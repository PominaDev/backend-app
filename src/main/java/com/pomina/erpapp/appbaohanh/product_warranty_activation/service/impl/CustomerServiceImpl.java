package com.pomina.erpapp.appbaohanh.product_warranty_activation.service.impl;

import com.pomina.erpapp.appbaohanh.common.model.PageRequest;
import com.pomina.erpapp.appbaohanh.common.model.PageResponse;
import com.pomina.erpapp.appbaohanh.common.util.AuditUtil;
import com.pomina.erpapp.appbaohanh.product_warranty_activation.converter.CustomerConverter;
import com.pomina.erpapp.appbaohanh.product_warranty_activation.dto.request.CustomerRequestDto;
import com.pomina.erpapp.appbaohanh.product_warranty_activation.dto.response.CustomerResponseDto;
import com.pomina.erpapp.appbaohanh.product_warranty_activation.entity.Customer;
import com.pomina.erpapp.appbaohanh.product_warranty_activation.mapper.CustomerMapper;
import com.pomina.erpapp.appbaohanh.product_warranty_activation.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerMapper customerMapper;

    private final CustomerConverter customerConverter;

    @Override
    @Transactional
    public int create(CustomerRequestDto dto) {
        Customer customer = customerConverter.toEntity(dto);
        customer.setCreatedAt(AuditUtil.now());
        customer.setCreatedBy(AuditUtil.getCurrentUser());
        customer.setUpdatedAt(AuditUtil.now());
        customer.setUpdatedBy(AuditUtil.getCurrentUser());
        customer.setIsDeleted(AuditUtil.getDefaultIsDeleted());
        customer.setStatus(AuditUtil.getDefaultStatus());
        int result = customerMapper.insert(customer);
        return result > 0 ? 1: 0;
    }

    @Override
    @Transactional
    public int update(Integer id, CustomerRequestDto dto) {
        Customer customer = customerConverter.toEntity(dto);
        customer.setId(Long.valueOf(id));
        customer.setUpdatedAt(AuditUtil.now());
        customer.setUpdatedBy(AuditUtil.getCurrentUser());
        int result = customerMapper.update(customer);
        return result > 0 ? 1: 0;
    }

    @Override
    public CustomerResponseDto getById(Integer id) {

        Customer customerInfo = customerMapper.findById(id);

        if (customerInfo != null) {
            return customerConverter.toResponse(customerInfo);
        }

        return null;
    }

    @Override
    public PageResponse<CustomerResponseDto> search(PageRequest pageRequest) {

        List<Customer> customerList = customerMapper.findAllPaged(pageRequest.getOffset(),
                pageRequest.getSize(),
                pageRequest);

        if (customerList == null || customerList.isEmpty()) {
            return null;
        }

        List<CustomerResponseDto> customerResponse = customerConverter.toResponseList(customerList);

        int totalElements = customerMapper.countAll();

        return PageResponse.createPaged(customerResponse, pageRequest.getPage(), pageRequest.getSize(), totalElements);
    }

    @Override
    public int delete(Integer id) {
        int result = customerMapper.softDeleteById(id);
        return result > 0 ? 1: 0;
    }
}
