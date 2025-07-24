package com.pomina.erpapp.appbaohanh.web.menu_permission.controller;

import com.pomina.erpapp.appbaohanh.web.menu_permission.dto.response.MenuPermissionApiResponseDto;
import com.pomina.erpapp.appbaohanh.web.menu_permission.dto.response.UserMenuPermissionResponseDto;
import com.pomina.erpapp.appbaohanh.web.menu_permission.mapper.MasterPermissionMapper;
import com.pomina.erpapp.appbaohanh.web.menu_permission.service.MasterPermissionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Field;

@Slf4j
@RestController
@RequestMapping("/api/permissions")
@RequiredArgsConstructor
public class MasterPermissionController {

  private final MasterPermissionService masterPermissionService;

  /**
   * DEBUG: Get raw permission data for testing
   * 
   * @param userId the user ID
   * @return raw permission list
   */
  @GetMapping("/users/{userId}/debug")
  public ResponseEntity<?> getDebugPermissions(@PathVariable Integer userId) {
    log.info("DEBUG: Getting raw permissions for user ID: {}", userId);

    try {
      Field permissions = masterPermissionService
          .getClass().getDeclaredField("masterPermissionMapper");
      permissions.setAccessible(true);
      var mapper = (MasterPermissionMapper) permissions
          .get(masterPermissionService);

      var rawData = mapper.findPermissionsWithMenuByUserId(userId);
      log.info("DEBUG: Found {} raw permissions", rawData.size());

      return ResponseEntity.ok(rawData);

    } catch (Exception e) {
      log.error("DEBUG: Error getting raw permissions", e);
      return ResponseEntity.internalServerError().body("Debug error: " + e.getMessage());
    }
  }

  /**
   * Get user menu permissions organized as tree structure
   * 
   * @param userId the user ID
   * @return API response with user menu permissions
   */
  @GetMapping("/users/{userId}/menus")
  public ResponseEntity<MenuPermissionApiResponseDto> getUserMenuPermissions(
      @PathVariable Integer userId) {

    log.info("Request to get menu permissions for user ID: {}", userId);

    try {
      UserMenuPermissionResponseDto userMenuPermissions = masterPermissionService.getUserMenuPermissions(userId);

      MenuPermissionApiResponseDto response = new MenuPermissionApiResponseDto(200, userMenuPermissions);

      return ResponseEntity.ok(response);

    } catch (Exception e) {
      log.error("Error getting menu permissions for user ID: {}", userId, e);

      MenuPermissionApiResponseDto errorResponse = new MenuPermissionApiResponseDto(500, null);

      return ResponseEntity.internalServerError().body(errorResponse);
    }
  }
}
