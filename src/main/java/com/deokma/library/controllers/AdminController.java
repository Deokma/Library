package com.deokma.library.controllers;

import com.deokma.library.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Controller with admin functions
 *
 * @author Denis Popolamov
 */
@Controller
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {
    private final UserService userService;

    /**
     * Go to the Admin panel page
     *
     * @return Admin Page
     */
    @GetMapping("/admin")
    public String admin(Model model) {
        model.addAttribute("users", userService.list());
        return "admin";
    }

    /**
     * Method for ban User
     */
    @PostMapping("/admin/user/ban/{id}")
    public String userBan(@PathVariable("id") Long id) {
        userService.banUser(id);
        return "redirect:/admin";
    }

    /**
     * Method for unban User
     */
    @PostMapping("/admin/user/unban/{id}")
    public String userUnban(@PathVariable("id") Long id) {
        userService.unbanUser(id);
        return "redirect:/admin";
    }
}
