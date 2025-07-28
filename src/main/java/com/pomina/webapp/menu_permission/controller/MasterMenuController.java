package com.pomina.webapp.menu_permission.controller;

import com.pomina.common.constant.ApiConstants;
import com.pomina.common.controller.BaseController;
import com.pomina.common.handler.ApiResponse;
import com.pomina.common.handler.ResponseHandler;
import com.pomina.common.model.PageRequest;
import com.pomina.common.model.PageResponse;
import com.pomina.webapp.menu_permission.converter.MasterMenuConverter;
import com.pomina.webapp.menu_permission.dto.request.MasterMenuCreateDto;
import com.pomina.webapp.menu_permission.dto.request.MasterMenuRequestDto;
import com.pomina.webapp.menu_permission.dto.request.MasterMenuUpdateDto;
import com.pomina.webapp.menu_permission.dto.response.MasterMenuResponseDto;
import com.pomina.webapp.menu_permission.service.MasterMenuService;
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

import java.util.List;

@RequestMapping(ApiConstants.ApiMasterMenu.BASE)
@RequiredArgsConstructor
public class MasterMenuController extends BaseController<MasterMenuCreateDto, MasterMenuUpdateDto, MasterMenuResponseDto, Integer> {

    // Dependency injection
    private final MasterMenuConverter masterMenuConverter;
    private final MasterMenuService masterMenuService;

    @PostMapping(ApiConstants.ApiMasterMenu.CREATE)
    @Override
    public ResponseEntity<ApiResponse<Integer>> create(MasterMenuCreateDto dto) {
        MasterMenuRequestDto requestDto = masterMenuConverter.toMasterMenuRequestDto(dto);
        return ResponseHandler.success(masterMenuService.create(requestDto));
    }

    /* API create a MasterMenu List
     * - List MenuCreate has: 1 parent and children (at least 1 child
     * - Response: Inserted rows
    */
    @PostMapping(ApiConstants.ApiMasterMenu.CREATE_LIST)
    public ResponseEntity<ApiResponse<Integer>> createListMenu(@RequestBody List<MasterMenuCreateDto> createDtoList) {
        //convert List<CreateDTO> to List<RequestDTO>
        List<MasterMenuRequestDto> requestDtoList = masterMenuConverter.toMasterMenuRequestDtoList(createDtoList);
        return ResponseHandler.success(masterMenuService.createListMasterMenu(requestDtoList));
    }

    @Override
    @GetMapping(ApiConstants.ApiMasterMenu.GET_BY_ID)
    public ResponseEntity<ApiResponse<MasterMenuResponseDto>> getById(@PathVariable("id") Integer id) {
        return ResponseHandler.success(masterMenuService.getById(id));
    }

    @GetMapping(ApiConstants.ApiMasterMenu.GET_ALL)
    public ResponseEntity<ApiResponse<List<MasterMenuResponseDto>>> getAll() {
        return ResponseHandler.success(masterMenuService.getAll());
    }

    @Override
    @GetMapping(ApiConstants.ApiMasterMenu.SEARCH)
    public ResponseEntity<ApiResponse<PageResponse<MasterMenuResponseDto>>> search( @Valid @ModelAttribute PageRequest pageRequest) {
        return ResponseHandler.success(masterMenuService.search(pageRequest));
    }

    @Override
    @PostMapping(ApiConstants.ApiMasterMenu.UPDATE)
    public ResponseEntity<ApiResponse<Integer>> update(@PathVariable("id") Integer id, @RequestBody MasterMenuUpdateDto dto) {
        MasterMenuRequestDto requestDto = masterMenuConverter.toMasterMenuRequestDto(dto);
        return ResponseHandler.success(masterMenuService.update(id, requestDto));
    }

    @PostMapping(ApiConstants.ApiMasterMenu.UPDATE_LIST)
    public ResponseEntity<ApiResponse<Integer>> updateListMasterMenu(@RequestBody List<MasterMenuUpdateDto> updateDtoList) {
        List<MasterMenuRequestDto> requestDtoList = masterMenuConverter.fromUpdateListToMasterRequestDtoList(updateDtoList);
        return ResponseHandler.success(masterMenuService.updateListMasterMenu(requestDtoList));
    }

    @Override
    @DeleteMapping(ApiConstants.ApiMasterMenu.DELETE)
    public ResponseEntity<ApiResponse<Integer>> delete(@PathVariable("id") Integer id) {
        return ResponseHandler.success(masterMenuService.delete(id));
    }
}
