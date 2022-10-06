package com.babakov.web.dto.response;

import java.util.Objects;

public class BrandPLPDto extends PLPDto{

    private final Long id;
    private final String brandName;

    public BrandPLPDto(Long id, String brandName) {
        this.id = id;
        this.brandName = brandName;
    }

    public Long getId() {
        return id;
    }

    public String getBrandName() {
        return brandName;
    }

    public Long id() {
        return id;
    }

    public String brandName() {
        return brandName;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (BrandPLPDto) obj;
        return Objects.equals(this.id, that.id) &&
                Objects.equals(this.brandName, that.brandName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, brandName);
    }

    @Override
    public String toString() {
        return "Brand[" +
                "id=" + id + ", " +
                "name=" + brandName + ']';
    }
}
