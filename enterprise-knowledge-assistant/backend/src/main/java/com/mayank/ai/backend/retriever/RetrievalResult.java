package com.mayank.ai.backend.retriever;

import java.util.List;

public record RetrievalResult(String context, List<String> sources) {}
