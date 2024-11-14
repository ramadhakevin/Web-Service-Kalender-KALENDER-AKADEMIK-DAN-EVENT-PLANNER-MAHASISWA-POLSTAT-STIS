package com.polstat.kalender.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.polstat.kalender.entity.EventType;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AcademicEventDto {
    private Long id;
    private String title;
    private String description;
    private Integer tanggal;
    private String bulan;
    private Integer tahun;
    private String target;
    private EventType eventType; // ID tipe acara
    private String academicYear;
    private Boolean isActive;
    private Date createdAt;
    private Date updatedAt;
}