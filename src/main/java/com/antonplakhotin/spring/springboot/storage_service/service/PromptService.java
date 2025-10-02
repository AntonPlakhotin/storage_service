package com.antonplakhotin.spring.springboot.storage_service.service;

import com.antonplakhotin.spring.springboot.storage_service.dto.PromptRes;
import com.antonplakhotin.spring.springboot.storage_service.dto.PromptRq;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface PromptService {

    public Optional<PromptRes> getPrompt(long promptId);

    public List<PromptRes> getAllPrompts();

    public long createPrompt(@RequestBody PromptRq promptRq);

    public boolean editPrompt(@RequestBody PromptRq promptRq, @PathVariable long promptId);

    public boolean deletePrompt(long promptId);
}
