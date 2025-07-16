package com.jongkeun.event_reservation.repository;

import com.jongkeun.event_reservation.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

// 다중 상속(Repository, 검색). 인터페이스만 가능
public interface EventRepository extends JpaRepository<Event, Long>, JpaSpecificationExecutor<Event> {
}
