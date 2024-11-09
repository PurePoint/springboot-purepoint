package com.purepoint.springbootpurepoint.auth.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @GetMapping("/login/google")
    public void googleLogin(HttpServletResponse response) throws IOException {
        String redirectUrl = "https://accounts.google.com/o/oauth2/v2/auth"
                + "?scope=profile%20email"
                + "&access_type=offline"
                + "&include_granted_scopes=true"
                + "&response_type=code"
                + "&state=state_parameter_passthrough_value"
                + "&redirect_uri=" + "http://localhost:3000/redirect"
                + "&client_id=" + "69356984572-fhmvvd12bdhvaq33peiu1t0jl2vvmob1.apps.googleusercontent.com";

        response.sendRedirect(redirectUrl);
    }

    @GetMapping("/login/google/callback")
    @ResponseBody
    public String googleCallback(@RequestParam("code") String code, HttpServletRequest request) {
        return "Auth code received: " + code;
    }
}