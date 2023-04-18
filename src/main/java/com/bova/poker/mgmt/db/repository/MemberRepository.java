package com.bova.poker.mgmt.db.repository;

import com.bova.poker.mgmt.db.entity.MemberEntity;
import com.bova.poker.mgmt.model.projection.MemberProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<MemberEntity> findByNickname(String nickname);

    List<MemberProjection> findProjectionsBySessions_Id(Long sessionsId);
}
