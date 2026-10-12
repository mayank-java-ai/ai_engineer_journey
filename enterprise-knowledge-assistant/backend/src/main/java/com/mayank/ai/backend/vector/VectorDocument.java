package com.mayank.ai.backend.vector;

public class VectorDocument {

    private String id;

    private String source;

    private String content;

    public VectorDocument() {
    }

    public VectorDocument(
            String source,
            String content
    ) {

        this.id =
                java.util.UUID
                        .randomUUID()
                        .toString();

        this.source = source;

        this.content = content;
    }

    public String getId() {
        return id;
    }

    public String getSource() {
        return source;
    }

    public String getContent() {
        return content;
    }
}