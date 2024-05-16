package com.auth0.example;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

/**
 * Controller for the home page.
 */
@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model, @AuthenticationPrincipal OidcUser principal) {
        if (principal != null) {
            model.addAttribute("profile", principal.getClaims());
            model.addAttribute("profile", principal.getAttributes());
        }
        getUserInfo(principal);
        return "index";
    }

    @GetMapping("/user-info")
    public ResponseEntity<?> getUserInfo(@AuthenticationPrincipal OidcUser principal) {
        if (principal != null) {
            String juanGamezEndpoint = "http://localhost:8081/nigga";
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.postForObject(juanGamezEndpoint, principal.getAttributes(), Void.class);

            return ResponseEntity.ok(principal.getAttributes());
        } else {
            return ResponseEntity.status(401).body("Usuario no autenticado");
        }
    }
}
