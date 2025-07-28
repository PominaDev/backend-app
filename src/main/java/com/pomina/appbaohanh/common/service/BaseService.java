package com.pomina.appbaohanh.common.service;

import com.pomina.appbaohanh.common.model.PageRequest;
import com.pomina.appbaohanh.common.model.PageResponse;

/**
 * Base Service
 * @param <REQ> Request DTO
 * @param <RES> Response DTO
 * @param <I> Data type ID
 */
public interface BaseService<REQ, RES, I> {
    int create(REQ dto);

    int update(I id, REQ dto);

    RES getById(I id);

    PageResponse<RES> search(PageRequest pageRequest);

    int delete(I id);
}
