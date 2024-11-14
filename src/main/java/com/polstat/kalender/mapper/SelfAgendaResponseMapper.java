package com.polstat.kalender.mapper;

import com.polstat.kalender.dto.SelfAgendaDto;
import com.polstat.kalender.entity.Event;
import com.polstat.kalender.entity.EventType;
import com.polstat.kalender.util.DateUtils;
import response.SelfAgendaResponse;

public class SelfAgendaResponseMapper {

    public static SelfAgendaResponse mapToResponse(SelfAgendaDto selfAgendaDto){

        return SelfAgendaResponse.builder()
                .id(selfAgendaDto.getId())
                .title(selfAgendaDto.getTitle())
                .description(selfAgendaDto.getDescription())
                .tanggal(selfAgendaDto.getTanggal())
                .bulan(selfAgendaDto.getBulan())
                .tahun(selfAgendaDto.getTahun())
                .build();
    }
}
