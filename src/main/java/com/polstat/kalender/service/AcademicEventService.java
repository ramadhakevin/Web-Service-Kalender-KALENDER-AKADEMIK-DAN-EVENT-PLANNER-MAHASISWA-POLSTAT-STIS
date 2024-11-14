package com.polstat.kalender.service;

import java.util.List;
import com.polstat.kalender.dto.AcademicEventDto;

public interface AcademicEventService {
    AcademicEventDto createAcademicEvent(AcademicEventDto academicEventDto);
    List<AcademicEventDto> getAllAcademicEvents();
    AcademicEventDto getAcademicEventById(Long id);
    List<AcademicEventDto> getAcademicEventsByMonth(String month);
    List<AcademicEventDto> getAcademicEventsByTarget(String target);
}