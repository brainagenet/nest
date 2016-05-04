/*
 * (#) net.brainage.nest.web.controller.AccountController.java
 * Created on 2016-05-02
 *
 * Copyright 2015 brainage.net
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. 
 */
package net.brainage.nest.web.controller;

import lombok.extern.slf4j.Slf4j;
import net.brainage.nest.data.model.User;
import net.brainage.nest.service.UserService;
import net.brainage.nest.service.UserNotAuthenticatedException;
import net.brainage.nest.service.UserNotFoundException;
import net.brainage.nest.web.form.SigninForm;
import net.brainage.nest.web.form.SignupForm;
import net.brainage.nest.web.util.HttpRequestUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author <a href="mailto:ms29.seo+ara@gmail.com">ms29.seo</a>
 */
@Slf4j
@Controller
@RequestMapping(path = {"/account"})
public class AccountController {

    @Autowired
    ModelMapper modelMapper;

    @Inject
    private UserService userService;

    /* ================================================================================================ */
    /* Controller Layer에서는 Request Parameter에 대한 Validation Check를 하고                          */
    /* Service Layer를 호출하여 조회된 결과값을 View Layer에 전달하는 역할을 한다.                      */
    /* 따라서 Service Layer의 method와 1:1 Mapping이 된다. (복수의 Service를 호출하는 것은 금지 됩니다. */
    /* ================================================================================================ */

    @RequestMapping(path = {"/signup/"}, method = RequestMethod.GET)
    public String signupForm(Model model) {
        if (!model.containsAttribute("signupForm")) {
            model.addAttribute("signupForm", new SignupForm());
        }
        return "account/signup";
    }

    @RequestMapping(path = {"/signup/"}, method = RequestMethod.POST)
    public String signupAction(SignupForm signupForm, BindingResult result, HttpServletRequest request, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("signupForm", signupForm);
            return signupForm(model);
        }

        // -------------------------------------------------------------
        // convert dto to entity
        // -------------------------------------------------------------
        User user = modelMapper.map(signupForm, User.class);
        // get user lang from http request
        user.setLang(HttpRequestUtils.getLanguage(request));
        // save new user
        userService.createUser(user);

        return "redirect:/account/signin/?next=/";
    }

    @RequestMapping(path = {"/signin/"}, method = RequestMethod.GET)
    public String signinForm(@RequestParam(required = false, defaultValue = "/") String next, Model model) {
        if (!model.containsAttribute("signinForm")) {
            model.addAttribute("signinForm", new SigninForm());
        }
        model.addAttribute("next", next);
        return "account/signin";
    }

    /*
    @RequestMapping(path = {"/signin/"}, method = RequestMethod.POST)
    public String signinAction(SigninForm signinForm,
                               BindingResult result,
                               @RequestParam(required = false, defaultValue = "/") String next,
                               HttpServletRequest request,
                               Model model) {
        if (result.hasErrors()) {
            model.addAttribute("signinForm", signinForm);
            return signinForm(next, model);
        }
        try {
            User user = userService.authenticat(signinForm.getUsername(), signinForm.getPassword());
            HttpSession session = request.getSession(true);
            session.setAttribute("auth.user", user);
            return String.format("redirect:/%s", next);
        } catch (UserNotFoundException | UserNotAuthenticatedException ex) {
            model.addAttribute("error.auth.message", ex.getMessage());
            model.addAttribute("error.auth.exception", ex);
            log.error("exception: {}", ex.getMessage());
            return signinForm(next, model);
        }
    }
    */

    @RequestMapping(path = {"/password/reset/"}, method = RequestMethod.GET)
    public String passwordResetForm(Model model) {
        return "account/password/reset";
    }

    @RequestMapping(path = {"/password/reset/"}, method = RequestMethod.POST)
    public String passwordResetAction(
            @RequestParam(name = "username_or_email", required = true) String usernameOrEmail,
            Model model) {
        return "account/password/reset";
    }

}
