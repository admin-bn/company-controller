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

package com.ibm.ssi.controller.company.service.dto;

import com.ibm.ssi.controller.company.domain.IssuedCredential;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

public class IssuedCredentialDTO {

    @Id
    private String id;

    @Field("issuance_date")
    private Date issuanceDate;

    public IssuedCredentialDTO(IssuedCredential issuedCredential) {

        this.id = issuedCredential.getId();
        this.issuanceDate = issuedCredential.getIssuanceDate();

    }

    public IssuedCredentialDTO() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

	public Date getIssuanceDate() {
		return issuanceDate;
	}

	public void setIssuanceDate(Date issuanceDate) {
        this.issuanceDate = issuanceDate;
	}

    @Override
    public String toString() {
        return "IssuedCredentialDTO [id=" + id + ", issuanceDate=" + issuanceDate + "]";
    }
}












