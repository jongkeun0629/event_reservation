package com.jongkeun.event_reservation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventDto {
    @NotBlank(message = "이벤트명을 입력하세요.")
    private String name;

    @NotNull(message = "이벤트 날짜를 입력하세요.")
    private LocalDateTime eventDate;

    private String location;
}
