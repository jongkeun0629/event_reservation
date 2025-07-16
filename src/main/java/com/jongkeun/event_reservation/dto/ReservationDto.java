package com.jongkeun.event_reservation.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDto {
    @NotBlank(message = "예약자 이름을 입력하세요.")
    private String attendeeName;

    @Min(1)
    @NotNull(message = "좌석 수를 입력하세요.")
    private Integer seats;
}
