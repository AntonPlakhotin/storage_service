package com.antonplakhotin.spring.springboot.storage_service.service;

import com.antonplakhotin.spring.springboot.storage_service.dto.PromptRes;
import com.antonplakhotin.spring.springboot.storage_service.dto.PromptRq;
import com.antonplakhotin.spring.springboot.storage_service.entity.Prompt;
import com.antonplakhotin.spring.springboot.storage_service.repository.PromptRepository;
import com.antonplakhotin.spring.springboot.storage_service.repository.PromptRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PromptServiceImpl implements PromptService {

    private final PromptRepository repository;

    @Override
    public Optional<PromptRes> getPrompt(long promptId) {
            return  repository.getPrompt(promptId)
                    .map(prompt -> PromptRes.builder()
                            .id(prompt.getId())
                            .title(prompt.getTitle())
                            .authorId(prompt.getAuthorId())
                            .description(prompt.getDescription())
                            .text(prompt.getText())
                            .build());
    }

    @Override
    public List<PromptRes> getAllPrompts() {
        List<Prompt> prompts = repository.getAllPrompts();
        List<PromptRes> promptRes = prompts.stream()
                .map(prompt -> PromptRes.builder()
                        .id(prompt.getId())
                        .title(prompt.getTitle())
                        .authorId(prompt.getAuthorId())
                        .description(prompt.getDescription())
                        .text(prompt.getText())
                        .build()).collect(Collectors.toList());
        return promptRes;
    }

    @Override
    public long createPrompt(PromptRq promptRq) {
        return repository.createPrompt(promptRq);
    }

    @Override
    public boolean editPrompt(PromptRq promptRq, long promptId) {
        return repository.editPrompt(promptRq, promptId);
    }

    @Override
    public boolean deletePrompt(long promptId) {
        return repository.deletePrompt(promptId);
    }
}
