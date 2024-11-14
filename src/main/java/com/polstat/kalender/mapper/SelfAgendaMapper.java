package com.polstat.kalender.mapper;

import com.polstat.kalender.dto.SelfAgendaDto;
import com.polstat.kalender.entity.Event;
import com.polstat.kalender.entity.EventType;
import com.polstat.kalender.util.DateUtils;

public class SelfAgendaMapper {

    public static Event mapToEvent(SelfAgendaDto selfAgendaDto){

        int bulan = DateUtils.convertMonthStringToNumber(selfAgendaDto.getBulan());
        EventType eventType = EventType.SELF_AGENDA;

        return Event.builder()
                .id(selfAgendaDto.getId())
                .title(selfAgendaDto.getTitle())
                .description(selfAgendaDto.getDescription())
                .tanggal(selfAgendaDto.getTanggal())
                .bulan(bulan)
                .tahun(selfAgendaDto.getTahun())
                .target(selfAgendaDto.getTarget())
                .eventType(eventType)
                .academicYear(selfAgendaDto.getAcademicYear())
                .isActive(selfAgendaDto.getIsActive())
                .createdAt(selfAgendaDto.getCreatedAt())
                .updatedAt(selfAgendaDto.getUpdatedAt())
                .build();
    }

    public static SelfAgendaDto mapToSelfAgendaDto(Event event){

        String bulan = DateUtils.convertMonthNumberToString(event.getBulan());

        return SelfAgendaDto.builder()
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
