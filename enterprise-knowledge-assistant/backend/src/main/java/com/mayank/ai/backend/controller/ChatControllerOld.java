// package com.mayank.ai.backend.controller;

// import com.mayank.ai.backend.dto.ChatRequest;
// import com.mayank.ai.backend.dto.ChatResponse;
// import com.mayank.ai.backend.service.AiServiceOld;
// import org.springframework.web.bind.annotation.*;

// @RestController
// @RequestMapping("/api/chat")
// @CrossOrigin("*")
// public class ChatControllerOld {

//     private final AiServiceOld aiService;

//     public ChatControllerOld(
//             AiServiceOld aiService
//     ) {
//         this.aiService = aiService;
//     }

//     @PostMapping
//     public ChatResponse chat(
//             @RequestBody ChatRequest request
//     ) {

//         String answer =
//                 aiService.ask(
//                         request.getQuestion()
//                 );

//         return new ChatResponse(answer);
//     }
// }
