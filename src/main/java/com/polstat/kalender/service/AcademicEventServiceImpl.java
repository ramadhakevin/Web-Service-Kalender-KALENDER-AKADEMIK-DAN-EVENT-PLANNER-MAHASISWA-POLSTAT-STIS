package com.polstat.kalender.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.polstat.kalender.dto.AcademicEventDto;
import com.polstat.kalender.entity.Event;
import com.polstat.kalender.entity.EventType;
import com.polstat.kalender.mapper.AcademicEventMapper;
import com.polstat.kalender.repository.EventRepository;
import com.polstat.kalender.util.DateUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AcademicEventServiceImpl implements AcademicEventService {

    @Autowired
    private EventRepository eventRepository;

    @Override
    public AcademicEventDto createAcademicEvent(AcademicEventDto academicEventDto) {
        eventRepository.save(AcademicEventMapper.mapToEvent(academicEventDto));
        System.out.println("evenType : " + academicEventDto.getEventType());
        return academicEventDto;
    }

    @Override
    public List<AcademicEventDto> getAllAcademicEvents() {
        List<Event> events = eventRepository.findByEventType(EventType.ACADEMIC_EVENT);
        List<AcademicEventDto> academicEventDtos = events.stream()
                .map(event -> AcademicEventMapper.mapToAcademicEventDto(event))
                .collect(Collectors.toList());
        return academicEventDtos;
    }

    @Override
    public AcademicEventDto getAcademicEventById(Long id) {
        Optional<Event> eventOptional = eventRepository.findById(id);
        if (eventOptional.isPresent()) {
            Event event = eventOptional.get();
            if (event.getEventType() == EventType.ACADEMIC_EVENT) {
                return AcademicEventMapper.mapToAcademicEventDto(event);
            }
            // Handle kesalahan jika event bukan tipe HOLIDAY
            throw new RuntimeException("Event dengan ID " + id + " bukan tipe ACADEMIC_EVENT.");
        } else {
            // Handle kesalahan jika event dengan ID yang diberikan tidak ditemukan
            throw new RuntimeException("Event dengan ID " + id + " tidak ditemukan.");
        }
    }

    @Override
    public List<AcademicEventDto> getAcademicEventsByMonth(String month) {
        // Ubah nama bulan dalam bentuk string menjadi angka
        int monthNumber = DateUtils.convertMonthStringToNumber(month);

        List<Event> academicEvents = eventRepository.findByBulanAndEventType(monthNumber, EventType.ACADEMIC_EVENT);

        List<AcademicEventDto> academicEventDtos = academicEvents.stream()
                .map(event -> AcademicEventMapper.mapToAcademicEventDto(event))
                .collect(Collectors.toList());

        return academicEventDtos;
    }

    @Override
    public List<AcademicEventDto> getAcademicEventsByTarget(String target) {
        // Menggunakan EventRepository untuk mencari data berdasarkan target dan event type "ACADEMIC_EVENT"
        List<Event> academicEvents = eventRepository.findByTargetAndEventType(target, EventType.ACADEMIC_EVENT);

        // Menggunakan EventMapper untuk mengubah entity Event menjadi DTO HolidayDto
        List<AcademicEventDto> academicEventDtos = academicEvents.stream()
                .map(event -> AcademicEventMapper.mapToAcademicEventDto(event))
                .collect(Collectors.toList());

        return academicEventDtos;
    }
}
