package com.pomina.erpapp.appbaohanh.web.menu_permission.controller;

import com.pomina.erpapp.appbaohanh.common.constant.ApiConstants;
import com.pomina.erpapp.appbaohanh.common.handler.ApiResponse;
import com.pomina.erpapp.appbaohanh.common.handler.ResponseHandler;
import com.pomina.erpapp.appbaohanh.common.model.PageRequest;
import com.pomina.erpapp.appbaohanh.common.model.PageResponse;
import com.pomina.erpapp.appbaohanh.web.menu_permission.converter.MasterMenuConverter;
import com.pomina.erpapp.appbaohanh.web.menu_permission.dto.request.MasterMenuCreateDto;
import com.pomina.erpapp.appbaohanh.web.menu_permission.dto.request.MasterMenuRequestDto;
import com.pomina.erpapp.appbaohanh.web.menu_permission.dto.request.MasterMenuUpdateDto;
import com.pomina.erpapp.appbaohanh.web.menu_permission.dto.response.MasterMenuResponseDto;
import com.pomina.erpapp.appbaohanh.web.menu_permission.service.MasterMenuService;
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

import java.util.List;

@RestController
@RequestMapping(ApiConstants.ApiMasterMenu.BASE)
@RequiredArgsConstructor
public class MasterMenuController {

    // Dependency injection
    private final MasterMenuConverter masterMenuConverter;
    private final MasterMenuService masterMenuService;

    @PostMapping(ApiConstants.ApiMasterMenu.CREATE)
    public ResponseEntity<ApiResponse<Integer>> createMenuList(
            @RequestBody List<@Valid MasterMenuCreateDto> dtoList) {
        List<MasterMenuRequestDto> requestDto = masterMenuConverter.toRequestDtoListFromCreate(dtoList);
        return ResponseHandler.success(masterMenuService.create(requestDto));
    }

    @GetMapping(ApiConstants.ApiMasterMenu.GET_BY_ID)
    public ResponseEntity<ApiResponse<MasterMenuResponseDto>> getById(@PathVariable("id") Integer id) {
        return ResponseHandler.success(masterMenuService.getById(id));
    }

    @GetMapping(ApiConstants.ApiMasterMenu.GET_ALL)
    public ResponseEntity<ApiResponse<List<MasterMenuResponseDto>>> getAll() {
        return ResponseHandler.success(masterMenuService.getAll());
    }

    @PostMapping(ApiConstants.ApiMasterMenu.UPDATE)
    public ResponseEntity<ApiResponse<Integer>> update(
            @RequestBody List<@Valid MasterMenuUpdateDto> dtoList) {
        List<MasterMenuRequestDto> requestDto = masterMenuConverter.toRequestDtoListFromUpdate(dtoList);
        return ResponseHandler.success(masterMenuService.update(requestDto));
    }

    @DeleteMapping(ApiConstants.ApiMasterMenu.DELETE)
    public ResponseEntity<ApiResponse<Integer>> deleteByListId(@RequestBody List<Integer> list) {
        return ResponseHandler.success(masterMenuService.delete(list));
    }

    @GetMapping(ApiConstants.ApiMasterMenu.SEARCH)
    public ResponseEntity<ApiResponse<PageResponse<MasterMenuResponseDto>>> search(@ModelAttribute PageRequest pageRequest) {
        return ResponseHandler.success(masterMenuService.search(pageRequest));
    }








}
