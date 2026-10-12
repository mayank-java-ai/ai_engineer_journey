package com.mayank.ai.backend.agents;

import com.mayank.ai.backend.service.AiService;
import org.springframework.stereotype.Component;

@Component
public class EmailAgent {

    private final AiService aiService;

    public EmailAgent(
            AiService aiService
    ) {

        this.aiService =
                aiService;
    }

    public String draftEmail(
            String prompt
    ) {

        return aiService.ask(
                "Draft professional email:\n"
                        + prompt
        );
    }
}