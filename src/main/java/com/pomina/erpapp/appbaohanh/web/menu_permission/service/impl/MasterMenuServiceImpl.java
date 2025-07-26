package com.pomina.erpapp.appbaohanh.web.menu_permission.service.impl;

import com.pomina.erpapp.appbaohanh.common.model.PageRequest;
import com.pomina.erpapp.appbaohanh.common.model.PageResponse;
import com.pomina.erpapp.appbaohanh.web.menu_permission.converter.MasterMenuConverter;
import com.pomina.erpapp.appbaohanh.web.menu_permission.dto.request.MasterMenuRequestDto;
import com.pomina.erpapp.appbaohanh.web.menu_permission.dto.response.MasterMenuResponseDto;
import com.pomina.erpapp.appbaohanh.web.menu_permission.entity.MasterMenu;
import com.pomina.erpapp.appbaohanh.web.menu_permission.mapper.MasterMenuMapper;
import com.pomina.erpapp.appbaohanh.web.menu_permission.service.MasterMenuService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MasterMenuServiceImpl implements MasterMenuService {
    // Dependency injection
    private final MasterMenuMapper masterMenuMapper;
    private final MasterMenuConverter masterMenuConverter;

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

    @Override
    public int delete(List<Integer> list) {
        if (list == null || list.isEmpty()) {
            return 0;
        }
        return masterMenuMapper.softDelete(list);
    }

    @Override
    public int create(List<MasterMenuRequestDto> requestDto) {
        List<MasterMenu> masterMenuList = masterMenuConverter.toEntityList(requestDto);
        if (masterMenuList == null || masterMenuList.isEmpty()) {
            return 0;
        }
        return masterMenuMapper.insert(masterMenuList);
    }

    @Override
    public int update(List<MasterMenuRequestDto> requestDto) {
        List<MasterMenu> masterMenuList = masterMenuConverter.toEntityList(requestDto);
        if (masterMenuList == null || masterMenuList.isEmpty()) {
            return 0;
        }
        return masterMenuMapper.update(masterMenuList);
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
}
