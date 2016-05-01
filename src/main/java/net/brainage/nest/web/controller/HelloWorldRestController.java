/*
 * (#) net.brainage.nest.web.controller.WelcomeRestController.java
 * Created on 2016-05-01
 */
package net.brainage.nest.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author <a href="mailto:ms29.seo@gmail.com">ms29.seo</a>
 */
@RestController
public class HelloWorldRestController {

    @RequestMapping("/hello")
    public String hello() {
        return "Hello World!";
    }

}
