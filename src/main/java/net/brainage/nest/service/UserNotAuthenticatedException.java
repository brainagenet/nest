/*
 * (#) net.brainage.nest.service.UserNotAuthenticatedException.java
 * Created on 2016-05-03
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
package net.brainage.nest.service;

import net.brainage.nest.core.NestException;

/**
 * @author <a href="mailto:ms29.seo+ara@gmail.com">ms29.seo</a>
 */
public class UserNotAuthenticatedException extends NestException {

    private final String username;

    public UserNotAuthenticatedException(String username) {
        super("User for '' dose not authenticate. Please check username and password.");
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

}
