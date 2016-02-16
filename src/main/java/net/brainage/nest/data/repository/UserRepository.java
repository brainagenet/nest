/*
 * (#) net.brainage.nest.data.repository.UserRepository.java
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
package net.brainage.nest.data.repository;

import net.brainage.nest.data.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author <a href="mailto:ms29.seo@gmail.com">ms29.seo</a>
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * username에 해당하는 사용자 정보 조회한다.
     *
     * @param username
     * @return
     */
    User findByUsername(String username);

}
