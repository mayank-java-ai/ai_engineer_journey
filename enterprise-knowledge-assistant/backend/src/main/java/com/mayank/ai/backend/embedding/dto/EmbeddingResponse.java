package com.mayank.ai.backend.embedding.dto;

import java.util.List;

public record EmbeddingResponse(List<Float> embedding) {}
