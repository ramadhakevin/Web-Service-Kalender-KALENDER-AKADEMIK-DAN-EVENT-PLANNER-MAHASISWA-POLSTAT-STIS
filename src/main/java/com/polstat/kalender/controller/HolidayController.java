package com.polstat.kalender.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.polstat.kalender.dto.HolidayDto;
import com.polstat.kalender.service.HolidayService;

import java.util.List;

@RestController
@RequestMapping("/holidays")
public class HolidayController {

    private final HolidayService holidayService;

    @Autowired
    public HolidayController(HolidayService holidayService) {
        this.holidayService = holidayService;
    }

    @PostMapping
    public ResponseEntity<HolidayDto> createHoliday(@RequestBody HolidayDto holidayDto) {
        HolidayDto createdHoliday = holidayService.createHoliday(holidayDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdHoliday);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HolidayDto> getHolidayById(@PathVariable Long id) {
        HolidayDto holidayDto = holidayService.getHolidayById(id);
        return ResponseEntity.ok(holidayDto);
    }

    @GetMapping
    public ResponseEntity<List<HolidayDto>> getAllHolidays() {
        List<HolidayDto> holidays = holidayService.getAllHolidays();
        return ResponseEntity.ok(holidays);
    }

    @GetMapping("/month/{month}")
    public ResponseEntity<List<HolidayDto>> getHolidaysByMonth(@PathVariable String month) {
        List<HolidayDto> holidays = holidayService.getHolidaysByMonth(month);
        return ResponseEntity.ok(holidays);
    }

    @GetMapping("/target/{target}")
    public ResponseEntity<List<HolidayDto>> getHolidaysByTarget(@PathVariable String target) {
        List<HolidayDto> holidays = holidayService.getHolidaysByTarget(target);
        return ResponseEntity.ok(holidays);
    }
}