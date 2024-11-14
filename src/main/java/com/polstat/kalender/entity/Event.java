package com.polstat.kalender.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = true)
    private String description;

    @Column(nullable = false)
    private Integer tanggal;

    @Column(nullable = false)
    private Integer bulan;

    @Column(nullable = false)
    private Integer tahun;

    @Column(nullable = true)
    private String target;

    @Enumerated(EnumType.STRING) // Atur tipe penyimpanan enum ke STRING
    @Column(nullable = true)
    private EventType eventType;

    @Column(nullable = true)
    private String academicYear;

    @Column(nullable = true)
    private Boolean isActive;

    @Column(nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
}
