package com.tosan.application;


import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/")
@RestController
public class Controller {

    @GetMapping(value = "/helloworld",
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<String> helloworld(String name) {
        if (!StringUtils.hasText(name)) {
            name = "Stranger";
        }
        return new ResponseEntity<>("Hello " + name, HttpStatus.OK);
    }

    @GetMapping(value = "/author",
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<String> author() {
        return new ResponseEntity<>("Mehrad Mehri", HttpStatus.OK);
    }

}