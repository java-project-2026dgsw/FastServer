package com.dgsw.fastserver.domain.volunteer.controller;

import com.dgsw.fastserver.domain.volunteer.dto.request.VolunteerRequest;
import com.dgsw.fastserver.domain.volunteer.dto.response.VolunteerResponse;
import com.dgsw.fastserver.domain.volunteer.service.VolunteerService;
import com.dgsw.fastserver.global.data.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class VolunteerController {

    private final VolunteerService volunteerService;

    @GetMapping("/volunteer")
    public ResponseEntity<ApiResponse<VolunteerResponse>> getVolunteer(@RequestParam(name = "id") Long id) {
        log.info("id: {}", id);
        return ResponseEntity.ok(ApiResponse.ok(volunteerService.getVolunteer(id)));
    }

    @GetMapping("/volunteers")
    public ResponseEntity<ApiResponse<List<VolunteerResponse>>> getVolunteers() {
        return ResponseEntity.ok(ApiResponse.ok(volunteerService.getVolunteers()));
    }

    @PostMapping("/volunteer")
    public ResponseEntity<ApiResponse<VolunteerResponse>> postVolunteer(@RequestBody VolunteerRequest request) {
        return ResponseEntity.ok(ApiResponse.ok(volunteerService.postVolunteer(request)));
    }

    @PatchMapping("/volunteer")
    public ResponseEntity<ApiResponse<VolunteerResponse>> putVolunteer(
            @RequestParam(name = "id") Long id,
            @RequestBody VolunteerRequest request) {
        return ResponseEntity.ok(ApiResponse.ok(volunteerService.updateVolunteer(id, request)));
    }

    @DeleteMapping("/volunteer")
    public ResponseEntity<Void> deleteVolunteer(@RequestParam(name = "id") Long id) {
        volunteerService.deleteVolunteer(id);
        return ResponseEntity.noContent().build();
    }
}
