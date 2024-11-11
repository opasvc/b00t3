package com.dztzb00t3.j2t.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HelloController {

    @GetMapping
    public Map hello() {
        HashMap<Object, Object> hashMap = new HashMap<>();
        hashMap.put("hello", "world");
        hashMap.put("url", "https://artislg.com/api/v1/zaoanyun?token=386b181bc0e64a574ee5e403df87550f");
        return hashMap;
    }
}
