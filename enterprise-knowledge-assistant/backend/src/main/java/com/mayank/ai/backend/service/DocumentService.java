package com.mayank.ai.backend.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.springframework.stereotype.Service;

@Service
public class DocumentService {

  public String getContext() {

    try {

      String leavePolicy = Files.readString(Path.of("documents/leave-policy.txt"));

      String travelPolicy = Files.readString(Path.of("documents/travel-policy.txt"));

      String wfhPolicy = Files.readString(Path.of("documents/wfh-policy.txt"));

      return leavePolicy + "\n" + travelPolicy + "\n" + wfhPolicy;

    } catch (IOException e) {

      throw new RuntimeException(e);
    }
  }
}
