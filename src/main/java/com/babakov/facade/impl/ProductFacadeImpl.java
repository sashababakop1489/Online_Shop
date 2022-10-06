package com.babakov.facade.impl;

import com.babakov.facade.ProductFacade;
import com.babakov.persistence.datatable.DataTableRequest;
import com.babakov.persistence.datatable.DataTableResponse;
import com.babakov.persistence.entity.Brand;
import com.babakov.persistence.entity.Product;
import com.babakov.service.BrandService;
import com.babakov.service.ProductService;
import com.babakov.util.WebUtil;
import com.babakov.web.dto.request.ProductRequestDto;
import com.babakov.web.dto.response.PageData;
import com.babakov.web.dto.response.ProductResponseDto;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductFacadeImpl implements ProductFacade {

    private final ProductService productService;
    private final BrandService brandService;

    public ProductFacadeImpl(ProductService productService, BrandService brandService) {
        this.productService = productService;
        this.brandService = brandService;
    }
    @SneakyThrows
    @Override
    public void create(ProductRequestDto productRequestDto) {
        Optional<Brand> optionalBrand = brandService.findById(productRequestDto.getBrandId());
        if (optionalBrand.isPresent()) {
            Product product = new Product();
            product.setProductName(productRequestDto.getProductName());
            product.setQuantity(product.getQuantity());
            product.setPrice(productRequestDto.getPrice());
            product.setColor(productRequestDto.getColor());
            product.setImageUrl(new String(productRequestDto.getProductImage().getBytes()));
            product.setSize(productRequestDto.getSize());
            product.setBrand(optionalBrand.get());
            productService.create(product);
        }
    }
    @SneakyThrows
    @Override
    public void update(ProductRequestDto productRequestDto, Long id) {
        Optional<Product> optionalProduct = productService.findById(id);
        if (optionalProduct.isPresent()){
            Product product = optionalProduct.get();
            product.setProductName(productRequestDto.getProductName());
            product.setQuantity(product.getQuantity());
            product.setPrice(productRequestDto.getPrice());
            product.setColor(productRequestDto.getColor());
            product.setImageUrl(new String(productRequestDto.getProductImage().getBytes()));
            product.setSize(productRequestDto.getSize());
            productService.update(product);
        }
    }

    @Override
    public void delete(Long id) {
        productService.delete(id);
    }

    @Override
    public ProductResponseDto findById(Long id) {
        return new ProductResponseDto(productService.findById(id).get());
    }

    @Override
    public PageData<ProductResponseDto> findAll(WebRequest request) {
        DataTableRequest dataTableRequest = WebUtil.generateDataTableRequestByWebRequest(request);
        DataTableResponse<Product> tableResponse = productService.findAll(dataTableRequest);
        List<ProductResponseDto> products = tableResponse.getItems().stream().
                map(ProductResponseDto::new).
                collect(Collectors.toList());

        PageData<ProductResponseDto> pageData = (PageData<ProductResponseDto>) WebUtil.initPageData(tableResponse);
        pageData.setItems(products);
        return pageData;
    }

    @Override
    public List<ProductResponseDto> findAllByBrandId(Long brandId) {
        return convertToDtoByEntity(productService.findAllByBrandId(brandId));
    }

    private List<ProductResponseDto> convertToDtoByEntity(List<Product> products) {
        return products.stream()
                .map(ProductResponseDto::new)
                .collect(Collectors.toList());
    }
}
