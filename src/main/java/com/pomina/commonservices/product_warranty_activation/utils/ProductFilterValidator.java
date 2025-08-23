package com.pomina.commonservices.product_warranty_activation.utils;

import com.pomina.commonservices.product_warranty_activation.dto.custom_mapper.ProductFilter;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Set;

public class ProductFilterValidator {

    private ProductFilterValidator() {}

    // Những field cho phép truyền nhiều value
    private static final Set<String> MULTIPLE_ALLOWED = Set.of(
            "mauSac", "doMa", "macThep"
    );

    public static void validate(ProductFilter filter) {
        if (filter == null) return;

        for (Field field : ProductFilter.class.getDeclaredFields()) {
            field.setAccessible(true);
            try {
                Object value = field.get(filter);
                if (value instanceof List<?>) {
                    List<?> listValue = (List<?>) value;

                    if (listValue != null && listValue.size() > 1) {
                        if (!MULTIPLE_ALLOWED.contains(field.getName())) {
                            throw new IllegalArgumentException(
                                    "Field '" + field.getName() + "' chỉ được phép có 1 giá trị"
                            );
                        }
                    }
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Validate filter lỗi", e);
            }
        }
    }
}
