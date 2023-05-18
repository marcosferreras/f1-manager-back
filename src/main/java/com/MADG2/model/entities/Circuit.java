package com.MADG2.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Circuit implements Serializable {

    //PROPERTIES
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Name can´t be empty")
    @Size(min = 1, max = 255 , message = "Name between 1 and 255 characters")
    private String name;

    @NotEmpty
    private String city;

    private String country;

    private String image;

    private Long laps;

    private Long meters;

    private Long slowCurves;

    private Long mediumCurves;

    private Long fastCurves;


    //RELATIONSHIPS
    //ONE CIRCUIT = ONE EVENT
    @JsonIgnore
    //@OneToOne(cascade = CascadeType.MERGE)
    //@JoinColumn(name = "event_id", referencedColumnName = "id")
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE, mappedBy = "circuit")
    private List<Event> event;
}
