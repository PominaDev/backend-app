package com.pomina.erpapp.appbaohanh.common.controller;

import com.pomina.erpapp.appbaohanh.common.handler.ApiResponse;
import com.pomina.erpapp.appbaohanh.common.model.PageRequest;
import com.pomina.erpapp.appbaohanh.common.model.PageResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * Base Controller
 * @param <CREATE> Create DTO
 * @param <UPDATE> Update DTO
 * @param <RES> Response DTO
 * @param <I> Data type ID
 */
public abstract class BaseController<CREATE, UPDATE, RES, I> {

    public abstract ResponseEntity<ApiResponse<Integer>> create(CREATE dto);

    public abstract ResponseEntity<ApiResponse<RES>> getById(I id);

    public abstract ResponseEntity<ApiResponse<PageResponse<RES>>> search(PageRequest pageRequest);

    public abstract ResponseEntity<ApiResponse<Integer>> update(I id, UPDATE dto);

    public abstract ResponseEntity<ApiResponse<Integer>> delete(I id);
}
