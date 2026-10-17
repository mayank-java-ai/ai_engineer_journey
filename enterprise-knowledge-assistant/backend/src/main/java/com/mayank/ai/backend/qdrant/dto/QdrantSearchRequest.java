package com.mayank.ai.backend.qdrant.dto;

public record QdrantSearchRequest(float[] vector, int limit, boolean with_payload) {}
