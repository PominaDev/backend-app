package com.pomina.erpapp.appbaohanh.product_warranty_activation.service.impl;

import com.pomina.erpapp.appbaohanh.common.config.datasource.CustomDataSource;
import com.pomina.erpapp.appbaohanh.common.config.datasource.DataSourceType;
import com.pomina.erpapp.appbaohanh.common.model.PageRequest;
import com.pomina.erpapp.appbaohanh.common.model.PageResponse;
import com.pomina.erpapp.appbaohanh.product_warranty_activation.converter.CustomerConverter;
import com.pomina.erpapp.appbaohanh.product_warranty_activation.dto.request.CustomerRequestDto;
import com.pomina.erpapp.appbaohanh.product_warranty_activation.dto.response.CustomerResponseDto;
import com.pomina.erpapp.appbaohanh.product_warranty_activation.entity.Customer;
import com.pomina.erpapp.appbaohanh.product_warranty_activation.mapper.CustomerMapper;
import com.pomina.erpapp.appbaohanh.product_warranty_activation.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerMapper customerMapper;

    private final CustomerConverter customerConverter;

    @Override
    @CustomDataSource(DataSourceType.MASTER)
    public int create(CustomerRequestDto dto) {
        Customer customer = customerConverter.toEntity(dto);
        return customerMapper.insert(customer);
    }

    @Override
    @CustomDataSource(DataSourceType.MASTER)
    public int update(Integer id, CustomerRequestDto dto) {
        Customer customer = customerConverter.toEntity(dto);
        return customerMapper.update(customer);
    }

    @Override
    @CustomDataSource(DataSourceType.SLAVE)
    public CustomerResponseDto getById(Integer id) {

        Customer customerInfo = customerMapper.findById(id);

        if (customerInfo != null) {
            return customerConverter.toResponse(customerInfo);
        }

        return null;
    }

    @Override
    @CustomDataSource(DataSourceType.SLAVE)
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

    @CustomDataSource(DataSourceType.MASTER)
    @Override
    public int delete(Integer id) {
        return customerMapper.deleteById(id);
    }
}
