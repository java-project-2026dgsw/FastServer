package com.dgsw.fastserver.domain.volunteer.controller;

import com.dgsw.fastserver.domain.volunteer.dto.response.VolunteerResponse;
import com.dgsw.fastserver.domain.volunteer.service.VolunteerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class VolunteerController {

    VolunteerService volunteerService;

    @GetMapping("/volunteer")
    public VolunteerResponse getVolunteer(@RequestParam(name = "id") Long id) {
        log.info("id: {}", id);
        return volunteerService.getVolunteer(id);
    }

    @PostMapping("/volunteer")
    public List<VolunteerResponse> postVolunteer() {
        return volunteerService.getVolunteers();
    }

    @PutMapping("/volunteer")
    public VolunteerResponse putVolunteer(@RequestParam(name = "id") Long id) {
        return volunteerService.getVolunteer(id);
    }

    @DeleteMapping("/volunteer")
    public VolunteerResponse deleteVolunteer(@RequestParam(name = "id") Long id) {
        return volunteerService.getVolunteer(id);
    }
}
