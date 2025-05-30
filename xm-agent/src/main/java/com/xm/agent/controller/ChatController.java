package com.xm.agent.controller;

import com.xm.agent.service.OpenAIService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/v1/chat")
@RequiredArgsConstructor
public class ChatController {
    private final OpenAIService openAIService;

    @PostMapping
    public String chat(@RequestBody String message) {
        return openAIService.chat(message);
    }

    @PostMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> streamChat(@RequestBody String message) {
        return openAIService.streamChat(message);
    }
} 