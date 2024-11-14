package com.polstat.kalender.service;

import com.polstat.kalender.dto.SelfAgendaDto;
import com.polstat.kalender.entity.Event;
import com.polstat.kalender.entity.EventType;
import com.polstat.kalender.mapper.SelfAgendaMapper;
import com.polstat.kalender.repository.EventRepository;
import com.polstat.kalender.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SelfAgendaServiceImpl implements SelfAgendaService {

    @Autowired
    private EventRepository eventRepository;

    @Override
    public SelfAgendaDto createSelfAgenda(SelfAgendaDto selfAgendaDto) {
        eventRepository.save(SelfAgendaMapper.mapToEvent(selfAgendaDto));
        return selfAgendaDto;
    }

    @Override
    public List<SelfAgendaDto> getAllSelfAgendas() {
        List<Event> events = eventRepository.findByEventType(EventType.SELF_AGENDA);
        List<SelfAgendaDto> selfAgendaDtos = events.stream()
                .map(event -> SelfAgendaMapper.mapToSelfAgendaDto(event))
                .collect(Collectors.toList());
        return selfAgendaDtos;
    }

    @Override
    public SelfAgendaDto getSelfAgendaById(Long id) {
        Optional<Event> eventOptional = eventRepository.findById(id);
        if (eventOptional.isPresent()) {
            Event event = eventOptional.get();
            if (event.getEventType() == EventType.SELF_AGENDA) {
                return SelfAgendaMapper.mapToSelfAgendaDto(event);
            }
            // Handle kesalahan jika event bukan tipe SELF_AGENDA
            throw new RuntimeException("Event dengan ID " + id + " bukan tipe SELF_AGENDA.");
        } else {
            // Handle kesalahan jika event dengan ID yang diberikan tidak ditemukan
            throw new RuntimeException("Event dengan ID " + id + " tidak ditemukan.");
        }
    }

    @Override
    public List<SelfAgendaDto> getSelfAgendasByMonth(String month) {
        int monthNumber = DateUtils.convertMonthStringToNumber(month);

        List<Event> selfAgendaEvents = eventRepository.findByBulanAndEventType(monthNumber, EventType.SELF_AGENDA);

        List<SelfAgendaDto> selfAgendaDtos = selfAgendaEvents.stream()
                .map(event -> SelfAgendaMapper.mapToSelfAgendaDto(event))
                .collect(Collectors.toList());

        return selfAgendaDtos;
    }

    @Override
    public List<SelfAgendaDto> getSelfAgendasByTarget(String target) {
        List<Event> selfAgendaEvents = eventRepository.findByTargetAndEventType(target, EventType.SELF_AGENDA);

        List<SelfAgendaDto> selfAgendaDtos = selfAgendaEvents.stream()
                .map(event -> SelfAgendaMapper.mapToSelfAgendaDto(event))
                .collect(Collectors.toList());

        return selfAgendaDtos;
    }
}
