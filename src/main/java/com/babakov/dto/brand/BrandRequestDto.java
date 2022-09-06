package com.babakov.dto.brand;

import com.babakov.dto.RequestDto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class BrandRequestDto extends RequestDto {

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
