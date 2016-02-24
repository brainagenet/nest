/*
 * (#) net.brainage.nest.service.impl.UserServiceImpl.java
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
package net.brainage.nest.service.impl;

import com.google.common.io.BaseEncoding;
import lombok.extern.slf4j.Slf4j;
import net.brainage.nest.data.model.User;
import net.brainage.nest.data.model.enums.UserState;
import net.brainage.nest.data.repository.UserRepository;
import net.brainage.nest.service.UserService;
import net.brainage.nuri.security.crypto.PasswordEncryptor;
import net.brainage.nuri.security.crypto.RandomNumberGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.Date;

/**
 * @author <a href="mailto:ms29.seo@gmail.com">ms29.seo</a>
 */
@Slf4j
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Value("${application.allows-anonymous-access}")
    boolean allowsAnonymousAccess;

    @Inject
    PasswordEncryptor passwordEncryptor;

    @Inject
    RandomNumberGenerator passwordSaltGenerator;

    @Inject
    UserRepository userRepository;

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public void create(User user) {
        // generate password salt byte
        byte[] salt = passwordSaltGenerator.generate();
        // encoding to base64 string
        String passwordSalt = BaseEncoding.base64().encode(salt);
        user.setPasswordSalt(passwordSalt);
        log.debug("user password salt: {}", passwordSalt);

        // encrypt password with salt for user
        String encryptedPassword = passwordEncryptor.encrypt(user.getPassword(), salt);
        user.setPassword(encryptedPassword);
        log.debug("user encrypted password: {}", encryptedPassword);

        // set user state
        user.setState(UserState.LOCKED);
        if (allowsAnonymousAccess) {
            user.setState(UserState.ACTIVE);
        }
        log.debug("user state: {}", user.getState());

        Date now = new Date();
        user.setCreatedOn(now);
        user.setLastModifiedOn(now);

        userRepository.save(user);
    }

}
