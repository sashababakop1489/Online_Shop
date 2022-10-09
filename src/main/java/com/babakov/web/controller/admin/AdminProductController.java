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
import java.util.List;


@Validated
@Controller
@RequestMapping("/admin/products")
public class AdminProductController extends AbstractController {

    private final HeaderName[] columnNames = new HeaderName[] {
            new HeaderName("#", null, null),
            new HeaderName("image", "image", "image_url"),
            new HeaderName("name", "name", "productname"),
            new HeaderName("brand", "brand", "brandname"),
            new HeaderName("size", "size", "size"),
            new HeaderName("color", "color", "color"),
            new HeaderName("price", "price", "price")
    };

    private final ProductFacade productFacade;

    public AdminProductController(ProductFacade productFacade) {
        this.productFacade = productFacade;
    }

    @GetMapping
    public String findAll(Model model, WebRequest request) {
        PageData<ProductResponseDto> response = productFacade.findAll(request);
        initDataTable(response, columnNames, model);
        model.addAttribute("createUrl", "/admin/products/all");
        model.addAttribute("createNew", "/admin/products/new");
        model.addAttribute("cardHeader", "All Products");
        return "pages/admin/product/product_all";
    }
    @GetMapping("/brands/{id}")
    public String findAll(Model model, @PathVariable Long id) {
        List<ProductResponseDto> products = productFacade.findAllByBrandId(id);
        model.addAttribute("products", products);
        return "pages/admin/product/product_all_brand";
    }

    @PostMapping("/all")
    public ModelAndView findAllRedirect(WebRequest request, ModelMap model) {
        return findAllRedirect(request, model, "products");
    }

    @GetMapping("/new/{brandId}")
    public String redirectToNewBookPage(@PathVariable Long brandId, Model model) {
        ProductRequestDto dto = new ProductRequestDto();
        dto.setBrandId(brandId);
        model.addAttribute("product", dto);
        model.addAttribute("brandId", brandId);

        return "pages/admin/product/product_new";
    }

    @PostMapping("/create")
    public String createNewDepartment(RedirectAttributes attributes, @ModelAttribute("product") ProductRequestDto dto) {
        productFacade.create(dto);
        return "redirect:/admin/products";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable @Min(value = 1, message = "id can not be zero") Long id) {
        productFacade.delete(id);
        return "redirect:/admin/products";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable @NotBlank(message = "id can not be empty") String id, Model model) {
        try {
            Long productId = Long.parseLong(id);
            ProductResponseDto dto = productFacade.findById(productId);
            model.addAttribute("product", dto);
            return "pages/admin/product/product_details";
        } catch (NumberFormatException e) {
            throw new NumberFormatException("incorrect value id");
        }
    }
}
