package com.babakov.web.controller.admin;

import com.babakov.facade.ProductFacade;
import com.babakov.web.controller.AbstractController;

import com.babakov.web.dto.request.ProductRequestDto;
import com.babakov.web.dto.response.PageData;
import com.babakov.web.dto.response.ProductResponseDto;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;


@Validated
@Controller
@RequestMapping("/admin/products")
public class AdminProductController extends AbstractController {

    private final ProductFacade productFacade;

    public AdminProductController(ProductFacade productFacade) {
        this.productFacade = productFacade;
    }

    @GetMapping
    public String findAll(Model model, WebRequest request) {
        PageData<ProductResponseDto> response = productFacade.findAll(request);
        model.addAttribute("createUrl", "/admin/products/all");
        model.addAttribute("createNew", "/admin/products/new");
        model.addAttribute("cardHeader", "All Products");
        return "pages/admin/product/product_all";
    }

    @PostMapping("/all")
    public ModelAndView findAllRedirect(WebRequest request, ModelMap model) {
        return findAllRedirect(request, model, "products");
    }

    @GetMapping("/new")
    public String redirectToNewBookPage(Model model) {
        model.addAttribute("product", new ProductRequestDto());
        return "pages/admin/product/product_new";
    }

    @PostMapping("/create")
    public String createNewDepartment(RedirectAttributes attributes, @ModelAttribute("product") ProductRequestDto dto, @RequestParam("file") MultipartFile file) {
        productFacade.create(dto);
        return "redirect:/admin/products";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable @Min(value = 1, message = "id can not be zero") Long id) {
        productFacade.delete(id);
        return "redirect:/admin/books";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable @NotBlank(message = "id can not be empty") String id, Model model) {
        try {
            Long productId = Long.parseLong(id);
            ProductResponseDto dto = productFacade.findById(productId);
            model.addAttribute("book", dto);
            return "pages/admin/book/book_details";
        } catch (NumberFormatException e) {
            throw new NumberFormatException("incorrect value id");
        }
    }
}
