/*
 * (#) net.brainage.nest.web.controller.AccountController.java
 * Created on 2016-02-15
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

import com.google.common.io.BaseEncoding;
import lombok.extern.slf4j.Slf4j;
import net.brainage.nest.data.model.User;
import net.brainage.nest.data.model.enums.UserState;
import net.brainage.nest.service.UserService;
import net.brainage.nest.web.form.SigninForm;
import net.brainage.nest.web.form.SignupForm;
import net.brainage.nuri.security.crypto.PasswordEncryptor;
import net.brainage.nuri.security.crypto.RandomNumberGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.concurrent.Callable;

/**
 * @author <a href="mailto:ms29.seo@gmail.com">ms29.seo</a>
 */
@Slf4j
@Controller
@RequestMapping(path = {"/account"})
public class AccountController {

    @Value("${application.allows-anonymous-access}")
    boolean allowsAnonymousAccess;

    @Inject
    PasswordEncryptor passwordEncryptor;

    @Inject
    RandomNumberGenerator passwordSaltGenerator;

    @Inject
    UserService userService;

    @RequestMapping(path = "/signup", method = RequestMethod.GET)
    public Callable<String> signupForm(Model model) {
        return () -> {
            model.addAttribute(new SignupForm());
            return "account/signup";
        };
    }

    @RequestMapping(path = "/signup", method = RequestMethod.POST)
    public Callable<String> signupAction(
            SignupForm form, BindingResult result,
            HttpServletRequest httpRequest, Model model) {
        return () -> {
            if (result.hasErrors()) {
                model.addAttribute(form);
                return signinForm(model);
            }

            if (log.isDebugEnabled()) {
                log.debug("input signup form: {}", form.toString());
            }

            User user = new User();
            user.setUsername(form.getUsername());

            byte[] salt = passwordSaltGenerator.generate();
            String passwordSalt = BaseEncoding.base64().encode(salt);
            user.setPasswordSalt(passwordSalt);

            String encryptedPassword = passwordEncryptor.encrypt(form.getPassword(), salt);
            user.setPassword(encryptedPassword);

            user.setName(form.getName());
            user.setEmail(form.getEmail());
            user.setState(UserState.LOCKED);
            if (allowsAnonymousAccess) {
                user.setState(UserState.ACTIVE);
            }

            user.setCreatedOn(new Date());
            user.setLastModifiedOn(new Date());

            if (log.isDebugEnabled()) {
                log.debug("user: {}", user.toString());
            }

            userService.create(user);

            return "redirect:/account/signin";
        };
    }

    @RequestMapping(path = "/signin", method = RequestMethod.GET)
    public String signinForm(Model model) {
        return "account/signin";
    }

    @RequestMapping(path = "/signin", method = RequestMethod.POST)
    public String signinAction(SigninForm form, BindingResult result, Model model) {
        // username과 password를 비교하여 인증을 한다.

        // 2-factor authentication이 활성화 되어 있다면 otp 확인 form으로 redirect 한다.
        boolean enable2FactorAuth = false;
        if (enable2FactorAuth) {
            return "redirect:/account/signin/otp";
        }

        // 2-factor authentication이 비활성화 되어 있다면 next 값이 존재하면 next uri로 redirect 하고
        // 그렇지 않다면 기본 main 화면으로 redirect 한다.
        if (StringUtils.hasText(form.getNext())) {
            return "redirect:" + form.getNext();
        }
        return "redirect:/";
    }

    @RequestMapping(path = "/signin/opt", method = RequestMethod.GET)
    public String signinOtpForm(Model model) {
        return "account/signinOtpForm";
    }

    @RequestMapping(path = "/signin/otp", method = RequestMethod.POST)
    public String signinOtpAction(SigninForm form, BindingResult result, Model model) {
        return "redirect:/";
    }

}
