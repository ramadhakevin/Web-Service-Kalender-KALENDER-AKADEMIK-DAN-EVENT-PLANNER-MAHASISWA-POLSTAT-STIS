package com.polstat.kalender.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.polstat.kalender.dto.HolidayDto;
import com.polstat.kalender.entity.Event;
import com.polstat.kalender.entity.EventType;
import com.polstat.kalender.mapper.HolidayMapper;
import com.polstat.kalender.repository.EventRepository;
import com.polstat.kalender.util.DateUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HolidayServiceImpl implements HolidayService {

    @Autowired
    private EventRepository eventRepository;

    @Override
    public HolidayDto createHoliday(HolidayDto holidayDto) {
        eventRepository.save(HolidayMapper.mapToEvent(holidayDto));
        return holidayDto;
    }

    @Override
    public List<HolidayDto> getAllHolidays() {
        List<Event> events = eventRepository.findByEventType(EventType.HOLIDAY);
        List<HolidayDto> holidayDtos = events.stream()
                .map(event -> HolidayMapper.mapToHolidayDto(event))
                .collect(Collectors.toList());
        return holidayDtos;
    }

    @Override
    public HolidayDto getHolidayById(Long id) {
        Optional<Event> eventOptional = eventRepository.findById(id);
        if (eventOptional.isPresent()) {
            Event event = eventOptional.get();
            if (event.getEventType() == EventType.HOLIDAY) {
                return HolidayMapper.mapToHolidayDto(event);
            }
            // Handle kesalahan jika event bukan tipe HOLIDAY
            throw new RuntimeException("Event dengan ID " + id + " bukan tipe HOLIDAY.");
        } else {
            // Handle kesalahan jika event dengan ID yang diberikan tidak ditemukan
            throw new RuntimeException("Event dengan ID " + id + " tidak ditemukan.");
        }
    }

    @Override
    public List<HolidayDto> getHolidaysByMonth(String month) {
        // Ubah nama bulan dalam bentuk string menjadi angka
        int monthNumber = DateUtils.convertMonthStringToNumber(month);

        List<Event> holidayEvents = eventRepository.findByBulanAndEventType(monthNumber, EventType.HOLIDAY);

        List<HolidayDto> holidayDtos = holidayEvents.stream()
                .map(event -> HolidayMapper.mapToHolidayDto(event))
                .collect(Collectors.toList());

        return holidayDtos;
    }

    @Override
    public List<HolidayDto> getHolidaysByTarget(String target) {
        // Menggunakan EventRepository untuk mencari data berdasarkan target dan event type "HOLIDAY"
        List<Event> holidayEvents = eventRepository.findByTargetAndEventType(target, EventType.HOLIDAY);

        // Menggunakan EventMapper untuk mengubah entity Event menjadi DTO HolidayDto
        List<HolidayDto> holidayDtos = holidayEvents.stream()
                .map(HolidayMapper::mapToHolidayDto)
                .collect(Collectors.toList());

        return holidayDtos;
    }
}
