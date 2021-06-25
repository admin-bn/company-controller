/*
 * Copyright 2021 Bundesreplublik Deutschland
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

package com.ibm.ssi.controller.company.init;

import java.time.Instant;

import javax.annotation.PostConstruct;

import com.ibm.ssi.controller.company.domain.Authority;
import com.ibm.ssi.controller.company.domain.User;
import com.ibm.ssi.controller.company.repository.UserRepository;
import com.ibm.ssi.controller.company.security.AuthoritiesConstants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DatabaseInit {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Value("${ssibk.company.controller.admin.username}")
    private String adminUsername;

    @Value("${ssibk.company.controller.admin.password}")
    private String adminPassword;

    @PostConstruct
    public void init() {
        initAdminUser();
    }

    private void initAdminUser() {
        Authority adminAuthority = new Authority();
        adminAuthority.setName(AuthoritiesConstants.ADMIN);

        String id = "admin";
        if (this.userRepository.existsById(id) == false) {
            User adminUser = new User();
            adminUser.setId(id);
            adminUser.setLogin(adminUsername);
            adminUser.setPassword(this.passwordEncoder.encode(adminPassword));
            adminUser.setFirstName("admin");
            adminUser.setLastName("Administrator");
            adminUser.setEmail("admin@localhost");
            adminUser.setCreatedBy("system");
            adminUser.setCreatedDate(Instant.now());
            adminUser.getAuthorities().add(adminAuthority);
            userRepository.insert(adminUser);
        }
    }
}
