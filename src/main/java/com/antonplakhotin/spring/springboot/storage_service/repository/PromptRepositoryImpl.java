package com.antonplakhotin.spring.springboot.storage_service.repository;

import com.antonplakhotin.spring.springboot.storage_service.dto.PromptRq;
import com.antonplakhotin.spring.springboot.storage_service.entity.Prompt;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class PromptRepositoryImpl implements PromptRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Optional<Prompt> getPrompt(long promptId) {
        return Optional.ofNullable(entityManager.find(Prompt.class, promptId));
    }

    @Override
    @Transactional
    public List<Prompt> getAllPrompts() {
        return entityManager.createQuery("select p from Prompt p", Prompt.class).getResultList();
    }

    @Override
    @Transactional
    public long createPrompt(PromptRq promptRq) {
        Prompt prompt = new Prompt();
        prompt.setTitle(promptRq.getTitle());
        prompt.setAuthorId(promptRq.getAuthorId());
        prompt.setDescription(promptRq.getDescription());
        prompt.setText(promptRq.getText());

        entityManager.persist(prompt);

        return prompt.getId();
    }

    @Override
    @Transactional
    public boolean editPrompt(PromptRq promptRq, long promptId) {
        Prompt prompt = entityManager.find(Prompt.class, promptId);
        if (prompt != null) {
            prompt.setTitle(promptRq.getTitle());
            prompt.setAuthorId(promptRq.getAuthorId());
            prompt.setDescription(promptRq.getDescription());
            prompt.setText(promptRq.getText());
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public boolean deletePrompt(long promptId) {
        Prompt prompt = entityManager.find(Prompt.class, promptId);
        if (prompt != null) {
            entityManager.remove(prompt);
            return true;
        }
        return false;
    }
}
