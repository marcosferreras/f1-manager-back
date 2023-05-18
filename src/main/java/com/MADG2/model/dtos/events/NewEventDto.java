package com.MADG2.model.dtos.events;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class NewEventDto {
    private OffsetDateTime startAt;
    private OffsetDateTime endAt;
    private String summary;
    private String color;
    private Long circuitId;
}
