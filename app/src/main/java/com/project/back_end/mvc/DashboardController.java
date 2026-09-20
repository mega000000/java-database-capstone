package com.project.back_end.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class DashboardController {

    // Simple mock token validation for MVC, this should ideally use TokenService
    private boolean isValidToken(String token, String expectedRole) {
        // In a real app, you would parse the JWT token here
        return token != null && token.length() > 5; // Basic mock condition
    }

    @GetMapping("/adminDashboard/{token}")
    public String adminDashboard(@PathVariable String token, Model model) {
        if (isValidToken(token, "ADMIN")) {
            return "admin/adminDashboard";
        }
        return "redirect:/login";
    }

    @GetMapping("/doctorDashboard/{token}")
    public String doctorDashboard(@PathVariable String token, Model model) {
        if (isValidToken(token, "DOCTOR")) {
            return "doctor/doctorDashboard";
        }
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login() {
        return "login"; // assuming a login template would exist
    }
}
