package com.mayank.ai.backend.qdrant;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class QdrantService {

    private final RestClient client;

    public QdrantService(
            RestClient client
    ) {
        this.client = client;
    }

    public String health() {
        return client.get()
                .uri("/")
                .retrieve()
                .body(String.class);
    }

    public void storeVector(
        String id,
        float[] vector,
        String content
) {

    String vectorJson =
            java.util.Arrays.toString(
                    vector
            );

    String body =
            """
            {
              "points": [
                {
                  "id": "%s",
                  "vector": %s,
                  "payload": {
                    "content": "%s"
                  }
                }
              ]
            }
            """
            .formatted(
                    id,
                    vectorJson,
                    content.replace("\"", "'")
            );

    client.put()
            .uri(
                    "/collections/policies/points"
            )
            .header(
                    "Content-Type",
                    "application/json"
            )
            .body(body)
            .retrieve()
            .toBodilessEntity();

    System.out.println(
            "Stored Vector : " + id
    );
}

    public String createCollection() {
        String body =
                """
                {
                  "vectors": {
                    "size": 384,
                    "distance": "Cosine"
                  }
                }
                """;

        client.put()
                .uri("/collections/policies")
                .body(body)
                .retrieve()
                .toBodilessEntity();

        return "Collection Created";
    }
}