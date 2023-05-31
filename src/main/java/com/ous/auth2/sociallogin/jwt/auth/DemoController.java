package com.ous.auth2.sociallogin.jwt.auth;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/app")
public class DemoController {

    @GetMapping("/demo-controller")
    public String defaultLoginPage(){
        return "<b> Hello, Welcome to you buy</b>";
    }
}
