package com.xm.agent.controller;

import com.xm.agent.dto.ChatRequest;
import com.xm.agent.dto.ChatResponse;
import com.xm.agent.service.OpenAIService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/v1/chat")
@RequiredArgsConstructor
public class ChatController {
    @Autowired
    private final OpenAIService openAIService;

    @PostMapping
    public ChatResponse chat(@RequestBody ChatRequest request) {
        String response = openAIService.chat(request.getMessage());
        return new ChatResponse(response);
    }

    @PostMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> streamChat(@RequestBody ChatRequest request) {
        return openAIService.streamChat(request.getMessage());
    }
} 