package com.MADG2.model.entities;

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
public class Vote implements Serializable {

    //PROPERTIES
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Name can´t be empty")
    @Size(min = 1, max = 255 , message = "Name between 1 and 255 characters")
    private String name;

    @NotEmpty
    private String email;

    //RELATIONSHIPS

    // ONE VOTE = ONE DRIVER
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "driver_id", insertable = false, updatable = false)
    private Driver driver;

    @Column(name = "driver_id")
    private Long driverId;

    // ONE VOTE = ONE SURVEY
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "survey_id", insertable = false, updatable = false)
    private Survey survey;

    @Column(name = "survey_id")
    private Long surveyId;


}
