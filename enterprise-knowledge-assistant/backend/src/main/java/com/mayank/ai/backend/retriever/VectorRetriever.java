package com.mayank.ai.backend.retriever;

import com.mayank.ai.backend.service.VectorStoreService;
import com.mayank.ai.backend.vector.VectorDocument;
import org.springframework.stereotype.Component;

@Component
public class VectorRetriever {

    private final VectorStoreService vectorStoreService;

    public VectorRetriever(
            VectorStoreService vectorStoreService
    ) {
        this.vectorStoreService = vectorStoreService;
    }

    public String retrieve(
            String question
    ) {

        StringBuilder context =
                new StringBuilder();

        String lowerQuestion =
                question.toLowerCase();

        for (
                VectorDocument doc :
                vectorStoreService.getDocuments()
        ) {

            String content =
                    doc.getContent()
                            .toLowerCase();

            if (
                    content.contains("paternity")
                    && lowerQuestion.contains("paternity")
            ) {

                context.append(
                        doc.getContent()
                ).append("\n");

            } else if (

                    content.contains("maternity")
                    && lowerQuestion.contains("maternity")

            ) {

                context.append(
                        doc.getContent()
                ).append("\n");

            } else if (

                    content.contains("travel")
                    && lowerQuestion.contains("travel")

            ) {

                context.append(
                        doc.getContent()
                ).append("\n");
            }
        }

        return context.toString();
    }
}
