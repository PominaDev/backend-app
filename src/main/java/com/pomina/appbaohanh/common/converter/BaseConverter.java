package com.pomina.appbaohanh.common.converter;

import java.util.List;

/**
 * Base Converter
 * @param <REQ> Request DTO
 * @param <RES> Response DTO
 * @param <E> Entity
 */
public interface BaseConverter<REQ, RES, E> {

    E toEntity(REQ requestDto);

    RES toResponse(E entity);

    List<E> toEntityList(List<REQ> requestDtoList);

    List<RES> toResponseList(List<E> entityList);
}
