package com.pomina.common.controller;

import com.pomina.common.handler.ApiResponse;
import com.pomina.common.model.PageRequest;
import com.pomina.common.model.PageResponse;
import org.springframework.http.ResponseEntity;

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
