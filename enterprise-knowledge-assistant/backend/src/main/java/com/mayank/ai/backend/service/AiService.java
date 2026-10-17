package com.mayank.ai.backend.service;

import com.mayank.ai.backend.memory.ConversationMemory;
import dev.langchain4j.model.ollama.OllamaChatModel;
import org.springframework.stereotype.Service;

@Service
public class AiService {

  private final OllamaChatModel model;
  // private final DocumentService documentService;
  private final ConversationMemory memory;

  public AiService(ConversationMemory memory) {
    this.memory = memory;
    this.model =
        OllamaChatModel.builder().baseUrl("http://localhost:11434").modelName("llama3.2").build();
  }

  public String ask(String question) {
    memory.addMessage("User: " + question);

    String prompt = memory.getHistory();
    String response = model.chat(prompt);

    memory.addMessage("AI: " + response);

    return response;
  }

  public String askWithoutMemory(String prompt) {

    return model.chat(prompt);
  }
}
