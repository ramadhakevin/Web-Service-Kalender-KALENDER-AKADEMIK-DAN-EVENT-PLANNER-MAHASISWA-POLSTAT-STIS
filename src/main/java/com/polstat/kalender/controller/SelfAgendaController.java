package com.polstat.kalender.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.polstat.kalender.dto.SelfAgendaDto;
import com.polstat.kalender.mapper.SelfAgendaResponseMapper;
import com.polstat.kalender.service.SelfAgendaService;
import response.SelfAgendaResponse;

import java.util.List;

@RestController
@CrossOrigin( origins = "")
@RequestMapping("/self-agendas")
public class SelfAgendaController {

    private final SelfAgendaService selfAgendaService;

    @Autowired
    public SelfAgendaController(SelfAgendaService selfAgendaService) {
        this.selfAgendaService = selfAgendaService;
    }

    @PostMapping
    public ResponseEntity<SelfAgendaDto> createSelfAgenda(@RequestBody SelfAgendaDto selfAgendaDto) {
        SelfAgendaDto createdSelfAgenda = selfAgendaService.createSelfAgenda(selfAgendaDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSelfAgenda);
    }


    @GetMapping("/{id}")
    public ResponseEntity<SelfAgendaResponse> getSelfAgendaById(@PathVariable Long id) {
        SelfAgendaDto selfAgendaDto = selfAgendaService.getSelfAgendaById(id);
        return ResponseEntity.ok(SelfAgendaResponseMapper.mapToResponse(selfAgendaDto));
    }

    @GetMapping
    public ResponseEntity<List<SelfAgendaDto>> getAllSelfAgendas() {
        List<SelfAgendaDto> selfAgendas = selfAgendaService.getAllSelfAgendas();
        return ResponseEntity.ok(selfAgendas);
    }

    @GetMapping("/month/{month}")
    public ResponseEntity<List<SelfAgendaDto>> getSelfAgendasByMonth(@PathVariable String month) {
        List<SelfAgendaDto> selfAgendas = selfAgendaService.getSelfAgendasByMonth(month);
        return ResponseEntity.ok(selfAgendas);
    }

    @GetMapping("/target/{target}")
    public ResponseEntity<List<SelfAgendaDto>> getSelfAgendasByTarget(@PathVariable String target) {
        List<SelfAgendaDto> selfAgendas = selfAgendaService.getSelfAgendasByTarget(target);
        return ResponseEntity.ok(selfAgendas);
    }
}
