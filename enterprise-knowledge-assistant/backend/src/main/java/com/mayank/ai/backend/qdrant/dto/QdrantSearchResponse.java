package com.mayank.ai.backend.qdrant.dto;

import java.util.List;
import java.util.Map;

public record QdrantSearchResponse(List<Result> result) {

  public record Result(String id, double score, Map<String, Object> payload) {}
}
