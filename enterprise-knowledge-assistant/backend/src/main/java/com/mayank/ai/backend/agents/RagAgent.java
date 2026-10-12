package com.mayank.ai.backend.agents;

import com.mayank.ai.backend.retriever.VectorRetriever;
import com.mayank.ai.backend.service.AiService;
//import com.mayank.ai.backend.service.AiService;
//import com.mayank.ai.backend.service.DocumentService;
import org.springframework.stereotype.Component;

@Component
public class RagAgent {

    private final AiService aiService;
    private final VectorRetriever retriever;
    // private final DocumentService documentService;

    public RagAgent(AiService aiService, VectorRetriever retriever) {
        this.aiService = aiService;
        this.retriever = retriever;
    }

    public String answer(String question) {
        String context = retriever.retrieve(question);
        if (
            context.isBlank()
    ) {

        return """
                No matching context found
                in uploaded documents.
                """;
    }
        String prompt =
                """
                Answer only using context.

                Context:
                %s

                Question:
                %s
                """
                .formatted(context, question);

        return aiService.ask(prompt);
    }
}