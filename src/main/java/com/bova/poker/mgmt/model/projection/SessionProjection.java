package com.bova.poker.mgmt.model.projection;

import com.bova.poker.mgmt.model.enums.DeckType;

public interface SessionProjection {

    String getTitle();

    DeckType getDeckType();
}
