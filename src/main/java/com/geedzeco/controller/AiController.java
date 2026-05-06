package com.geedzeco.controller;

import com.geedzeco.service.AiService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/ai")
public class AiController {

    private final AiService aiService;

    public AiController(AiService aiService) {
        this.aiService = aiService;
    }

    @PostMapping("/expense/from-text")
    public String test(@RequestBody Map<String, String> body) {
        return aiService.ask(body.get("text"));
    }
}
