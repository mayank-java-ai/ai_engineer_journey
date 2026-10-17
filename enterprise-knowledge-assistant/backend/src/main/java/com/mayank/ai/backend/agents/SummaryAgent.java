package com.mayank.ai.backend.agents;

import com.mayank.ai.backend.service.AiService;
import org.springframework.stereotype.Component;

@Component
public class SummaryAgent {

  private final AiService aiService;

  public SummaryAgent(AiService aiService) {
    this.aiService = aiService;
  }

  public String summarize(String content) {

    return aiService.ask("Summarize:\n" + content);
  }
}
