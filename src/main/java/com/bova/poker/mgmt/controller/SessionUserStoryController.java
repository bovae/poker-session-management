package com.bova.poker.mgmt.controller;

import com.bova.poker.mgmt.model.request.UserStoryCreateRequest;
import com.bova.poker.mgmt.service.UserStoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@RestController
@RequestMapping("/sessions/{sessionId}/stories")
@RequiredArgsConstructor
public class SessionUserStoryController {

    private final UserStoryService userStoryService;

    @Operation(summary = "Create user story",
            responses = {
                    @ApiResponse(description = "Successfully created user story.", responseCode = "201"),
                    @ApiResponse(description = "Incorrect session id or user story description.", responseCode = "400")
            })
    @PostMapping
    public ResponseEntity<Void> createUserStory(
            @PathVariable Long sessionId,
            @Valid @RequestBody UserStoryCreateRequest userStoryCreateRequest) {

        var userStoryId = userStoryService.createUserStory(userStoryCreateRequest, sessionId);
        var userStoryLocation = fromCurrentRequest().path("/{userStoryId}").buildAndExpand(userStoryId).toUri();

        return ResponseEntity.created(userStoryLocation).build();
    }

    @Operation(summary = "Delete user story",
            responses = {
                    @ApiResponse(description = "Successfully deleted user story.", responseCode = "204"),
                    @ApiResponse(description = "User story does not exist.", responseCode = "404")
            })
    @DeleteMapping("/{userStoryId}")
    public ResponseEntity<Void> deleteUserStory(@PathVariable Long sessionId, @PathVariable Long userStoryId) {
        userStoryService.deleteUserStory(userStoryId, sessionId);

        return ResponseEntity.noContent().build();
    }
}
