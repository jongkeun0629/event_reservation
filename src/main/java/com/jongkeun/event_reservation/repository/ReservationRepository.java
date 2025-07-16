package com.jongkeun.event_reservation.repository;

import com.jongkeun.event_reservation.model.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository 상속 시 어노테이션 사용 안해도 레포지토리로 자동 추론
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    Page<Reservation> findByEventId(Long eventId, Pageable pageable);
}
