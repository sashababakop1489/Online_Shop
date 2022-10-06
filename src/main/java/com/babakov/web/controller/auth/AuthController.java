package com.babakov.web.controller.auth;

import com.babakov.config.security.SecurityService;
import com.babakov.facade.AuthValidatorFacade;
import com.babakov.facade.ProductFacade;
import com.babakov.facade.RegistrationFacade;
import com.babakov.persistence.type.RoleType;
import com.babakov.util.SecurityUtil;
import com.babakov.web.controller.AbstractController;
import com.babakov.web.dto.request.register.AuthDto;
import com.babakov.web.dto.response.ProductResponseDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@Controller
public class AuthController extends AbstractController {

    private final RegistrationFacade registrationFacade;
    private final AuthValidatorFacade authValidatorFacade;
    private final SecurityService securityService;
    private final ProductFacade productFacade;

    public AuthController(
            RegistrationFacade registrationFacade,
            AuthValidatorFacade authValidatorFacade,
            SecurityService securityService, ProductFacade productFacade) {
        this.registrationFacade = registrationFacade;
        this.authValidatorFacade = authValidatorFacade;
        this.securityService = securityService;
        this.productFacade = productFacade;
    }
    @GetMapping("/")
    public String main(Model model){
        return redirectProcess(model);
    }

    @GetMapping("/open")
    public String open(Model model){
        return "pares/open/open";
    }

    @GetMapping("/products/{id}")
    public String findAll(Model model, @PathVariable Long id) {
        List<ProductResponseDto> products = productFacade.findAllByBrandId(id);
        model.addAttribute("products", products);
        return "pages/open/product_all";
    }


    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        showMessage(model, false);
        boolean authenticated = securityService.isAuthenticated();
        if (authenticated) {
            if (SecurityUtil.hasRole(RoleType.ROLE_ADMIN.name())) {
                return "redirect:/admin/dashboard";
            }
            if (SecurityUtil.hasRole(RoleType.ROLE_PERSONAL.name())) {
                return "redirect:/personal/dashboard";
            }
        }
        if (error != null) {
            showError(model, "Your email and password is invalid.");
        }
        if (logout != null) {
            showInfo(model, "You have been logged out successfully.");
        }
        return "login";
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        if (securityService.isAuthenticated()) {
            return redirectProcess(model);
        }
        model.addAttribute("authForm", new AuthDto());
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("authForm") AuthDto authForm, BindingResult bindingResult, Model model) {
        showMessage(model, false);
        authValidatorFacade.validate(authForm, bindingResult);
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        registrationFacade.registration(authForm);
        securityService.autoLogin(authForm.getEmail(), authForm.getPasswordConfirm());
        return redirectProcess(model);
    }

    private String redirectProcess(Model model) {
        showMessage(model, false);
        if (SecurityUtil.hasRole(RoleType.ROLE_ADMIN.name())) {
            return "redirect:/admin/dashboard";
        }
        if (SecurityUtil.hasRole(RoleType.ROLE_PERSONAL.name())) {
            return "redirect:/personal/dashboard";
        }
        return "redirect:/login";
    }
}
