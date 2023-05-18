package com.MADG2.model.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.OffsetDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Event implements Serializable {

    //PROPERTIES
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private OffsetDateTime startAt;
    private OffsetDateTime endAt;

    // Formato startAt: 2021-11-21T18:00:00.000',
    // Formato endAt: 2021-11-21T18:00:00.000',

    private String summary;
    private String color;

    //RELATIONSHIPS
    //ONE EVENT = ONE CIRCUIT
    //@OneToOne(mappedBy="event")
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private Circuit circuit;

}
