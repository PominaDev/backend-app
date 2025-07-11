package com.pomina.erpapp.appbaohanh.product_warranty_activation.controller;

import com.pomina.erpapp.appbaohanh.common.constant.ApiConstants;
import com.pomina.erpapp.appbaohanh.common.controller.BaseController;
import com.pomina.erpapp.appbaohanh.common.handler.ApiResponse;
import com.pomina.erpapp.appbaohanh.common.handler.ResponseHandler;
import com.pomina.erpapp.appbaohanh.common.model.PageRequest;
import com.pomina.erpapp.appbaohanh.common.model.PageResponse;
import com.pomina.erpapp.appbaohanh.product_warranty_activation.converter.CustomerConverter;
import com.pomina.erpapp.appbaohanh.product_warranty_activation.dto.request.CustomerCreateDto;
import com.pomina.erpapp.appbaohanh.product_warranty_activation.dto.request.CustomerRequestDto;
import com.pomina.erpapp.appbaohanh.product_warranty_activation.dto.request.CustomerUpdateDto;
import com.pomina.erpapp.appbaohanh.product_warranty_activation.dto.response.CustomerResponseDto;
import com.pomina.erpapp.appbaohanh.product_warranty_activation.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiConstants.ApiCustomer.BASE)
@RequiredArgsConstructor
public class CustomerController extends BaseController<CustomerCreateDto, CustomerUpdateDto, CustomerResponseDto, Integer> {

    private final CustomerConverter customerConverter;

    private final CustomerService customerService;

    @Override
    @PostMapping
    public ResponseEntity<ApiResponse<Integer>> create(@Valid @RequestBody CustomerCreateDto dto) {
        CustomerRequestDto requestDto = customerConverter.toCustomerRequestDto(dto);
        return ResponseHandler.success(customerService.create(requestDto));
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<ApiResponse<CustomerResponseDto>> getById(@PathVariable("id") Integer id) {
        return ResponseHandler.success(customerService.getById(id));
    }

    @GetMapping("/search")
    @Override
    public ResponseEntity<ApiResponse<PageResponse<CustomerResponseDto>>> search(@Valid @ModelAttribute PageRequest pageRequest) {
        return ResponseHandler.success(customerService.search(pageRequest));
    }

    @Override
    @PostMapping("/update/{id}")
    public ResponseEntity<ApiResponse<Integer>> update(@PathVariable("id") Integer id, @Valid @RequestBody CustomerUpdateDto dto) {
        CustomerRequestDto requestDto = customerConverter.toCustomerRequestDto(dto);
        return ResponseHandler.success(customerService.update(id, requestDto));
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Integer>> delete(@PathVariable("id") Integer id) {
        return ResponseHandler.success(customerService.delete(id));
    }
}
