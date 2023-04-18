package com.bova.poker.mgmt.db.repository;

import com.bova.poker.mgmt.db.entity.UserStoryEntity;
import com.bova.poker.mgmt.model.enums.UserStoryStatus;
import com.bova.poker.mgmt.model.projection.UserStoryProjection;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserStoryRepository extends JpaRepository<UserStoryEntity, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value = "10000")})
    Optional<UserStoryEntity> findByIdAndSessionId(Long id, Long sessionId);

    List<UserStoryProjection> findProjectionsBySession_IdAndStatus(Long sessionId, UserStoryStatus status);
}
