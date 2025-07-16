package com.jongkeun.event_reservation.controller;

import com.jongkeun.event_reservation.dto.EventDto;
import com.jongkeun.event_reservation.model.Event;
import com.jongkeun.event_reservation.service.EventService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;

    @GetMapping
    public Page<Event> list(
            // 기본 값 false. 있는 경우 search 에서 검색
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String location,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("eventDate").ascending());
        return eventService.search(name, location, from, to, pageable);
    }

    @GetMapping("/{id}")
    // 주소 창의 id값 Service의 getById 메서드로 전달. 단일 검색
    public Event get(@PathVariable Long id){
        return eventService.getById(id);
    }

    @PostMapping
    public Event create(@Valid @RequestBody EventDto eventDto){
        return eventService.create(eventDto);
    }

    @PutMapping("/{id}")
    public Event update(@PathVariable Long id, @Valid @RequestBody EventDto eventDto) {
        return eventService.update(id, eventDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        eventService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
