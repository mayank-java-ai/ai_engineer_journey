package com.mayank.ai.backend.memory;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class ConversationMemory {

  private final List<String> history = new ArrayList<>();

  public void addMessage(String message) {
    history.add(message);
  }

  public String getHistory() {

    return String.join("\n", history);
  }
}
