/*
 * (#) net.brainage.nest.data.model.User.java
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
package net.brainage.nest.data.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import net.brainage.nest.data.model.enums.UserState;

import javax.persistence.*;
import java.util.Date;

/**
 * @author <a href="mailto:ms29.seo@gmail.com">ms29.seo</a>
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(name = "UK_USERS_USERNAME", columnNames = {"username"})})
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(name = "password_salt", nullable = false)
    private String passwordSalt;

    @Column(name = "otp_secret", nullable = true)
    private String otpSecret;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    private String lang;

    @Enumerated(EnumType.STRING)
    private UserState state;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_on")
    private Date createdOn;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_modified_on")
    private Date lastModifiedOn;

}
