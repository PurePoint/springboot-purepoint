package com.purepoint.springbootpurepoint.community.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Community")
public class CommunityController {
    @GetMapping("/")
    public String test() {
        return "Hello Community";
    }
}
