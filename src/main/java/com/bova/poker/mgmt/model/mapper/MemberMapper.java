package com.bova.poker.mgmt.model.mapper;

import com.bova.poker.mgmt.db.entity.MemberEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MemberMapper {

    MemberEntity convert(String nickname);
}
