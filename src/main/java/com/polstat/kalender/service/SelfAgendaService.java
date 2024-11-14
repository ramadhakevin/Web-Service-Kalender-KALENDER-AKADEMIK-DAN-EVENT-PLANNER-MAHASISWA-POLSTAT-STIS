package com.polstat.kalender.service;

import com.polstat.kalender.dto.SelfAgendaDto;
import java.util.List;

public interface SelfAgendaService {
    SelfAgendaDto createSelfAgenda(SelfAgendaDto SelfAgendaDto);
    List<SelfAgendaDto> getAllSelfAgendas();
    SelfAgendaDto getSelfAgendaById(Long id);
    List<SelfAgendaDto> getSelfAgendasByMonth(String month);
    List<SelfAgendaDto> getSelfAgendasByTarget(String target);
}