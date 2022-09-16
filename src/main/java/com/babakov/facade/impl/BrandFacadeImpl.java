package com.babakov.facade.impl;

import com.babakov.persistence.datatable.DataTableRequest;
import com.babakov.persistence.datatable.DataTableResponse;
import com.babakov.persistence.entity.Brand;
import com.babakov.util.WebUtil;
import com.babakov.web.dto.request.BrandRequestDto;
import com.babakov.web.dto.response.BrandResponseDto;
import com.babakov.facade.BrandFacade;
import com.babakov.service.BrandService;
import com.babakov.web.dto.response.PageData;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BrandFacadeImpl implements BrandFacade {

    private final BrandService brandService;


    public BrandFacadeImpl(BrandService brandService) {
        this.brandService = brandService;
    }


    @Override
    public void create(BrandRequestDto brandRequestDto) {
        Brand brand = new Brand();
        brand.setBrandName(brandRequestDto.getBrandName());
        brandService.create(brand);
    }

    @Override
    public void update(BrandRequestDto brandRequestDto, Long id) {
        Optional<Brand> optionalBrand = brandService.findById(id);
        if (optionalBrand.isPresent()){
            Brand brand = optionalBrand.get();
            brand.setBrandName(brandRequestDto.getBrandName());
        }
    }

    @Override
    public void delete(Long id) {
        brandService.delete(id);
    }

    @Override
    public BrandResponseDto findById(Long id) {
        return new BrandResponseDto(brandService.findById(id).get());
    }

    @Override
    public PageData<BrandResponseDto> findAll(WebRequest request) {
//        DataTableRequest dataTableRequest = WebUtil.generateDataTableRequestByWebRequest(request);
//        DataTableResponse<Brand> tableResponse = brandService.findAll(dataTableRequest);
//        List<BrandResponseDto> brands = tableResponse.getItems().stream().
//                map(BrandResponseDto::new).
//                collect(Collectors.toList());
//
//        PageData<BrandResponseDto> pageData = (PageData<BrandResponseDto>) WebUtil.initPageData(tableResponse);
//        pageData.setItems(brands);
//        return pageData;
        PageAndSizeData pageAndSizeData = WebRequestUtil.generatePageAndSizeData(request);
        SortData sortData = WebRequestUtil.generateSortData(request);
        DataTableRequest dataTableRequest = Converter.pageAndSortDataToDtoReq(pageAndSizeData, sortData);
        DataTableResponse<Owner> owners = ownerService.findAll(dataTableRequest);
        List<OwnerDtoResponse> items = owners.getItems().stream().map(OwnerDtoResponse::new).collect(Collectors.toList());
        PageData<OwnerDtoResponse> pageData = Converter.dtoRespToPageAndSortData(items, pageAndSizeData, sortData);
        pageData.setItemsSize(owners.getItemsSize());
        pageData.initPaginationState(pageData.getCurrentPage());
        return pageData;
    }
}

