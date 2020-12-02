package com.thigorqueiroz.fry.port.adapter.resource;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/campaigns")
public class Campaign {
    private static final Logger Logger = LoggerFactory.getLogger(Campaign.class);
}
