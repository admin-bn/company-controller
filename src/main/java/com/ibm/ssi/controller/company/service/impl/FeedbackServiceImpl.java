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

package com.ibm.ssi.controller.company.service.impl;

import com.ibm.ssi.controller.company.client.TRELLOClient;
import com.ibm.ssi.controller.company.service.FeedbackService;
import com.ibm.ssi.controller.company.service.dto.FeedbackDTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    private final Logger log = LoggerFactory.getLogger(FeedbackServiceImpl.class);

    @Autowired
    private TRELLOClient trelloClient;

    @Value("${ssibk.company.controller.trello.apikey}")
    private String apiKey;

    @Value("${ssibk.company.controller.trello.apitoken}")
    private String apiToken;

    @Value("${ssibk.company.controller.trello.idlist}")
    private String idList;

    @Value("${ssibk.company.controller.trello.issue}")
    private String issue;

    @Value("${ssibk.company.controller.trello.question}")
    private String question;

    @Value("${ssibk.company.controller.trello.comment}")
    private String comment;


    /**
     * Save Feedback in Trello
     *
     * @param feedbackDTO the entity to save.
     * @return the persisted entity.
     */

    @Override
    public void createFeedback(FeedbackDTO feedbackDTO) {

        String topic = this.getTopic(feedbackDTO);
        this.trelloClient.createCard(apiKey, apiToken, idList, feedbackDTO.getFeedback(), topic);
    }

    private String getTopic(FeedbackDTO feedbackDto) {
        log.debug("get topic");
        String feedback = feedbackDto.getTopic().toString();
        switch(feedback)
        {
            case "Issue":
                feedback = issue;
                break;
            case "Question":
                feedback = question;
                break;
            case "Comment":
                feedback = comment;
                break;
            default:
                throw new IllegalArgumentException("Invalid label");
        }

        return feedback;
    }
}
