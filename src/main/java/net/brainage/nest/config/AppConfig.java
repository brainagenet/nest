/*
 * (#) net.brainage.nest.config.AppConfig.java
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
package net.brainage.nest.config;

import net.brainage.nuri.security.crypto.PBKDF2PasswordEncryptor;
import net.brainage.nuri.security.crypto.PasswordEncryptor;
import net.brainage.nuri.security.crypto.RandomNumberGenerator;
import net.brainage.nuri.security.crypto.SecureRandomNumberGenerator;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author <a href="mailto:ms29.seo@gmail.com">ms29.seo</a>
 */
@Configuration
public class AppConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public RandomNumberGenerator randomNumberGenerator() {
        return new SecureRandomNumberGenerator(16);
    }

    @Bean
    public PasswordEncryptor passwordEncryptor() {
        return new PBKDF2PasswordEncryptor();
    }

}
