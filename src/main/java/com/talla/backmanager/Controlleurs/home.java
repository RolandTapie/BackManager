package com.talla.backmanager.Controlleurs;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
public class home {
    @GetMapping()
    public String home(){
        return "application Back Manager deployée";
    }

}
