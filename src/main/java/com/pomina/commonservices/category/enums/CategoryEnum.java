package com.pomina.commonservices.category.enums;

import com.pomina.commonservices.category.dto.custom_mapper.CategoryItem;
import com.pomina.commonservices.category.dto.response.CategoryResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.function.BiConsumer;

@Getter
@AllArgsConstructor
public enum CategoryEnum {
    LOAI_TON("loại tôn", CategoryResponseDto::setLoaiTon),
    LOAI_HANG("loại hàng", CategoryResponseDto::setLoaiHang),
    BE_MAT("bề mặt", CategoryResponseDto::setBeMat),
    DO_MA("độ mạ", CategoryResponseDto::setDoMa),
    DO_DAY("độ dày", CategoryResponseDto::setDoDay),
    MAU_SAC("màu sắc", CategoryResponseDto::setMauSac),
    MAC_THEP("mác thép", CategoryResponseDto::setMacThep),
    ;

    private final String nameGroup;

    private final BiConsumer<CategoryResponseDto, List<CategoryItem>> setter;

    public void apply(CategoryResponseDto dto, List<CategoryItem> items) {
        setter.accept(dto, items);
    }
}
