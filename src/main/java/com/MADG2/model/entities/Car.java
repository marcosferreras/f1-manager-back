package com.MADG2.model.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Car implements Serializable {

    //PROPERTIES
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Name can´t be empty")
    @Size(min = 1, max = 255 , message = "Name between 1 and 255 characters")
    private String name;

    @Column(unique = true)
    private String code;

    private Double ERS_SlowCurve;

    private Double ERS_MediumCurve;

    private Double ERS_FastCurve;

    private Long consumption;


    //RELATIONSHIPS
    // ONE CAR = ONE TEAM
    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "team_id", updatable = false, insertable = false)
    private Team team;

    @Column(name = "team_id")
    private Long teamId;
}
