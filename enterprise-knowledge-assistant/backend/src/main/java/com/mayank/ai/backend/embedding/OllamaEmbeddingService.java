package com.mayank.ai.backend.embedding;

import com.mayank.ai.backend.embedding.dto.EmbeddingRequest;
import com.mayank.ai.backend.embedding.dto.EmbeddingResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class OllamaEmbeddingService {

  private final RestClient client = RestClient.builder().baseUrl("http://localhost:11434").build();

  public float[] embed(String text) {

    EmbeddingResponse response =
        client
            .post()
            .uri("/api/embeddings")
            .body(new EmbeddingRequest("nomic-embed-text", text))
            .retrieve()
            .body(EmbeddingResponse.class);

    float[] vector = new float[response.embedding().size()];

    for (int i = 0; i < response.embedding().size(); i++) {

      vector[i] = response.embedding().get(i);
    }

    return vector;
  }
}
