package com.mayank.ai.backend.agents;

import com.mayank.ai.backend.retriever.RetrievalResult;
import com.mayank.ai.backend.retriever.VectorRetriever;
import com.mayank.ai.backend.service.AiService;
// import com.mayank.ai.backend.service.AiService;
// import com.mayank.ai.backend.service.DocumentService;
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

    RetrievalResult retrieval = retriever.retrieve(question);

    String context = retrieval.context();

    if (context == null || context.isBlank()) {

      return """
      Information not found
      in uploaded documents.
      """;
    }

    String prompt =
        """
        You are an enterprise policy assistant.

        Use conversation history
        and retrieved context.

        If a follow-up question is asked,
        use previous conversation.

        Answer ONLY from context.

        Context:
        %s

        Question:
        %s
        """
            .formatted(context, question);

    String answer = aiService.askWithoutMemory(prompt);

    return answer + "\n\nSources:\n" + String.join("\n", retrieval.sources());
  }
}
