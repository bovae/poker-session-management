package com.bova.poker.mgmt.service;

import com.bova.poker.mgmt.db.entity.SessionEntity;
import com.bova.poker.mgmt.db.repository.*;
import com.bova.poker.mgmt.model.enums.UserStoryStatus;
import com.bova.poker.mgmt.model.exception.EntityNotFoundException;
import com.bova.poker.mgmt.model.mapper.SessionMapper;
import com.bova.poker.mgmt.model.request.SessionCreateRequest;
import com.bova.poker.mgmt.model.response.PlanningSessionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Service
@RequiredArgsConstructor
public class SessionService {

    private final EntityManager entityManager;

    private final MemberService memberService;

    private final SessionMapper sessionMapper;

    private final MemberRepository memberRepository;
    private final SessionRepository sessionRepository;
    private final UserStoryRepository userStoryRepository;

    @Transactional
    public Long createSession(SessionCreateRequest sessionCreateRequest) {
        var session = sessionMapper.convert(sessionCreateRequest);

        sessionRepository.save(session);

        return session.getId();
    }

    @Transactional
    public PlanningSessionResponse enterSession(Long sessionId, String nickname) {
        memberService.joinSession(sessionId, nickname);

        var session = sessionRepository.findProjectionById(sessionId);
        var userStories = userStoryRepository.findProjectionsBySession_IdAndStatus(sessionId, UserStoryStatus.VOTING);
        var members = memberRepository.findProjectionsBySessions_Id(sessionId);

        return PlanningSessionResponse.builder()
                .title(session.getTitle())
                .deckType(session.getDeckType())
                .userStories(userStories)
                .members(members)
                .build();
    }

    @Transactional
    public void deleteSession(Long sessionId) {
        sessionRepository.findById(sessionId)
                .ifPresentOrElse(this::deleteSession, () -> {
                    throw new EntityNotFoundException();
                });
    }

    private void deleteSession(SessionEntity session) {
        for (var member : session.getMembers()) {
            member.getSessions().remove(session);
        }
        sessionRepository.delete(session);
    }
}
