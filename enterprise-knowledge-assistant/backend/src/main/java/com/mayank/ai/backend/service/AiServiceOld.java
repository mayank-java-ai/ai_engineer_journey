// package com.mayank.ai.backend.service;

// import dev.langchain4j.model.ollama.OllamaChatModel;
// import org.springframework.stereotype.Service;

// @Service
// public class AiServiceOld {

//     private final OllamaChatModel model;
//     private final DocumentService documentService;

//     public AiServiceOld(DocumentService documentService) {
//         this.documentService =
//                 documentService;
//         this.model =
//                 OllamaChatModel.builder()
//                         .baseUrl("http://localhost:11434")
//                         .modelName("llama3.2")
//                         .build();
//     }

//     public String ask(String question) {
//         String context =
//                 documentService
//                         .getContext();

//         String prompt =
//                 """
//                 Answer the question using only the context below.

//                 Context:
//                 %s

//                 Question:
//                 %s
//                 """
//                 .formatted(
//                         context,
//                         question
//                 );
//         return model.chat(prompt);
//     }
// }
