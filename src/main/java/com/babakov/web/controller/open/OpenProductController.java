package com.babakov.web.controller.open;

import com.babakov.facade.PLPFacade;
import com.babakov.facade.ProductFacade;
import com.babakov.persistence.repository.ProductRepository;
import com.babakov.web.dto.response.ProductResponseDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Controller
@RequestMapping("/products")
public class OpenProductController {

    private final ProductRepository productRepository;
    private final PLPFacade plpFacade;
    private final ProductFacade productFacade;

    public OpenProductController(ProductRepository productRepository, PLPFacade plpFacade, ProductFacade productFacade) {
        this.productRepository = productRepository;
        this.plpFacade = plpFacade;
        this.productFacade = productFacade;
    }

    @GetMapping
    private String allProducts(Model model, WebRequest webRequest) {
        model.addAttribute("productList", plpFacade.search(webRequest));
        return "pages/open/plp";
    }

//    @GetMapping("/{id}")
//    public String findAll(Model model, @PathVariable Long id) {
//        List<ProductResponseDto> products = productFacade.findAllByBrandId(id);
//        model.addAttribute("products", products);
//        return "pages/open/product_all";
//    }

    @PostMapping("/search")
    private String allProductsSearch(
            RedirectAttributes redirectAttributes, @RequestParam String productSearch) {
        redirectAttributes.addAttribute("productSearch", productSearch);
        return "redirect:/products";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable @NotBlank(message = "id can not be empty") String id, Model model) {
        try {
            Long bookId = Long.parseLong(id);
            ProductResponseDto dto = productFacade.findById(bookId);
            model.addAttribute("product", dto);
            return "pages/open/product_details";
        } catch (NumberFormatException e) {
            throw new NumberFormatException("incorrect value id");
        }
    }
}
