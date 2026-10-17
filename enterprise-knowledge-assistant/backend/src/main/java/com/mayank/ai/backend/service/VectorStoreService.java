package com.mayank.ai.backend.service;

import com.mayank.ai.backend.vector.VectorDocument;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class VectorStoreService {

  private final List<VectorDocument> documents = new ArrayList<>();

  public void addDocument(VectorDocument document) {

    documents.add(document);
  }

  public List<VectorDocument> getDocuments() {

    return documents;
  }
}
