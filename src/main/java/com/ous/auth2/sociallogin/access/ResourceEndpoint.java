package com.ous.auth2.sociallogin.access;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class ResourceEndpoint {

    @GetMapping
    public String hiThere(){
        return "<br>Hi there</b>";
    }
}
