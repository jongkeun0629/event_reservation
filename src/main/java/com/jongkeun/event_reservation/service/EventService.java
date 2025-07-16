package com.jongkeun.event_reservation.service;

import com.jongkeun.event_reservation.dto.EventDto;
import com.jongkeun.event_reservation.model.Event;
import com.jongkeun.event_reservation.repository.EventRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional
public class EventService {
    private final EventRepository eventRepository;

    public Page<Event> search(String name, String location, LocalDateTime from, LocalDateTime to, Pageable pageable){
        Specification<Event> specification = Specification.allOf();
        // like. 단어 포함되어 있는
        if (name != null) specification = specification.and((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("name"), "%" + name + "%"));
        if (location != null) specification = specification.and((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("location"), "%" + location + "%"));
        // from 부터 (이상)
        if (from != null) specification = specification.and((root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("eventDate"), from));
        // to 까지 (이하)
        if (to != null) specification = specification.and((root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("eventDate"), to));

        return eventRepository.findAll(specification, pageable);
    }

    public Event getById(Long id){
        return eventRepository.findById(id).orElseThrow(() -> new NoSuchElementException("이벤트 없음"));
    }

    public Event create(EventDto eventDto){
        Event event = new Event();

        event.setName(eventDto.getName());
        event.setEventDate(eventDto.getEventDate());
        event.setLocation(eventDto.getLocation());

        return eventRepository.save(event);
    }

    public Event update(Long id, EventDto eventDto) {
        Event event = getById(id);
        event.setName(eventDto.getName());
        event.setEventDate(eventDto.getEventDate());
        event.setLocation(eventDto.getLocation());

        return eventRepository.save(event);
    }

    public void delete(Long id){
        eventRepository.deleteById(id);
    }
}
