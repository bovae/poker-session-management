package com.bova.poker.mgmt.model.mapper;

import com.bova.poker.mgmt.db.entity.SessionEntity;
import com.bova.poker.mgmt.model.request.SessionCreateRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SessionMapper {

    SessionEntity convert(SessionCreateRequest sessionCreateRequest);
}
