package com.pomina.app.common.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class PageRequest {

    @Min(1)
    private Integer page;

    @Min(10)
    private Integer size;

    private String sort;

    @Pattern(regexp = "asc|desc", flags = Pattern.Flag.CASE_INSENSITIVE)
    private String direction;

    private static final Set<String> ALLOWED_SORT_FIELDS = Set.of(
            "id", "created_at"
    );

    public int getPage() {
        return page != null ? page : 1;
    }

    public int getSize() {
        return size != null ? size : 10;
    }

    public int getOffset() {
        return (getPage() - 1) * getSize();
    }

    public String getSort() {
        if (sort == null || !ALLOWED_SORT_FIELDS.contains(sort)) {
            return "created_at";
        }
        return sort;
    }

    public String getDirection() {
        if (direction == null) return "DESC";
        return direction.equalsIgnoreCase("asc") ? "ASC" : "DESC";
    }

    public String getOrderByClause() {
        return getSort() + " " + getDirection();
    }
}
