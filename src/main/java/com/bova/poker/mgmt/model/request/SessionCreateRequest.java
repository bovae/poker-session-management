package com.bova.poker.mgmt.model.request;

import com.bova.poker.mgmt.model.enums.DeckType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.*;

@Data
public class SessionCreateRequest {

    @NotBlank
    @Size(min = 1, max = 32)
    private String title;

    @NotNull
    @JsonProperty("deck_type")
    private DeckType deckType;
}
