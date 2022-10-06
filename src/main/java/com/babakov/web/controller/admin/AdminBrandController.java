package com.babakov.web.controller.admin;

import com.babakov.facade.BrandFacade;
import com.babakov.web.controller.AbstractController;
import com.babakov.web.dto.request.BrandRequestDto;
import com.babakov.web.dto.response.BrandResponseDto;
import com.babakov.web.dto.response.PageData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Controller
@RequestMapping("/admin/brands")
public class AdminBrandController extends AbstractController {

    private final HeaderName[] columnNames = new HeaderName[] {
            new HeaderName("#", null, null),
            new HeaderName("image", "image", "image_url"),
            new HeaderName("name", "name", "brandname"),
    };


    private final BrandFacade brandFacade;


    public AdminBrandController(BrandFacade brandFacade) {
        this.brandFacade = brandFacade;
    }


    @GetMapping
    public String findAll(Model model, WebRequest request) {
        PageData<BrandResponseDto> response = brandFacade.findAll(request);
        initDataTable(response, columnNames, model);
        model.addAttribute("createUrl", "/admin/brands/all");
        model.addAttribute("createNew", "/admin/brands/new");
        model.addAttribute("cardHeader", "All Brands");
        return "pages/admin/brand/brand_all";
    }

    @PostMapping("/all")
    public ModelAndView findAllRedirect(WebRequest request, ModelMap model) {
        return findAllRedirect(request, model, "brands");
    }

    @GetMapping("/new")
    public String redirectToNewBookPage(Model model) {
        model.addAttribute("brand", new BrandRequestDto());
        return "pages/admin/brand/brand_new";
    }
//    @RequestParam("file") MultipartFile file
    @PostMapping("/create")
    public String createNewDepartment(@ModelAttribute("brand") BrandRequestDto dto) {
//        if (StringUtils.isBlank(file.getOriginalFilename())) {
//            showError(attributes, "image can not be empty");
//            return "redirect:/brands/new";
//        }
        //dto.setBrandImage(file);
        brandFacade.create(dto);
        return "redirect:/admin/brands";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable @Min(value = 1, message = "id can not be zero") Long id) {
        brandFacade.delete(id);
        return "redirect:/admin/brands";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable @NotBlank(message = "id can not be empty") Long id, Model model) {
        try {
           // Long brandId = Long.parseLong(id);
            BrandResponseDto dto = brandFacade.findById(id);
            model.addAttribute("brand", dto);
            return "pages/admin/brand/brand_details";
        } catch (NumberFormatException e) {
            throw new NumberFormatException("incorrect value id");
        }
    }
}
