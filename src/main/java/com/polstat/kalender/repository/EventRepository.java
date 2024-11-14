package com.polstat.kalender.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.polstat.kalender.entity.Event;
import com.polstat.kalender.entity.EventType;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findAll();
    List<Event> findByBulan(int bulan);
    List<Event> findByTarget(String target);
    List<Event> findByEventType(EventType eventType);
    List<Event> findByBulanAndEventType(int monthNumber, EventType eventType);
    List<Event> findByTargetAndEventType(String target, EventType eventType);
}