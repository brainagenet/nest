/*
 * (#) net.brainage.nest.web.form.SignupForm.java
 * Created on 2016-02-16
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
package net.brainage.nest.web.form;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 회원가입 양식
 *
 * @author <a href="mailto:ms29.seo@gmail.com">ms29.seo</a>
 */
@Data
@NoArgsConstructor
public class SignupForm {

    @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9-]+([_.][a-zA-Z0-9-]+)*$")
    private String username;

    @NotNull
    private String password;

    @NotNull
    private String passwordConfirm;

    @NotNull
    private String name;

    @NotNull
    @Email
    private String email;

}
