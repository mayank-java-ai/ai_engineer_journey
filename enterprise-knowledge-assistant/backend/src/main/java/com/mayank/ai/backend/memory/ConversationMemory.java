package com.mayank.ai.backend.memory;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ConversationMemory {

    private final List<String> history =
            new ArrayList<>();

    public void addMessage(
            String message
    ) {
        history.add(message);
    }

    public String getHistory() {

        return String.join(
                "\n",
                history
        );
    }
}