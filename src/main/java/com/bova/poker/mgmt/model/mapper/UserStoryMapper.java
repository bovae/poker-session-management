package com.bova.poker.mgmt.model.mapper;

import com.bova.poker.mgmt.db.entity.SessionEntity;
import com.bova.poker.mgmt.db.entity.UserStoryEntity;
import com.bova.poker.mgmt.model.enums.UserStoryStatus;
import com.bova.poker.mgmt.model.request.UserStoryCreateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;

@Mapper(componentModel = "spring", imports = {UserStoryStatus.class, SessionEntity.class})
public abstract class UserStoryMapper {

    protected EntityManager entityManager;

    @Autowired
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Mapping(target = "status", expression = "java(UserStoryStatus.PENDING)")
    @Mapping(target = "session", expression = "java(entityManager.getReference(SessionEntity.class, sessionId))")
    public abstract UserStoryEntity convert(UserStoryCreateRequest userStoryCreateRequest, Long sessionId);
}
