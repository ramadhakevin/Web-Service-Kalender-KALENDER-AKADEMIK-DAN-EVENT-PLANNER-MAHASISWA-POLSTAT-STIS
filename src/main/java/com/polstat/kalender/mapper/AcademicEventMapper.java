package com.polstat.kalender.mapper;

import com.polstat.kalender.dto.AcademicEventDto;
import com.polstat.kalender.entity.Event;
import com.polstat.kalender.entity.EventType;
import com.polstat.kalender.util.DateUtils;

public class AcademicEventMapper {

    public static Event mapToEvent(AcademicEventDto academicEventDto){

        int bulan = DateUtils.convertMonthStringToNumber(academicEventDto.getBulan());
        EventType eventType = EventType.ACADEMIC_EVENT;

        return Event.builder()
                .id(academicEventDto.getId())
                .title(academicEventDto.getTitle())
                .description(academicEventDto.getDescription())
                .tanggal(academicEventDto.getTanggal())
                .bulan(bulan)
                .tahun(academicEventDto.getTahun())
                .target(academicEventDto.getTarget())
                .eventType(eventType)
                .academicYear(academicEventDto.getAcademicYear())
                .isActive(academicEventDto.getIsActive())
                .createdAt(academicEventDto.getCreatedAt())
                .updatedAt(academicEventDto.getUpdatedAt())
                .build();
    }

    public static AcademicEventDto mapToAcademicEventDto(Event event){

        String bulan = DateUtils.convertMonthNumberToString(event.getBulan());

        return AcademicEventDto.builder()
                .id(event.getId())
                .title(event.getTitle())
                .description(event.getDescription())
                .tanggal(event.getTanggal())
                .bulan(bulan)
                .tahun(event.getTahun())
                .target(event.getTarget())
                .eventType(event.getEventType())
                .academicYear(event.getAcademicYear())
                .isActive(event.getIsActive())
                .createdAt(event.getCreatedAt())
                .updatedAt(event.getUpdatedAt())
                .build();
    }
}
