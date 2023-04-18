package com.bova.poker.mgmt.service;

import com.bova.poker.mgmt.db.entity.MemberEntity;
import com.bova.poker.mgmt.db.entity.SessionEntity;
import com.bova.poker.mgmt.db.repository.MemberRepository;
import com.bova.poker.mgmt.model.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberMapper memberMapper;
    private final EntityManager entityManager;
    private final MemberRepository memberRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void joinSession(Long sessionId, String nickname) {
        var member = createMemberIfNotExists(nickname);
        var session = entityManager.getReference(SessionEntity.class, sessionId);
        member.getSessions().add(session);
    }

    private MemberEntity createMemberIfNotExists(String nickname) {
        return memberRepository.findByNickname(nickname)
                .orElseGet(() -> createMember(nickname));
    }

    private MemberEntity createMember(String nickname) {
        var member = memberMapper.convert(nickname);
        return memberRepository.save(member);
    }
}
