package com.pomina.erpapp.appbaohanh.web.menu_permission.controller;

import com.pomina.erpapp.appbaohanh.common.constant.ApiConstants;
import com.pomina.erpapp.appbaohanh.common.controller.BaseController;
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
public class MasterMenuController extends BaseController<MasterMenuCreateDto, MasterMenuUpdateDto, MasterMenuResponseDto, Integer> {

    // Dependency injection
    private final MasterMenuConverter masterMenuConverter;
    private final MasterMenuService masterMenuService;

    @Override
    @PostMapping(ApiConstants.ApiMasterMenu.CREATE)
    public ResponseEntity<ApiResponse<Integer>> create(@Valid @RequestBody MasterMenuCreateDto dto) {
        MasterMenuRequestDto requestDto = masterMenuConverter.toMasterMenuRequestDto(dto);
        return ResponseHandler.success(masterMenuService.create(requestDto));
    }

    @Override
    @GetMapping(ApiConstants.ApiMasterMenu.GET_BY_ID)
    public ResponseEntity<ApiResponse<MasterMenuResponseDto>> getById(@PathVariable("id") Integer id) {
        return ResponseHandler.success(masterMenuService.getById(id));
    }

    @Override
    @GetMapping(ApiConstants.ApiMasterMenu.SEARCH)
    public ResponseEntity<ApiResponse<PageResponse<MasterMenuResponseDto>>> search( @Valid @ModelAttribute PageRequest pageRequest) {
        return ResponseHandler.success(masterMenuService.search(pageRequest));
    }

    @Override
    @PostMapping(ApiConstants.ApiMasterMenu.UPDATE)
    public ResponseEntity<ApiResponse<Integer>> update(@PathVariable("id") Integer id, @Valid @RequestBody MasterMenuUpdateDto dto) {
        MasterMenuRequestDto requestDto = masterMenuConverter.toMasterMenuRequestDto(dto);
        return ResponseHandler.success(masterMenuService.update(id, requestDto));
    }

    @Override
    @DeleteMapping(ApiConstants.ApiMasterMenu.DELETE)
    public ResponseEntity<ApiResponse<Integer>> delete(@PathVariable("id") Integer id) {
        return ResponseHandler.success(masterMenuService.delete(id));
    }

    @GetMapping(ApiConstants.ApiMasterMenu.GET_ALL)
    public ResponseEntity<ApiResponse<List<MasterMenuResponseDto>>> getAll() {
        return ResponseHandler.success(masterMenuService.getAll());
    }

}
