
package com.mayank.ai.backend.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mayank.ai.backend.ingestion.DocumentIngestor;

@Service
public class DocumentAsyncService {

    private final DocumentIngestor documentIngestor;

    public DocumentAsyncService(
            DocumentIngestor documentIngestor
    ) {

        this.documentIngestor =
                documentIngestor;
    }

    @Async("documentExecutor")
public void processDocument(
        String filePath,
        String fileName
) {

    documentIngestor.processFile(filePath, fileName
    );
}
}