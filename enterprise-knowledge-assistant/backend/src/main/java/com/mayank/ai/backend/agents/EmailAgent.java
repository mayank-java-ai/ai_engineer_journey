package com.mayank.ai.backend.agents;

import com.mayank.ai.backend.service.AiService;
import org.springframework.stereotype.Component;

@Component
public class EmailAgent {

  private final AiService aiService;

  public EmailAgent(AiService aiService) {

    this.aiService = aiService;
  }

  public String draftEmail(
        String userPrompt
) {

    String prompt =
            """
            You are a professional email assistant.

            Generate:

            1. Subject
            2. Email body

            Keep language professional.

            User Request:
            %s
            """
            .formatted(userPrompt);

    return aiService.askWithoutMemory(
            prompt
    );
}
}
