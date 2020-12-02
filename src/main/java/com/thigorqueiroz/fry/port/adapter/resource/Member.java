package com.thigorqueiroz.fry.port.adapter.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/members")
public class Member {
    private static final Logger Logger = LoggerFactory.getLogger(Member.class);
}
