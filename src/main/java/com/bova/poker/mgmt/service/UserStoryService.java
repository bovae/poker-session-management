package com.bova.poker.mgmt.service;

import com.bova.poker.mgmt.db.repository.UserStoryRepository;
import com.bova.poker.mgmt.model.exception.EntityNotFoundException;
import com.bova.poker.mgmt.model.mapper.UserStoryMapper;
import com.bova.poker.mgmt.model.request.UserStoryCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserStoryService {

    private final UserStoryMapper userStoryMapper;
    private final UserStoryRepository userStoryRepository;

    @Transactional
    public Long createUserStory(UserStoryCreateRequest userStoryCreateRequest, Long sessionId) {
        var userStory = userStoryMapper.convert(userStoryCreateRequest, sessionId);

        userStoryRepository.save(userStory);

        return userStory.getId();
    }

    @Transactional
    public void deleteUserStory(Long userStoryId, Long sessionId) {
        userStoryRepository.findByIdAndSessionId(userStoryId, sessionId)
                .ifPresentOrElse(userStory -> userStoryRepository.deleteById(userStoryId), () -> {
                    throw new EntityNotFoundException();
                });
    }
}
