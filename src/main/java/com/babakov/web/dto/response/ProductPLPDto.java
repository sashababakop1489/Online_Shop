package com.babakov.web.dto.response;
import com.babakov.persistence.entity.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;
@Getter
@Setter
public class ProductPLPDto extends PLPDto{

    private Long id;
    private Boolean visible;
    private String productName;
    private String imageUrl;
    private Integer price;
    private Brand brand;

    public ProductPLPDto(Product product){
        this.id = product.getId();
        this.productName = product.getProductName();
        this.visible = product.getVisible();
        this.imageUrl = product.getImageUrl();
        this.price = product.getPrice();
        if (product.getBrand() != null) {
            this.brand = new Brand(
                    product.getBrand().getId(),
                    product.getBrand().getBrandName());
        }
    }

    private static final class Brand {

        private final Long id;
        private final String brandName;

        private Brand(Long id, String brandName) {
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
            var that = (Brand) obj;
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
}
