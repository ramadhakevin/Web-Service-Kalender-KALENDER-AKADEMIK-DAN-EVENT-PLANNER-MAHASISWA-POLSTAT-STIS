package com.polstat.kalender.service;

import com.polstat.kalender.dto.HolidayDto;
import java.util.List;

public interface HolidayService {
    HolidayDto createHoliday(HolidayDto holidayDto);
    List<HolidayDto> getAllHolidays();
    HolidayDto getHolidayById(Long id);
    List<HolidayDto> getHolidaysByMonth(String month);
    List<HolidayDto> getHolidaysByTarget(String target);
}
