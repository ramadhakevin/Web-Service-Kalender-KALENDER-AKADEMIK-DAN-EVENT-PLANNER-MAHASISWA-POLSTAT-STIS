package com.polstat.kalender.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.polstat.kalender.dto.AcademicEventDto;
import com.polstat.kalender.service.AcademicEventService;

import java.util.List;

@RestController
@RequestMapping("/academic-events")
public class AcademicEventController {

    private final AcademicEventService academicEventService;

    @Autowired
    public AcademicEventController(AcademicEventService academicEventService) {
        this.academicEventService = academicEventService;
    }

    @PostMapping
    public ResponseEntity<AcademicEventDto> createAcademicEvent(@RequestBody AcademicEventDto academicEventDto) {
        AcademicEventDto createdAcademicEvent = academicEventService.createAcademicEvent(academicEventDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAcademicEvent);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AcademicEventDto> getAcademicEventById(@PathVariable Long id) {
        AcademicEventDto academicEventDto = academicEventService.getAcademicEventById(id);
        return ResponseEntity.ok(academicEventDto);
    }

    @GetMapping
    public ResponseEntity<List<AcademicEventDto>> getAllAcademicEvents() {
        List<AcademicEventDto> academicEvents = academicEventService.getAllAcademicEvents();
        return ResponseEntity.ok(academicEvents);
    }

    @GetMapping("/month/{month}")
    public ResponseEntity<List<AcademicEventDto>> getAcademicEventsByMonth(@PathVariable String month) {
        List<AcademicEventDto> academicEvents = academicEventService.getAcademicEventsByMonth(month);
        return ResponseEntity.ok(academicEvents);
    }

    @GetMapping("/target/{target}")
    public ResponseEntity<List<AcademicEventDto>> getAcademicEventsByTarget(@PathVariable String target) {
        List<AcademicEventDto> academicEvents = academicEventService.getAcademicEventsByTarget(target);
        return ResponseEntity.ok(academicEvents);
    }
}
