package com.antonplakhotin.spring.springboot.storage_service.repository;

import com.antonplakhotin.spring.springboot.storage_service.dto.PromptRes;
import com.antonplakhotin.spring.springboot.storage_service.dto.PromptRq;
import com.antonplakhotin.spring.springboot.storage_service.entity.Prompt;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface PromptRepository {

    public Optional<Prompt> getPrompt(long promptId);

    public List<Prompt> getAllPrompts();

    public long createPrompt(@RequestBody PromptRq promptRq);

    public boolean editPrompt(@RequestBody PromptRq promptRq, @PathVariable long promptId);

    public boolean deletePrompt(long promptId);
}
