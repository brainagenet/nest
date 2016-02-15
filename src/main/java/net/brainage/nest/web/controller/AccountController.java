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

import lombok.extern.slf4j.Slf4j;
import net.brainage.nest.web.form.SigninForm;
import net.brainage.nest.web.form.SignupForm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author <a href="mailto:ms29.seo@gmail.com">ms29.seo</a>
 */
@Slf4j
@Controller
@RequestMapping(path = {"/account"})
public class AccountController {

    @RequestMapping(path = "/signup", method = RequestMethod.GET)
    public String signupForm(Model model) {
        return "account/signupForm";
    }

    @RequestMapping(path = "/signup", method = RequestMethod.POST)
    public String signupAction(SignupForm form, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute(form);
            return signinForm(model);
        }

        // TODO: 2-factor authentication이 활성화 된다면 QR Code URI로 Redirect 한다.
        boolean enable2FactorAuth = false;
        if (enable2FactorAuth) {
            return "redirect:/account/signup/opt/qrcode";
        }
        return "redirect:/account/signin";
    }

    @RequestMapping(path = "/signin", method = RequestMethod.GET)
    public String signinForm(Model model) {
        return "account/signinForm";
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
