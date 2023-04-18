package com.bova.poker.mgmt.model.response;

import com.bova.poker.mgmt.model.enums.DeckType;
import com.bova.poker.mgmt.model.projection.MemberProjection;
import com.bova.poker.mgmt.model.projection.UserStoryProjection;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PlanningSessionResponse {

    private String title;

    private DeckType deckType;

    private List<UserStoryProjection> userStories;

    private List<MemberProjection> members;
}
