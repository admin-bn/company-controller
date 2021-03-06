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

package com.ibm.ssi.controller.company.client.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

public class InvitationRequestDTO {

    @JsonProperty("mediation_id")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String mediationId;

    @JsonProperty("metadata")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String metadata;

    @JsonProperty("recipient_keys")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String[] recipientKeys;

    @JsonProperty("routing_keys")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String[] routingKeys;

    @JsonProperty("service_endpoint")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String serviceEndpoint;

    public InvitationRequestDTO () {};

}
