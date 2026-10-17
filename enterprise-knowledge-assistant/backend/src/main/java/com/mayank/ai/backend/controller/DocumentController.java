package com.mayank.ai.backend.controller;

import com.mayank.ai.backend.ingestion.DocumentIngestor;
import com.mayank.ai.backend.qdrant.QdrantService;
import com.mayank.ai.backend.service.VectorStoreService;
import com.mayank.ai.backend.service.DocumentAsyncService;
import com.mayank.ai.backend.vector.VectorDocument;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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
  private final DocumentAsyncService documentAsyncService;

  public DocumentController(
      DocumentIngestor documentIngestor,
      VectorStoreService vectorStoreService,
      QdrantService qdrantService,
      DocumentAsyncService documentAsyncService) {
    this.documentIngestor = documentIngestor;
    this.vectorStoreService = vectorStoreService;
    this.qdrantService = qdrantService;
    this.documentAsyncService = documentAsyncService;
  }

@PostMapping("/upload")
public String upload(
        @RequestParam("files")
        MultipartFile[] files
) {

    try {

        for (MultipartFile file : files) {

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

            documentAsyncService
                    .processDocument(
                            savedFile.toString(),
                            file.getOriginalFilename()
                    );
        }

        return files.length +
                " documents accepted for processing";

    } catch (IOException e) {

        throw new RuntimeException(
                e
        );
    }
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

    return vectorStoreService.getDocuments().size();
  }

  @DeleteMapping("/{fileName}")
  public String deleteDocument(@PathVariable String fileName) {

    documentIngestor.deleteDocument(fileName);

    return "Deleted : " + fileName;
  }

  @GetMapping("/files")
  public List<String> getFiles(
      @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) {

    try {

      Path uploadDirectory = Paths.get("uploaded-documents");

      List<String> files =
          Files.list(uploadDirectory).map(path -> path.getFileName().toString()).toList();

      int start = page * size;

      int end = Math.min(start + size, files.size());

      if (start >= files.size()) {

        return List.of();
      }

      return files.subList(start, end);

    } catch (Exception e) {

      throw new RuntimeException(e);
    }
  }
}
