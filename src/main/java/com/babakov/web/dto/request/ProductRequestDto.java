package com.babakov.web.dto.request;

import com.babakov.web.dto.request.RequestDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
public class ProductRequestDto extends RequestDto {

    private String productName;
    private String description;
    private String color;
    private String size;
    private MultipartFile productImage;
    private Integer quantity;
    private Long brandId;
    private int price;

}
