package com.mayank.ai.backend.ingestion;

import com.mayank.ai.backend.embedding.OllamaEmbeddingService;
import com.mayank.ai.backend.event.DocumentUploadedEvent;
import com.mayank.ai.backend.qdrant.QdrantService;
import com.mayank.ai.backend.service.VectorStoreService;
import com.mayank.ai.backend.vector.VectorDocument;
import java.io.IOException;
import java.nio.file.*;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class DocumentIngestor {

  private final ChunkingService chunkingService;

  private final VectorStoreService vectorStoreService;
  private final OllamaEmbeddingService ollamaEmbeddingService;
  private final QdrantService qdrantService;
  private final ApplicationEventPublisher publisher;

  public DocumentIngestor(
      ChunkingService chunkingService,
      VectorStoreService vectorStoreService,
      OllamaEmbeddingService ollamaEmbeddingService,
      QdrantService qdrantService,
      ApplicationEventPublisher publisher) {

    this.chunkingService = chunkingService;
    this.vectorStoreService = vectorStoreService;
    this.ollamaEmbeddingService = ollamaEmbeddingService;
    this.qdrantService = qdrantService;
    this.publisher = publisher;
  }

  // @CacheEvict(value = "retrieval-cache", allEntries = true)
  // public void ingest(MultipartFile file) {

  //   try {

  //     Path uploadDirectory = Paths.get("uploaded-documents");

  //     Files.createDirectories(uploadDirectory);

  //     Path savedFile = uploadDirectory.resolve(file.getOriginalFilename());

  //     Files.copy(file.getInputStream(), savedFile, StandardCopyOption.REPLACE_EXISTING);

  //     String content = Files.readString(savedFile);

  //     var chunks = chunkingService.chunk(content);

  //     for (String chunk : chunks) {

  //       float[] embedding = ollamaEmbeddingService.embed(chunk);

  //       qdrantService.storeVector(
  //           java.util.UUID.randomUUID().toString(), embedding, chunk, file.getOriginalFilename());

  //       vectorStoreService.addDocument(new VectorDocument(file.getOriginalFilename(), chunk));
  //     }

  //     System.out.println("File Saved : " + savedFile.toAbsolutePath());

  //     System.out.println("Chunks Created : " + chunks.size());
  //     publisher.publishEvent(new DocumentUploadedEvent(file.getOriginalFilename()));

  //   } catch (IOException e) {

  //     throw new RuntimeException(e);
  //   }
  // }

  @CacheEvict(value = "retrieval-cache", allEntries = true)
  public void deleteDocument(String fileName) {

    try {

      Path filePath = Paths.get("uploaded-documents", fileName);

      Files.deleteIfExists(filePath);

      qdrantService.deleteBySource(fileName);

      System.out.println("Deleted : " + fileName);

    } catch (Exception e) {

      throw new RuntimeException(e);
    }
  }

  @CacheEvict(value = "retrieval-cache", allEntries = true)
  public void processFile(
        String filePath,
        String fileName
) {

    try {

        Path path =
                Paths.get(filePath);

        String content =
                Files.readString(path);

        var chunks =
                chunkingService.chunk(
                        content
                );

        for(String chunk : chunks) {

            float[] embedding =
                    ollamaEmbeddingService.embed(
                            chunk
                    );

            qdrantService.storeVector(
                    java.util.UUID.randomUUID()
                            .toString(),
                    embedding,
                    chunk,
                    fileName
            );

            vectorStoreService.addDocument(
                    new VectorDocument(
                            fileName,
                            chunk
                    )
            );
        }

        System.out.println(
                "File Saved : " + path
        );

        System.out.println(
                "Chunks Created : "
                + chunks.size()
        );

        publisher.publishEvent(
                new DocumentUploadedEvent(
                        fileName
                )
        );

    } catch(Exception e) {

        throw new RuntimeException(
                e
        );
    }
}
}
