package com.bova.poker.mgmt.controller;

import com.bova.poker.mgmt.model.request.SessionCreateRequest;
import com.bova.poker.mgmt.model.response.PlanningSessionResponse;
import com.bova.poker.mgmt.service.SessionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@RestController
@RequestMapping("/sessions")
@RequiredArgsConstructor
public class SessionController {

    private final SessionService sessionService;

    @Operation(summary = "Create poker planning session",
            responses = {
                    @ApiResponse(description = "Successfully created session.", responseCode = "201"),
                    @ApiResponse(description = "Incorrect session title or deck type.", responseCode = "400")
            })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> createPokerPlanningSession(
            @Valid @RequestBody SessionCreateRequest sessionCreateRequest) {

        var sessionId = sessionService.createSession(sessionCreateRequest);
        var sessionLocation = fromCurrentRequest().path("/{sessionId}").buildAndExpand(sessionId).toUri();

        return ResponseEntity.created(sessionLocation).build();
    }

    @Operation(summary = "Enter poker planning session",
            responses = {
                    @ApiResponse(description = "Successfully entered planning session.", responseCode = "200"),
                    @ApiResponse(description = "Member is already in the planning session.", responseCode = "400")
            })
    @GetMapping("/{sessionId}")
    public PlanningSessionResponse enterPlanningSession(@PathVariable Long sessionId, @RequestParam String nickname) {
        return sessionService.enterSession(sessionId, nickname);
    }

    @Operation(summary = "Destroy poker planning session",
            responses = {
                    @ApiResponse(description = "Successfully deleted planning session.", responseCode = "204"),
                    @ApiResponse(description = "Planning session does not exist.", responseCode = "404")
            })
    @DeleteMapping("/{sessionId}")
    public ResponseEntity<Void> deletePokerPlanningSession(@PathVariable Long sessionId) {
        sessionService.deleteSession(sessionId);

        return ResponseEntity.noContent().build();
    }
}
