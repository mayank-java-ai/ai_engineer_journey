package com.mayank.ai.backend.qdrant;

import com.mayank.ai.backend.qdrant.dto.QdrantSearchResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class QdrantService {

  private final RestClient client;

  public QdrantService(RestClient client) {
    this.client = client;
  }

  public String health() {
    return client.get().uri("/").retrieve().body(String.class);
  }

  public void storeVector(String id, float[] vector, String content, String source) {

    System.out.println("INSIDE STORE VECTOR");
    System.out.println("CONTENT = " + content);
    System.out.println("SOURCE = " + source);
    System.out.println("VECTOR SIZE = " + vector.length);

    String vectorJson = java.util.Arrays.toString(vector);

    String body =
        """
        {
          "points": [
            {
              "id": "%s",
              "vector": %s,
              "payload": {
                "content": "%s",
                "source": "%s"
              }
            }
          ]
        }
        """
            .formatted(
                id,
                vectorJson,
                content
                    .replace("\\", "\\\\")
                    .replace("\"", "\\\"")
                    .replace("\n", " ")
                    .replace("\r", " "),
                source);

    System.out.println(body);

    client
        .put()
        .uri("/collections/policies/points")
        .header("Content-Type", "application/json")
        .body(body)
        .retrieve()
        .toBodilessEntity();

    System.out.println("Stored Vector : " + id);
  }

  public String createCollection() {
    String body =
        """
        {
          "vectors": {
            "size": 768,
            "distance": "Cosine"
          }
        }
        """;

    client.put().uri("/collections/policies").body(body).retrieve().toBodilessEntity();

    return "Collection Created";
  }

  public QdrantSearchResponse search(float[] vector) {

    String body =
        """
        {
          "vector": %s,
          "limit": 3,
          "with_payload": true
        }
        """
            .formatted(java.util.Arrays.toString(vector));

    return client
        .post()
        .uri("/collections/policies/points/search")
        .header("Content-Type", "application/json")
        .body(body)
        .retrieve()
        .body(QdrantSearchResponse.class);
  }

  public void deleteBySource(String fileName) {

    String body =
        """
        {
          "filter": {
            "must": [
              {
                "key": "source",
                "match": {
                  "value": "%s"
                }
              }
            ]
          }
        }
        """
            .formatted(fileName);

    client
        .post()
        .uri("/collections/policies/points/delete")
        .header("Content-Type", "application/json")
        .body(body)
        .retrieve()
        .toBodilessEntity();
  }
}
