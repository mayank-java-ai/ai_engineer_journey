package com.mayank.ai.backend.ingestion;

import com.mayank.ai.backend.embedding.EmbeddingService;
import com.mayank.ai.backend.qdrant.QdrantService;
import com.mayank.ai.backend.service.VectorStoreService;
import com.mayank.ai.backend.vector.VectorDocument;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;

@Service
public class DocumentIngestor {

    private final ChunkingService chunkingService;

    private final VectorStoreService vectorStoreService;
    private final EmbeddingService embeddingService;
    private final QdrantService qdrantService;


    public DocumentIngestor(
            ChunkingService chunkingService,
            VectorStoreService vectorStoreService,
            EmbeddingService embeddingService,
            QdrantService qdrantService
    ) {

        this.chunkingService = chunkingService;
        this.vectorStoreService = vectorStoreService;
        this.embeddingService = embeddingService;
        this.qdrantService = qdrantService;
    }

    public void ingest(
            MultipartFile file
    ) {

        try {

            Path uploadDirectory =
                    Paths.get(
                            "uploaded-documents"
                    );

            Files.createDirectories(
                    uploadDirectory
            );

            Path savedFile =
                    uploadDirectory.resolve(
                            file.getOriginalFilename()
                    );

            Files.copy(
                    file.getInputStream(),
                    savedFile,
                    StandardCopyOption.REPLACE_EXISTING
            );

            String content =
                    Files.readString(
                            savedFile
                    );

            var chunks =
                    chunkingService.chunk(
                            content
                    );

            for (String chunk : chunks) {

                float[] embedding =
        embeddingService.embed(
                chunk
        );

// qdrantService.storeVector(
//         java.util.UUID.randomUUID()
//                 .toString(),
//         embedding,
//         chunk
// );

vectorStoreService.addDocument(
        new VectorDocument(
                file.getOriginalFilename(),
                chunk
        )
);
            }

            System.out.println(
                    "File Saved : " +
                    savedFile.toAbsolutePath()
            );

            System.out.println(
                    "Chunks Created : " +
                    chunks.size()
            );

        } catch (IOException e) {

            throw new RuntimeException(e);
        }
    }
}