package com.babakov.web.controller;

import com.babakov.persistence.entity.user.User;
import com.babakov.persistence.repository.user.UserRepository;
import com.babakov.persistence.type.RoleType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    private final UserRepository userRepository;

    public MainController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public String main(){
        return "redirect:/brands";
    }

    @GetMapping("/dashboard")
    public String redirectToDashBoards() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByEmail(authentication.getName());
        if (user.getRoleType() == RoleType.ROLE_ADMIN) {
            return "redirect:/admin/dashboard";
        }
        if (user.getRoleType() == RoleType.ROLE_PERSONAL) {
            return "redirect:/personal/dashboard";
        }
        return "redirect:/brands";
    }
}
