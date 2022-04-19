package com.example.w7_miniproject_backend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ResponseBody
public class SampleController {
    @RequestMapping("/sample/")
    public String sample(
            @RequestParam("residence") int residence,
            @RequestParam("size") int size,
            @RequestParam("style") int style,
            @RequestParam("space") int space
    )
    {
        return
    }

    

