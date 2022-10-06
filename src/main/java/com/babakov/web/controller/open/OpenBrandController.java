package com.babakov.web.controller.open;

import com.babakov.facade.BrandFacade;
import com.babakov.facade.PLPFacade;
import com.babakov.persistence.repository.BrandRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

@Controller
@RequestMapping("/brands")
public class OpenBrandController {

    private final BrandRepository brandRepository;
    private final PLPFacade plpFacade;
    private final BrandFacade brandFacade;

    public OpenBrandController(BrandRepository brandRepository, PLPFacade plpFacade, BrandFacade brandFacade) {
        this.brandRepository = brandRepository;
        this.plpFacade = plpFacade;
        this.brandFacade = brandFacade;
    }

    @GetMapping
    private String allProducts(Model model, WebRequest webRequest) {
        model.addAttribute("brandList", brandRepository.findAll());
        return "pages/open/brand_plp";
    }
}
