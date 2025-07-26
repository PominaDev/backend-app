package com.pomina.erpapp.appbaohanh.web.menu_permission.service.impl;

import com.pomina.erpapp.appbaohanh.common.model.PageRequest;
import com.pomina.erpapp.appbaohanh.common.model.PageResponse;
import com.pomina.erpapp.appbaohanh.web.menu_permission.converter.MasterMenuConverter;
import com.pomina.erpapp.appbaohanh.web.menu_permission.dto.request.MasterMenuRequestDto;
import com.pomina.erpapp.appbaohanh.web.menu_permission.dto.response.MasterMenuResponseDto;
import com.pomina.erpapp.appbaohanh.web.menu_permission.dto.response.MenuPermissionResponseDto;
import com.pomina.erpapp.appbaohanh.web.menu_permission.dto.response.MenuStructured;
import com.pomina.erpapp.appbaohanh.web.menu_permission.dto.response.PermissionResponseDto;
import com.pomina.erpapp.appbaohanh.web.menu_permission.entity.MasterMenu;
import com.pomina.erpapp.appbaohanh.web.menu_permission.entity.MenuPermission;
import com.pomina.erpapp.appbaohanh.web.menu_permission.mapper.MasterMenuMapper;
import com.pomina.erpapp.appbaohanh.web.menu_permission.mapper.MasterPermissionMapper;
import com.pomina.erpapp.appbaohanh.web.menu_permission.service.MasterMenuService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class MasterMenuServiceImpl implements MasterMenuService {
    // Dependency injection
    private final MasterMenuMapper masterMenuMapper;
    private final MasterMenuConverter masterMenuConverter;
    private final MasterPermissionMapper masterPermissionMapper;

    @Override
    public MasterMenuResponseDto getById(Integer id) {
        MasterMenu masterMenu = masterMenuMapper.findById(id);
        if (masterMenu != null) {
            return masterMenuConverter.toResponse(masterMenu);
        }
        return null;
    }

    @Override
    public PageResponse<MasterMenuResponseDto> search(PageRequest pageRequest) {

        List<MasterMenu> masterMenuList = masterMenuMapper.findAllPaged(pageRequest.getOffset(),
                pageRequest.getSize(),
                pageRequest);

        if (masterMenuList == null || masterMenuList.isEmpty()) {
            return PageResponse.empty(pageRequest.getPage(), pageRequest.getSize());
        }

        List<MasterMenuResponseDto> masterMenuResponse = masterMenuConverter.toResponseList(masterMenuList);

        int totalElements = masterMenuMapper.countAll();

        return PageResponse.createPaged(masterMenuResponse, pageRequest.getPage(), pageRequest.getSize(),
                totalElements);

    }

    @Override
    public List<MasterMenuResponseDto> getAll() {
        List<MasterMenu> masterMenuList = masterMenuMapper.findAll();
        return loadMenuStructure(masterMenuList);
    }

    private List<MasterMenuResponseDto> loadMenuStructure(List<MasterMenu> menus) {

        List<MasterMenu> parentMenus = new ArrayList<>();

        for (MasterMenu menu : menus) {
            if (menu.getIsParent()) {
                parentMenus.add(menu);
            }
        }

        parentMenus.sort((menu1, menu2) -> {
            if (menu1.getOrderIndex() == null && menu2.getOrderIndex() == null) return 0;
            if (menu1.getOrderIndex() == null) return 1;
            if (menu2.getOrderIndex() == null) return -1;
            return menu1.getOrderIndex().compareTo(menu2.getOrderIndex());
        });

        List<MasterMenuResponseDto> result = new ArrayList<>();
        for (MasterMenu parent : parentMenus) {
            MasterMenuResponseDto dto = convertToDto(parent, menus);
            result.add(dto);
        }

        return result;
    }

    private MasterMenuResponseDto convertToDto(MasterMenu menu, List<MasterMenu> allMenus) {

        MasterMenuResponseDto dto = masterMenuConverter.toResponse(menu);

        List<MasterMenu> children = findChildren(menu, allMenus);

        if (!children.isEmpty()) {
            List<MasterMenuResponseDto> childDtos = new ArrayList<>();
            for (MasterMenu child : children) {
                MasterMenuResponseDto childDto = convertToDto(child, allMenus);
                childDtos.add(childDto);
            }
            dto.setMenuItems(childDtos);
        }

        return dto;
    }

    private List<MasterMenu> findChildren(MasterMenu parentMenu, List<MasterMenu> allMenus) {
        List<MasterMenu> children = new ArrayList<>();
        for (MasterMenu menu : allMenus) {
            if (parentMenu.getMasterMenuId().equals(menu.getParentId()) &&
                    !menu.getIsParent()) {
                children.add(menu);
            }
        }
        children.sort((menu1, menu2) -> {
            if (menu1.getOrderIndex() == null && menu2.getOrderIndex() == null) return 0;
            if (menu1.getOrderIndex() == null) return 1;
            if (menu2.getOrderIndex() == null) return -1;
            return menu1.getOrderIndex().compareTo(menu2.getOrderIndex());
        });

        return children;
    }

    private List<MenuStructured> convertMenuPermissionEntityToMenuStructured(List<MenuPermission> listMenuPermission) {
        List<MenuStructured> finalMenu = new ArrayList<>();
        // Convert MenuPermissionE to MenuPermissionRes
        List<MenuPermissionResponseDto> listMenuPermissionResponseDtos = new ArrayList<>();
        for (MenuPermission menuPermission : listMenuPermission) {
            // Menu
            MenuPermissionResponseDto menuPermissionResponseDto = new MenuPermissionResponseDto();
            menuPermissionResponseDto.setMasterMenuId(menuPermission.getMasterMenu().getMasterMenuId());
            menuPermissionResponseDto.setMasterMenuName(menuPermission.getMasterMenu().getMasterMenuName());
            menuPermissionResponseDto.setPath(menuPermission.getMasterMenu().getPath());
            menuPermissionResponseDto.setIcon(menuPermission.getMasterMenu().getIcon());
            menuPermissionResponseDto.setIsParent(menuPermission.getMasterMenu().getIsParent());
            menuPermissionResponseDto.setOrderIndex(menuPermission.getMasterMenu().getOrderIndex());
            menuPermissionResponseDto.setParentId(menuPermission.getMasterMenu().getParentId());
            menuPermissionResponseDto.setIsDeleted(menuPermission.getMasterMenu().getIsDeleted());
            menuPermissionResponseDto.setNoted(menuPermission.getMasterMenu().getNoted());
            // Permission
            PermissionResponseDto permissionResponseDto = new PermissionResponseDto();
            permissionResponseDto.setMasterPermissionId(menuPermission.getMasterPermissionId());
            permissionResponseDto.setIsFull(menuPermission.getIsFull());
            permissionResponseDto.setMasterMenuId(menuPermission.getMasterMenuId());
            permissionResponseDto.setSysUserId(menuPermission.getSysUserId());
            permissionResponseDto.setIsInsert(menuPermission.getIsInsert());
            permissionResponseDto.setIsDelete(menuPermission.getIsDelete());
            permissionResponseDto.setIsUpdate(menuPermission.getIsUpdate());
            permissionResponseDto.setIsRead(menuPermission.getIsRead());
            permissionResponseDto.setIsPrint(menuPermission.getIsPrint());
            permissionResponseDto.setIsExport(menuPermission.getIsExport());
            menuPermissionResponseDto.setPermission(permissionResponseDto);
            listMenuPermissionResponseDtos.add(menuPermissionResponseDto);
        };
        // Structured Menu
        List<MenuStructured> listMenuStructured = new ArrayList<>();
        List<MenuPermissionResponseDto> menuPermissionResponseDtoParent = listMenuPermissionResponseDtos.stream()
                .filter(e -> e.getIsParent() == true)
                .collect(Collectors.toList());
        List<MenuPermissionResponseDto> menuPermissionResponseDtoChild = listMenuPermissionResponseDtos.stream()
                .filter(e -> e.getIsParent() == false)
                .collect(Collectors.toList());
        for (MenuPermissionResponseDto parent : menuPermissionResponseDtoParent) {
            MenuStructured item = new MenuStructured();
            item.setMasterMenuId(parent.getMasterMenuId());
            item.setMasterMenuName(parent.getMasterMenuName());
            item.setPath(parent.getPath());
            item.setIcon(parent.getIcon());
            item.setIsParent(parent.getIsParent());
            item.setOrderIndex(parent.getOrderIndex());
            item.setParentId(parent.getParentId());
            item.setIsDeleted(parent.getIsDeleted());
            item.setNoted(parent.getNoted());
            item.setPermission(parent.getPermission());
            List<MenuPermissionResponseDto> listItem = menuPermissionResponseDtoChild.stream()
                    .filter(e -> Objects.equals(e.getParentId(), parent.getMasterMenuId()))
                    .collect(Collectors.toList());
            item.setItemMenu(listItem);
            finalMenu.add(item);
        }
        return finalMenu;
    }
}
