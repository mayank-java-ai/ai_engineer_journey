package com.mayank.ai.backend.event;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class NotificationListener {

  @EventListener
  public void handleUpload(DocumentUploadedEvent event) {

    System.out.println("NOTIFICATION => " + event.fileName() + " uploaded successfully");
  }
}
  