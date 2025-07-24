package com.pomina.erpapp.appbaohanh.web.menu_permission.service.impl;

import com.pomina.erpapp.appbaohanh.web.menu_permission.dto.response.MenuPermissionResponseDto;
import com.pomina.erpapp.appbaohanh.web.menu_permission.dto.response.UserMenuPermissionResponseDto;
import com.pomina.erpapp.appbaohanh.web.menu_permission.entity.MasterPermission;
import com.pomina.erpapp.appbaohanh.web.menu_permission.mapper.MasterPermissionMapper;
import com.pomina.erpapp.appbaohanh.web.menu_permission.service.MasterPermissionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class MasterPermissionServiceImpl implements MasterPermissionService {

    private final MasterPermissionMapper masterPermissionMapper;

    @Override
    public UserMenuPermissionResponseDto getUserMenuPermissions(Integer userId) {
        log.info("Getting menu permissions for user ID: {}", userId);

        List<MasterPermission> permissions = masterPermissionMapper.findPermissionsWithMenuByUserId(userId);
        log.info("Found {} permissions for user ID: {}", permissions.size(), userId);

        if (permissions.isEmpty()) {
            log.warn("No permissions found for user ID: {}", userId);
            return new UserMenuPermissionResponseDto("", new ArrayList<>());
        }

        // Debug first permission record
        MasterPermission firstPermission = permissions.get(0);
        log.info("First permission - Menu ID: {}, Menu Name: {}, User Name: {}",
                firstPermission.getMasterMenuId(),
                firstPermission.getMasterMenuName(),
                firstPermission.getFullName());

        // Get user name from first permission record
        String userName = firstPermission.getFullName();

        // Convert to DTO and build tree structure
        List<MenuPermissionResponseDto> menuTree = buildMenuTree(permissions);
        log.info("Built menu tree with {} root menus", menuTree.size());

        return new UserMenuPermissionResponseDto(userName, menuTree);
    }

    @Override
    public List<MasterPermission> getDebugPermissions(Integer userId) {
        log.info("DEBUG: Getting raw permissions for user ID: {}", userId);
        return masterPermissionMapper.findPermissionsWithMenuByUserId(userId);
    }

    private List<MenuPermissionResponseDto> buildMenuTree(List<MasterPermission> permissions) {
        // Convert entities to DTOs
        List<MenuPermissionResponseDto> allMenus = permissions.stream()
                .map(this::convertToMenuDto)
                .collect(Collectors.toList());

        // Group menus by parent ID
        Map<String, List<MenuPermissionResponseDto>> menusByParent = allMenus.stream()
                .collect(
                        Collectors.groupingBy(menu -> menu.getParentId() != null ? menu.getParentId().toString() : ""));

        // Build tree starting from root menus (parentId is null or empty)
        List<MenuPermissionResponseDto> rootMenus = menusByParent.getOrDefault("", new ArrayList<>());

        // Recursively set children
        setChildren(rootMenus, menusByParent);

        // Sort by order index
        rootMenus.sort((a, b) -> {
            if (a.getOrderIndex() == null && b.getOrderIndex() == null)
                return 0;
            if (a.getOrderIndex() == null)
                return 1;
            if (b.getOrderIndex() == null)
                return -1;
            return a.getOrderIndex().compareTo(b.getOrderIndex());
        });

        return rootMenus;
    }

    private void setChildren(List<MenuPermissionResponseDto> parentMenus,
            Map<String, List<MenuPermissionResponseDto>> menusByParent) {
        for (MenuPermissionResponseDto parent : parentMenus) {
            List<MenuPermissionResponseDto> children = menusByParent.getOrDefault(parent.getId(), new ArrayList<>());

            // Sort children by order index
            children.sort((a, b) -> {
                if (a.getOrderIndex() == null && b.getOrderIndex() == null)
                    return 0;
                if (a.getOrderIndex() == null)
                    return 1;
                if (b.getOrderIndex() == null)
                    return -1;
                return a.getOrderIndex().compareTo(b.getOrderIndex());
            });

            parent.setChildren(children);

            // Recursively set children for nested levels
            if (!children.isEmpty()) {
                setChildren(children, menusByParent);
            }
        }
    }

    private MenuPermissionResponseDto convertToMenuDto(MasterPermission permission) {
        MenuPermissionResponseDto dto = new MenuPermissionResponseDto();

        // Generate ID from menu ID (you can customize this format)
        dto.setId("menu_" + permission.getMasterMenuId());
        dto.setName(permission.getMasterMenuName());
        dto.setPath(permission.getPath());
        dto.setIcon(permission.getIcon());
        dto.setParentId(permission.getParentId() != null ? "menu_" + permission.getParentId() : "");
        dto.setOrderIndex(permission.getOrderIndex());
        dto.setChildren(new ArrayList<>());

        // Set permissions
        dto.setIsFull(permission.getIsFull());
        dto.setIsInsert(permission.getIsInsert());
        dto.setIsDelete(permission.getIsDelete());
        dto.setIsUpdate(permission.getIsUpdate());
        dto.setIsRead(permission.getIsRead());
        dto.setIsPrint(permission.getIsPrint());
        dto.setIsExport(permission.getIsExport());

        return dto;
    }
}
