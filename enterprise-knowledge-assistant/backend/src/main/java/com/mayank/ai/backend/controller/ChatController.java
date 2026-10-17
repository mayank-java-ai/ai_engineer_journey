package com.mayank.ai.backend.controller;

import com.mayank.ai.backend.agents.OrchestratorAgent;
import com.mayank.ai.backend.dto.ChatRequest;
import com.mayank.ai.backend.dto.ChatResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chat")
@CrossOrigin("*")
public class ChatController {

  private final OrchestratorAgent orchestratorAgent;

  public ChatController(OrchestratorAgent orchestratorAgent) {
    this.orchestratorAgent = orchestratorAgent;
  }

  @PostMapping
  public ChatResponse chat(@RequestBody ChatRequest request) {

    String answer = orchestratorAgent.process(request.getQuestion());

    return new ChatResponse(answer);
  }
}
