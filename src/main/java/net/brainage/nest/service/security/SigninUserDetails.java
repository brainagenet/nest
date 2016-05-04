/*
 * (#) net.brainage.nest.service.security.SigninUserDetails.java
 * Created on 2016-05-04
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
package net.brainage.nest.service.security;

import lombok.Data;
import net.brainage.nest.data.model.User;
import net.brainage.nest.data.model.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * @author <a href="mailto:ms29.seo+ara@gmail.com">ms29.seo</a>
 */
@Data
public class SigninUserDetails extends org.springframework.security.core.userdetails.User {

    private final User user;

    public SigninUserDetails(User user) {
        super(user.getUsername(), user.getPassword(), convert2Autorities(user.getRoles()));
        this.user = user;
    }

    private static Collection<? extends GrantedAuthority> convert2Autorities(Set<UserRole> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>(0);
        for (UserRole role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }

}
