package com.bova.poker.mgmt.db.repository;

import com.bova.poker.mgmt.db.entity.SessionEntity;
import com.bova.poker.mgmt.model.projection.SessionProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends JpaRepository<SessionEntity, Long> {

    SessionProjection findProjectionById(@Param("id") Long id);
}
