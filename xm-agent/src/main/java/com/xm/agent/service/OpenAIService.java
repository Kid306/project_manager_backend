package com.xm.agent.service;

import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatCompletionResult;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.service.OpenAiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OpenAIService {
    private final OpenAiService openAiService;
    private final OpenAIConfig openAIConfig;

    public String chat(String message) {
        ChatCompletionRequest request = ChatCompletionRequest.builder()
                .model(openAIConfig.getModel())
                .messages(List.of(new ChatMessage("user", message)))
                .build();

        ChatCompletionResult result = openAiService.createChatCompletion(request);
        return result.getChoices().get(0).getMessage().getContent();
    }

    public Flux<String> streamChat(String message) {
        ChatCompletionRequest request = ChatCompletionRequest.builder()
                .model(openAIConfig.getModel())
                .messages(List.of(new ChatMessage("user", message)))
                .stream(true)
                .build();

        return Flux.create(sink -> {
            openAiService.streamChatCompletion(request)
                    .doOnNext(chunk -> {
                        String content = chunk.getChoices().get(0).getMessage().getContent();
                        if (content != null) {
                            sink.next(content);
                        }
                    })
                    .doOnComplete(sink::complete)
                    .doOnError(sink::error)
                    .subscribe();
        });
    }
} 