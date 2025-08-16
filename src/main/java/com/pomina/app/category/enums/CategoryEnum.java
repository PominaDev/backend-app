package com.pomina.app.category.enums;

import com.pomina.app.category.dto.custom_mapper.CategoryItem;
import com.pomina.app.category.dto.response.CategoryResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.function.BiConsumer;

@Getter
@AllArgsConstructor
public enum CategoryEnum {
    LOAI_TON("loại tôn", CategoryResponseDto::setLoaiTon),
    DO_MA("độ mạ", CategoryResponseDto::setDoMa),
    MAC_THEP("mác thép", CategoryResponseDto::setMacThep),
    MAU_SAC("màu sắc", CategoryResponseDto::setMauSac),
    LOAI_HANG("loại hàng", CategoryResponseDto::setLoaiHang),
    ;

    private final String nameGroup;

    private final BiConsumer<CategoryResponseDto, List<CategoryItem>> setter;

    public void apply(CategoryResponseDto dto, List<CategoryItem> items) {
        setter.accept(dto, items);
    }
}
