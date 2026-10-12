package com.mayank.ai.backend.controller;

import com.mayank.ai.backend.dto.UploadResponse;
import com.mayank.ai.backend.ingestion.DocumentIngestor;
import com.mayank.ai.backend.qdrant.QdrantService;
import com.mayank.ai.backend.service.VectorStoreService;
import com.mayank.ai.backend.vector.VectorDocument;

import java.util.List;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/document")
@CrossOrigin("*")
public class DocumentController {

    private final DocumentIngestor documentIngestor;
    private final VectorStoreService vectorStoreService;
    private final QdrantService qdrantService;

    public DocumentController(
            DocumentIngestor documentIngestor,
            VectorStoreService vectorStoreService,
            QdrantService qdrantService
    ) {
        this.documentIngestor = documentIngestor;
        this.vectorStoreService = vectorStoreService;
        this.qdrantService = qdrantService;
    }

    @PostMapping("/upload")
    public UploadResponse upload(
            @RequestParam("file") MultipartFile file
    ) {
        documentIngestor.ingest(file);

        return new UploadResponse(
                "Document Uploaded Successfully",
                file.getOriginalFilename(),
                0
        );
    }

    @GetMapping("/documents")
    public List<VectorDocument> getDocuments() {
        return vectorStoreService.getDocuments();
    }

    @GetMapping("/qdrant-health")
    public String qdrantHealth() {
        return qdrantService.health();
    }

    @GetMapping("/create-collection")
    public String createCollection() {
        return qdrantService.createCollection();
    }

    @GetMapping("/vector-count")
public int count() {

    return vectorStoreService
            .getDocuments()
            .size();
}
}