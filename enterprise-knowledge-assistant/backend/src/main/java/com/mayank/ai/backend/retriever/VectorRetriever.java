package com.mayank.ai.backend.retriever;

import com.mayank.ai.backend.embedding.OllamaEmbeddingService;
import com.mayank.ai.backend.qdrant.QdrantService;
import com.mayank.ai.backend.qdrant.dto.QdrantSearchResponse;
import java.util.ArrayList;
import java.util.List;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class VectorRetriever {

  private final QdrantService qdrantService;
  private final OllamaEmbeddingService embeddingService;

  public VectorRetriever(QdrantService qdrantService, OllamaEmbeddingService embeddingService) {
    this.qdrantService = qdrantService;
    this.embeddingService = embeddingService;
  }

  @Cacheable(value = "retrieval-cache", key = "#question")
  public RetrievalResult retrieve(String question) {
    System.out.println("QUESTION = " + question);
    System.out.println("QDRANT SEARCH EXECUTED");
    float[] vector = embeddingService.embed(question);
    System.out.println("EMBEDDING SIZE = " + vector.length);
    QdrantSearchResponse response = qdrantService.search(vector);

    System.out.println("RESULT SIZE = " + response.result().size());

    response
        .result()
        .forEach(
            result -> {
              System.out.println("SCORE = " + result.score());

              System.out.println("PAYLOAD = " + result.payload());
            });

    if (response.result() == null || response.result().isEmpty()) {

      return new RetrievalResult("", List.of());
    }
    // TOP RESULT SCORE CHECK
    if (response.result().get(0).score() < 0.70) {

      return new RetrievalResult("", List.of());
    }
    StringBuilder context = new StringBuilder();

    List<String> sources = new ArrayList<>();

    response.result().stream()
        .limit(1)
        .forEach(
            result -> {
              Object content = result.payload().get("content");

              Object source = result.payload().get("source");

              if (content != null) {

                context.append(content).append("\n");
              }

              if (source != null) {

                sources.add(source.toString());
              }
            });

    return new RetrievalResult(context.toString(), sources);
  }
}
