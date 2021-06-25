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

package com.ibm.ssi.controller.company.service;

import com.ibm.ssi.controller.company.domain.IssuedCredential;
import com.ibm.ssi.controller.company.service.dto.IssuedCredentialDTO;
import com.ibm.ssi.controller.company.service.exceptions.EmployeeAlreadyExistsException;
import com.ibm.ssi.controller.company.service.dto.EmployeeDTO;

import java.util.List;
import java.util.Optional;

public interface IssuedCredentialService {

    IssuedCredential createIssuedCredential(IssuedCredential issuedCredential);

    List<IssuedCredentialDTO> getAllIssuedCredential();

    void resendIssuedCredential(EmployeeDTO employeeDTO, String connectionId) throws EmployeeAlreadyExistsException;

    Optional<IssuedCredentialDTO> getIssuedCredentialById(String id);

    void deleteIssuedCredential(String id);

}
