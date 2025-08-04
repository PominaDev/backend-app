package com.pomina.webapp.grant_approval.controller;

import com.pomina.common.constant.ApiConstants;
import com.pomina.common.controller.BaseController;
import com.pomina.common.handler.ApiResponse;
import com.pomina.common.handler.ResponseHandler;
import com.pomina.common.model.PageRequest;
import com.pomina.common.model.PageResponse;
import com.pomina.webapp.grant_approval.converter.MasterGroupUserConverter;
import com.pomina.webapp.grant_approval.dto.request.MasterGroupUserCreateDto;
import com.pomina.webapp.grant_approval.dto.request.MasterGroupUserRequestDto;
import com.pomina.webapp.grant_approval.dto.request.MasterGroupUserUpdateDto;
import com.pomina.webapp.grant_approval.dto.response.MasterGroupUserResponseDto;
import com.pomina.webapp.grant_approval.service.MasterGroupUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiConstants.ApiMasterGroupUser.BASE)
@RequiredArgsConstructor
public class MasterGroupUserController extends BaseController<MasterGroupUserCreateDto, MasterGroupUserUpdateDto, MasterGroupUserResponseDto, Integer> {

    // Dependency injection
    private final MasterGroupUserConverter masterGroupUserConverter;
    private final MasterGroupUserService masterGroupUserService;

    @GetMapping(ApiConstants.ApiMasterGroupUser.GET_BY_GROUP_USER_CODE)
    public ResponseEntity<ApiResponse<List<MasterGroupUserResponseDto>>> getByGroupUserCode(@PathVariable("groupUserCode") String groupUserCode) {
        return ResponseHandler.success(masterGroupUserService.getAllMasterGroupUserByGroupCode(groupUserCode));
    }

    @GetMapping(ApiConstants.ApiMasterGroupUser.GET_ALL)
    public ResponseEntity<ApiResponse<List<MasterGroupUserResponseDto>>> getAll() {
        return ResponseHandler.success(masterGroupUserService.getAllMasterGroupUser());
    }

    @Override
    @GetMapping(ApiConstants.ApiMasterGroupUser.GET_BY_ID)
    public ResponseEntity<ApiResponse<MasterGroupUserResponseDto>> getById(@PathVariable("id") Integer id) {
        return ResponseHandler.success(masterGroupUserService.getById(id));
    }

    @Override
    @GetMapping(ApiConstants.ApiMasterGroupUser.SEARCH)
    public ResponseEntity<ApiResponse<PageResponse<MasterGroupUserResponseDto>>> search( @Valid @ModelAttribute PageRequest pageRequest) {
        return ResponseHandler.success(masterGroupUserService.search(pageRequest));
    }

    @PostMapping(ApiConstants.ApiMasterGroupUser.CREATE)
    @Override
    public ResponseEntity<ApiResponse<Integer>> create(@RequestBody MasterGroupUserCreateDto dto) {
        MasterGroupUserRequestDto requestDto = masterGroupUserConverter.toMasterGroupUserRequestDto(dto);
        return ResponseHandler.success(masterGroupUserService.create(requestDto));
    }

    @Override
    @PostMapping(ApiConstants.ApiMasterGroupUser.UPDATE)
    public ResponseEntity<ApiResponse<Integer>> update(@PathVariable("id") Integer id, @RequestBody MasterGroupUserUpdateDto dto) {
        MasterGroupUserRequestDto requestDto = masterGroupUserConverter.toMasterGroupUserRequestDto(dto);
        return ResponseHandler.success(masterGroupUserService.update(id, requestDto));
    }

    @Override
    @DeleteMapping(ApiConstants.ApiMasterGroupUser.DELETE)
    public ResponseEntity<ApiResponse<Integer>> delete(@PathVariable("id") Integer id) {
        return ResponseHandler.success(masterGroupUserService.delete(id));
    }

    /* API create a MasterGroupUser List
     * - List GroupUserCreate has: 1 parent and children (at least 1 child)
     * - Response: Inserted rows
     */
    @PostMapping(ApiConstants.ApiMasterGroupUser.CREATE_LIST)
    public ResponseEntity<ApiResponse<Integer>> createListMenu(@RequestBody List<MasterGroupUserCreateDto> createDtoList) {
        List<MasterGroupUserRequestDto> requestDtoList = masterGroupUserConverter.toMasterGroupUserRequestDtoList(createDtoList);
        return ResponseHandler.success(masterGroupUserService.createListMasterGroupUser(requestDtoList));
    }

    /* API update a MasterGroupUser List
     * - List GroupUserCreate has: 1 parent and children (at least 1 child)
     * - Response: Inserted rows
     */
    @PostMapping(ApiConstants.ApiMasterGroupUser.UPDATE_LIST)
    public ResponseEntity<ApiResponse<Integer>> updateListMasterGroupUser(@RequestBody List<MasterGroupUserUpdateDto> updateDtoList) {
        List<MasterGroupUserRequestDto> requestDtoList = masterGroupUserConverter.fromUpdateListToMasterRequestDtoList(updateDtoList);
        return ResponseHandler.success(masterGroupUserService.updateListMasterGroupUser(requestDtoList));
    }

    @DeleteMapping(ApiConstants.ApiMasterGroupUser.DELETE_LIST)
    public ResponseEntity<ApiResponse<Integer>> deleteList(@RequestParam List<Integer> idList) {
        return null;
    }


}
