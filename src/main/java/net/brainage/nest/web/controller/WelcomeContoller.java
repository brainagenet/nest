/*
 * (#) net.brainage.nest.web.controller.WelcomeContoller.jav
 * Created on 2016-05-01
 */
package net.brainage.nest.web.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author <a href="mailto:ms29.seo@gmail.com">ms29.seo</a>
 */
@Controller
public class WelcomeContoller {

    @Value("${application.title:nest}")
    private String applicationTitle;

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("title", applicationTitle);
        return "index";
    }

}
