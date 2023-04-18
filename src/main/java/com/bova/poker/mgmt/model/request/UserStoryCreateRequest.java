package com.bova.poker.mgmt.model.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class UserStoryCreateRequest {

    @NotBlank
    @Size(min = 1, max = 256)
    private String description;
}
