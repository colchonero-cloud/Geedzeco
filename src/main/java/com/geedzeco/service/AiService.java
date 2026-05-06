package com.geedzeco.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.geedzeco.model.Expense;
import com.geedzeco.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public class AiService {

    @Value("${openai.api.key}")
    private String apiKey;

    private final WebClient webClient;

    private final ExpenseRepository repository;

    public AiService(final WebClient.Builder builder, final ExpenseRepository expenseRepository) {
        this.webClient = builder.baseUrl("https://api.openai.com/v1").build();
        this.repository = expenseRepository;
    }

    public String ask(String input) {
        final var today = LocalDate.now().toString();
        final var systemPrompt = """
                                                You are a financial assistant.
                                                
                                                Extract expense data from the user input.
                                                
                                                Return ONLY valid JSON with this structure:
                                                {
                                                  "amount": number,
                                                  "category": "FOOD | TRANSPORT | ENTERTAINMENT | BILLS | OTHER",
                                                  "description": string,
                                                  "date": string (YYYY-MM-DD)
                                                }
                                                
                                                Rules:
                                                - If category is unclear, use OTHER
                                                - Normalize Polish text
                                                - Do not return anything except JSON
                                                - If date is not provided, use today's date.
                                                - Today's date is %s.
                                                """.formatted(today);
        final var response = webClient.post()
                .uri("/chat/completions")
                .header("Authorization", "Bearer " + apiKey)
                .bodyValue(Map.of(
                        "model", "gpt-4o-mini",
                        "messages", List.of(
                                Map.of(
                                        "role", "system",
                                        "content", systemPrompt
                                ),
                                Map.of(
                                        "role", "user",
                                        "content", input
                                )
                        )
                ))
                .retrieve()
                .bodyToMono(Map.class)
                .block();

        var choices = (List) response.get("choices");
        var firstChoice = (Map) choices.get(0);
        var message = (Map) firstChoice.get("message");

        String content = (String) message.get("content");

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        try {
            Expense expense = mapper.readValue(content, Expense.class);
            expense.setUserId(1L);
            repository.save(expense);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return content;
    }
}