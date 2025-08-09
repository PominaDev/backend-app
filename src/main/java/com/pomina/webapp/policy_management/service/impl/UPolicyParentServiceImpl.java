package com.pomina.webapp.policy_management.service.impl;

import com.pomina.common.exception.AppException;
import com.pomina.common.exception.ErrorCode;
import com.pomina.common.model.PageRequest;
import com.pomina.common.model.PageResponse;
import com.pomina.common.utils.AuditUtil;
import com.pomina.webapp.policy_management.converter.UPolicyParentConverter;
import com.pomina.webapp.policy_management.dto.request.UPolicyParentRequestDto;
import com.pomina.webapp.policy_management.dto.response.UPolicyParentResponseDto;
import com.pomina.webapp.policy_management.entity.UPolicyParent;
import com.pomina.webapp.policy_management.mapper.UPolicyParentMapper;
import com.pomina.webapp.policy_management.service.UPolicyParentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UPolicyParentServiceImpl implements UPolicyParentService {

    // Dependency injection
    private final UPolicyParentMapper uPolicyParentMapper;
    private final UPolicyParentConverter uPolicyParentConverter;

    @Override
    public int create(UPolicyParentRequestDto dto) {
        try {
            var entity = uPolicyParentConverter.toEntity(dto);
            AuditUtil.insert(entity);
            return uPolicyParentMapper.insert(entity);
        } catch (Exception e) {
            throw new RuntimeException("Lỗi thêm chính sách", e);
        }
    }

    @Override
    public int update(Integer id, UPolicyParentRequestDto dto) {
        try {
            var entity = uPolicyParentConverter.toEntity(dto);
            if (!uPolicyParentMapper.existsById(id)){
                AuditUtil.insert(entity);
                return uPolicyParentMapper.insert(entity);
            }else{
                AuditUtil.update(entity);
                return uPolicyParentMapper.update(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException("Lỗi cập nhật chính sách", e);
        }
    }

    @Override
    public UPolicyParentResponseDto getById(Integer id) {
        var entity = uPolicyParentMapper.findById(id);
        if (!Objects.isNull(entity)) {
            return uPolicyParentConverter.toResponse(entity);
        } else {
            throw new AppException(ErrorCode.POLICY_PARENT_NOT_FOUND);
        }
    }

    @Override
    public PageResponse<UPolicyParentResponseDto> search(PageRequest pageRequest) {
        return null;
    }

    @Override
    public int delete(Integer id) {
        return 0;
    }

    @Override
    public List<UPolicyParentResponseDto> getAllUPolicyParent() {
        var listEntity = uPolicyParentMapper.findAll();
        if (listEntity != null && !listEntity.isEmpty()) {
            return uPolicyParentConverter.toResponseList(listEntity);
        } else {
            throw new RuntimeException("Danh sách chính sách rỗng");
        }
    }

    @Override
    public List<UPolicyParentResponseDto> getAllUPolicyParentByCode(String code) {
        var listEntity = uPolicyParentMapper.findByPolicyParentCode(code);
        if (listEntity != null && !listEntity.isEmpty()) {
            return uPolicyParentConverter.toResponseList(listEntity);
        } else {
            throw new RuntimeException("Danh sách chính sách rỗng");
        }
    }

    @Override
    public Integer createListUPolicyParent(List<UPolicyParentRequestDto> dtoList) {
        try {
            var listEntity = uPolicyParentConverter.toEntityList(dtoList);
            listEntity.forEach(uPolicyParent -> {
                AuditUtil.insert(uPolicyParent);
            });
            return uPolicyParentMapper.insertList(listEntity);
        } catch (Exception e) {
            throw new RuntimeException("Lỗi thêm chính sách", e);
        }
    }

    @Override
    public Integer updateListUPolicyParent(List<UPolicyParentRequestDto> dtoList) {
        try {
            var listEntity = uPolicyParentConverter.toEntityList(dtoList);
            var updateList = new ArrayList<UPolicyParent>();
            var insertList = new ArrayList<UPolicyParent>();

            for (UPolicyParent entity : listEntity) {
                if (uPolicyParentMapper.existsById(entity.getPolicyParentId())) {
                    updateList.add(entity);
                } else {
                    insertList.add(entity);
                }
            }
            insertList.forEach(uPolicyParent -> {
                AuditUtil.insert(uPolicyParent);
            });
            updateList.forEach(uPolicyParent -> {
                AuditUtil.update(uPolicyParent);
            });

            Integer saveRows = 0;
            for (UPolicyParent entity : updateList) {
                saveRows += uPolicyParentMapper.update(entity);
            }

            return saveRows + uPolicyParentMapper.insertList(insertList);

        } catch (Exception e) {
            throw new RuntimeException("Lỗi cập nhật chính sách", e);
        }
    }

    @Override
    public Integer softDeleteList(List<Integer> idList) {
        try {
            var deleteList = new ArrayList<UPolicyParent>();
            for (Integer id : idList) {
                deleteList.add(uPolicyParentMapper.findById(id));
            }
            if (deleteList.isEmpty()) {
                throw new RuntimeException("Không tìm thấy các chính sách cần xóa");
            }
            deleteList.forEach(uPolicyParent -> {
                AuditUtil.softDelete(uPolicyParent, "");
            });
            return uPolicyParentMapper.softDeleteList(idList);
        } catch (Exception e) {
            throw new RuntimeException("Lỗi xóa chính sách", e);
        }
    }
}