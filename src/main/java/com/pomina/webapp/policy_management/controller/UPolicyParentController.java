package com.pomina.webapp.policy_management.controller;

import com.pomina.common.constant.ApiConstants;
import com.pomina.common.controller.BaseController;
import com.pomina.common.handler.ApiResponse;
import com.pomina.common.handler.ResponseHandler;
import com.pomina.common.model.PageRequest;
import com.pomina.common.model.PageResponse;
import com.pomina.webapp.policy_management.converter.UPolicyParentConverter;
import com.pomina.webapp.policy_management.dto.request.UPolicyParentCreateDto;
import com.pomina.webapp.policy_management.dto.request.UPolicyParentUpdateDto;
import com.pomina.webapp.policy_management.dto.response.UPolicyParentResponseDto;
import com.pomina.webapp.policy_management.service.UPolicyParentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiConstants.ApiUPolicyParent.BASE)
@RequiredArgsConstructor
public class UPolicyParentController extends BaseController<UPolicyParentCreateDto, UPolicyParentUpdateDto, UPolicyParentResponseDto, Integer> {
    private final UPolicyParentService uPolicyParentService;
    private final UPolicyParentConverter uPolicyParentConverter;


    @Override
    @PostMapping(ApiConstants.ApiUPolicyParent.CREATE)
    public ResponseEntity<ApiResponse<Integer>> create(@RequestBody UPolicyParentCreateDto createDto) {
        var requestDto = uPolicyParentConverter.toUPolicyParentRequestDto(createDto);
        return ResponseHandler.success(uPolicyParentService.create(requestDto));
    }

    @PutMapping(ApiConstants.ApiUPolicyParent.CREATE_LIST)
    public ResponseEntity<ApiResponse<Integer>> createListUPolicyParent(@RequestBody List<UPolicyParentCreateDto> createDtoList) {
        var requestDtoList = uPolicyParentConverter.fromCreateListToUPolicyParentRequestDtoList(createDtoList);
        return ResponseHandler.success(uPolicyParentService.createListUPolicyParent(requestDtoList));
    }


    @GetMapping(ApiConstants.ApiUPolicyParent.GET_ALL)
    public ResponseEntity<ApiResponse<List<UPolicyParentResponseDto>>> getByAll() {
        return ResponseHandler.success(uPolicyParentService.getAllUPolicyParent());
    }

    @Override
    @GetMapping(ApiConstants.ApiUPolicyParent.GET_BY_ID)
    public ResponseEntity<ApiResponse<UPolicyParentResponseDto>> getById(@PathVariable("id") Integer id) {
        return ResponseHandler.success(uPolicyParentService.getById(id));
    }

    @GetMapping(ApiConstants.ApiUPolicyParent.GET_BY_CODE)
    public ResponseEntity<ApiResponse<List<UPolicyParentResponseDto>>> getByCode(@PathVariable("code") String code) {
        return ResponseHandler.success(uPolicyParentService.getAllUPolicyParentByCode(code));
    }

    @Override
    public ResponseEntity<ApiResponse<PageResponse<UPolicyParentResponseDto>>> search(PageRequest pageRequest) {
        return null;
    }

    @Override
    @PutMapping(ApiConstants.ApiUPolicyParent.UPDATE)
    public ResponseEntity<ApiResponse<Integer>> update(@PathVariable("id")Integer id, @RequestBody UPolicyParentUpdateDto updateDto) {
        var requestDto = uPolicyParentConverter.toUPolicyParentRequestDto(updateDto);
        return ResponseHandler.success(uPolicyParentService.update(id, requestDto));
    }

    @PutMapping(ApiConstants.ApiUPolicyParent.UPDATE_LIST)
    public ResponseEntity<ApiResponse<Integer>> updateListUPolicyParent(@RequestBody List<UPolicyParentUpdateDto> updateDtoList) {
        var requestDtoList = uPolicyParentConverter.fromUpdateListToUPolicyParentDtoList(updateDtoList);
        return ResponseHandler.success(uPolicyParentService.updateListUPolicyParent(requestDtoList));
    }

    @Override
    @DeleteMapping(ApiConstants.ApiUPolicyParent.DELETE)
    public ResponseEntity<ApiResponse<Integer>> delete(Integer id) {
        return ResponseHandler.success(uPolicyParentService.delete(id));
    }

    @DeleteMapping(ApiConstants.ApiUPolicyParent.DELETE_LIST)
    public ResponseEntity<ApiResponse<Integer>> solftDeleteListUPolicyParent(@RequestBody List<Integer> listId) {
        return ResponseHandler.success(uPolicyParentService.softDeleteList(listId));
    }

}
