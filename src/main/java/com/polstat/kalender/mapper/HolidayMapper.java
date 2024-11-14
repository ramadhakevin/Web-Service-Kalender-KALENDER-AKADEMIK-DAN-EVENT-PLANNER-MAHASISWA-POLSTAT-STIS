package com.polstat.kalender.mapper;

import com.polstat.kalender.dto.HolidayDto;
import com.polstat.kalender.entity.Event;
import com.polstat.kalender.entity.EventType;
import com.polstat.kalender.util.DateUtils;

public class HolidayMapper {

    public static Event mapToEvent(HolidayDto holidayDto){

        int bulan = DateUtils.convertMonthStringToNumber(holidayDto.getBulan());
        EventType eventType = EventType.HOLIDAY;

        return Event.builder()
                .id(holidayDto.getId())
                .title(holidayDto.getTitle())
                .description(holidayDto.getDescription())
                .tanggal(holidayDto.getTanggal())
                .bulan(bulan)
                .tahun(holidayDto.getTahun())
                .target(holidayDto.getTarget())
                .eventType(eventType)
                .academicYear(holidayDto.getAcademicYear())
                .isActive(holidayDto.getIsActive())
                .createdAt(holidayDto.getCreatedAt())
                .updatedAt(holidayDto.getUpdatedAt())
                .build();
    }

    public static HolidayDto mapToHolidayDto(Event event){

        String bulan = DateUtils.convertMonthNumberToString(event.getBulan());

        return HolidayDto.builder()
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
