package com.mayank.ai.backend.dto;

public class UploadResponse {

    private String message;

    private String fileName;

    private int chunks;

    public UploadResponse(
            String message,
            String fileName,
            int chunks
    ) {
        this.message = message;
        this.fileName = fileName;
        this.chunks = chunks;
    }

    public String getMessage() {
        return message;
    }

    public String getFileName() {
        return fileName;
    }

    public int getChunks() {
        return chunks;
    }
}