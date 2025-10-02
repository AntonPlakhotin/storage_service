package com.antonplakhotin.spring.springboot.storage_service.controller;

import com.antonplakhotin.spring.springboot.storage_service.dto.PromptRes;
import com.antonplakhotin.spring.springboot.storage_service.dto.PromptRq;
import com.antonplakhotin.spring.springboot.storage_service.service.PromptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class PromptController {

    @Autowired
    private PromptService promptService;

    @GetMapping("/testPrompt")
    public String test() {
        return "123";
    }

    @GetMapping("/prompt/{promptId}")
    public ResponseEntity<PromptRes> getPrompt(@PathVariable long promptId) {
        Optional<PromptRes> prompt = promptService.getPrompt(promptId);
        if (prompt.isPresent()) {
            return ResponseEntity.ok(prompt.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/prompts")
    public ResponseEntity<List<PromptRes>> getAllPrompts() {
        List<PromptRes> prompts = promptService.getAllPrompts();
        if (prompts != null) {
            return ResponseEntity.ok(prompts);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/prompt")
    public ResponseEntity<Long> createPrompt(@RequestBody PromptRq promptRq) {
        long result = promptService.createPrompt(promptRq);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/prompt/{promptId}")
    public HttpStatus editPrompt(@RequestBody PromptRq promptRq, @PathVariable long promptId) {
        boolean result = promptService.editPrompt(promptRq, promptId);
        if (result) {
            return HttpStatus.OK;
        } else {
            return HttpStatus.BAD_REQUEST;
        }
    }

    @DeleteMapping("/prompt/{promptId}")
    public HttpStatus deletePrompt(@PathVariable long promptId) {
        boolean result = promptService.deletePrompt(promptId);

        if (result) {
            return HttpStatus.NO_CONTENT;
        } else {
            return HttpStatus.NOT_FOUND;
        }
    }
}
