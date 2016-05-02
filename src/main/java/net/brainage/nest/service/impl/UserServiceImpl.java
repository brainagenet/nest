/*
 * (#) net.brainage.nest.service.impl.UserServiceImpl.java
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
package net.brainage.nest.service.impl;

import com.google.common.io.BaseEncoding;
import lombok.extern.slf4j.Slf4j;
import net.brainage.nest.data.model.User;
import net.brainage.nest.data.model.UserState;
import net.brainage.nest.data.repository.UserRepository;
import net.brainage.nest.service.UserService;
import net.brainage.nuri.security.crypto.PasswordEncryptor;
import net.brainage.nuri.security.crypto.RandomNumberGenerator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.Date;

/**
 * @author <a href="mailto:ms29.seo+ara@gmail.com">ms29.seo</a>
 */
@Slf4j
@Service("userService")
@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
public class UserServiceImpl implements UserService {

    @Inject
    private UserRepository userRepository;

    @Inject
    private RandomNumberGenerator randomNumberGenerator;

    @Inject
    private PasswordEncryptor passwordEncryptor;

    @Transactional(rollbackFor = Throwable.class)
    public void createUser(final User user) {
        // created password salt
        byte[] saltBytes = randomNumberGenerator.generate();
        String passwordSalt = BaseEncoding.base64().encode(saltBytes);
        user.setPasswordSalt(passwordSalt);

        // encrypt password with salt
        String encryptedPassword = passwordEncryptor.encrypt(user.getPassword(), saltBytes);
        user.setPassword(encryptedPassword);

        // set user state
        user.setState(UserState.ACTIVE);

        Date now = new Date();
        user.setCreatedOn(now);
        user.setLastModifiedOn(now);

        userRepository.save(user);
    }


}
