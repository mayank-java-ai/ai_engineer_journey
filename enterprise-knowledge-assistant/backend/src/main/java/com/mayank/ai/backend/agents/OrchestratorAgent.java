package com.mayank.ai.backend.agents;

import org.springframework.stereotype.Component;

@Component
public class OrchestratorAgent {

  private final RagAgent ragAgent;

  private final SummaryAgent summaryAgent;

  private final EmailAgent emailAgent;

  public OrchestratorAgent(RagAgent ragAgent, SummaryAgent summaryAgent, EmailAgent emailAgent) {

    this.ragAgent = ragAgent;

    this.summaryAgent = summaryAgent;

    this.emailAgent = emailAgent;
  }

  public String process(String question) {

    String lower = question.toLowerCase();

    if(
    question.toLowerCase()
    .contains("email")
    ||

    question.toLowerCase()
    .contains("mail")
) {

    return emailAgent
            .draftEmail(
                    question
            );
}

    if (lower.contains("summary")) {

      return summaryAgent.summarize(question);
    }

    return ragAgent.answer(question);
  }
}
